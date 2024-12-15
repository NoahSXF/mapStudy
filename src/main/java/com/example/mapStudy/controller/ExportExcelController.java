package com.example.mapStudy.controller;

import com.alibaba.fastjson2.JSON;
import com.example.mapStudy.entity.Password;
import com.example.mapStudy.entity.PasswordDto;
import com.example.mapStudy.service.PasswordServer;
import com.example.mapStudy.utils.WordUtil;
import com.github.pagehelper.PageInfo;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.controller
 * @Author: Noah
 * @CreateTime: 2022-11-12  22:29
 * @Description: excel导出
 * @Version: 1.0
 */
@RestController
@RequestMapping("/export/excel")
public class ExportExcelController {
    @Autowired
    PasswordServer passwordServer;
    @RequestMapping(value = {"/test/{key}","/test"})
    public void test(HttpServletResponse response, @PathVariable(value = "key", required = false) String key) throws Exception {
        List<String[]> demos = getDemoList(key);
        InputStream is = this.getClass().getResourceAsStream("/temple/temple.xlsx");
        assert is != null;
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        //获取第一张表格
        XSSFSheet sheetAt = workbook.getSheetAt(0);
        //获取标题列数
        int physicalNumberOfCells = demos.get(0).length;
        for (int j = 0; j < demos.size(); j++) {
            XSSFRow xssfRow = sheetAt.createRow(j);//行
            for (int i = 0; i < physicalNumberOfCells; i++) {
                //创建单元格
                XSSFCell xssfCell = xssfRow.createCell(i);
                xssfCell.setCellValue(demos.get(j)[i]);
            }
        }
        File file = new File("D://temple.xlsx");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream os = new FileOutputStream(file);
        workbook.write(os);
        WordUtil.close(os);
        WordUtil.close(is);

        response.reset();
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader(
                "Content-disposition",
                "attachment; filename=afgsdg.xlsx");

        try(
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D://temple.xlsx"));
                // 输出流
                BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        ){
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = bis.read(buff)) > 0) {
                bos.write(buff, 0, len);
            }
        }
    }

    private List<String[]> getDemoList(String key) {
        Password password = new Password();
        password.setKey(key);
        PageInfo<Password> passwordPageInfo = passwordServer.selectByPassword(password, 1, 500);
        List<Password> list = passwordPageInfo.getList();
        List<String[]> arrayList = new ArrayList<>();
        arrayList.add(new String[] {"1","2","3","2","3","2","3","2","3"});
        //获取实体类中声明的所有属性
        list.forEach(x->{
            PasswordDto passwordDto = JSON.parseObject(JSON.toJSONString(x), PasswordDto.class);

            Field[] fields = passwordDto.getClass().getDeclaredFields();
            //创建一个数组来保存属性值
            String[] values = new String[fields.length];
            //循环遍历所有属性，获取属性值并保存到数组中
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                try {
                    if(fields[i].get(passwordDto) == null){
                        values[i] = "";
                    }else if(fields[i].get(passwordDto) instanceof Date){
                        values[i] =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)fields[i].get(passwordDto));
                    }else{
                        values[i] = fields[i].get(passwordDto).toString();
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            //输出数组中的属性值
            arrayList.add(values);
        });

        return arrayList;
    }
}
