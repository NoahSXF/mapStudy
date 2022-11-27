package com.example.mapStudy.controller;

import com.example.mapStudy.entity.Demo;
import com.example.mapStudy.utils.WordUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
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
    @RequestMapping("/test")
    public void test() throws Exception {
        List<Demo> demos = getDemoList();
        InputStream is = this.getClass().getResourceAsStream("/temple/temple.xlsx");
        assert is != null;
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        //获取第一张表格
        XSSFSheet sheetAt = workbook.getSheetAt(0);
        //获取标题，我的标题是表格的第一行，所以获取第一行数据
        XSSFRow row = sheetAt.getRow(0);
        //获取标题列数
        int physicalNumberOfCells = row.getPhysicalNumberOfCells();
        for (int j = 0; j < demos.size(); j++) {
            //创建行(因为第一行是标题，所以从第二行开始插入)
            XSSFRow xssfRow = sheetAt.createRow(j + 1);
            for (int i = 0; i < physicalNumberOfCells; i++) {
                //获取标题单元格
                XSSFCell titleCell = row.getCell(i);
                if (titleCell != null) {
                    //获取标题单元格内容
                    String titleCellValue = row.getCell(i).getStringCellValue();
                    //创建单元格
                    XSSFCell xssfCell = xssfRow.createCell(i);
                    if ("姓名".equals(titleCellValue)) {
                        //设置单元格内容
                        xssfCell.setCellValue(demos.get(j).getName());
                    } else if ("年龄".equals(titleCellValue)) {
                        xssfCell.setCellValue(demos.get(j).getAge());
                    } else if ("手机号".equals(titleCellValue)) {
                        xssfCell.setCellValue(demos.get(j).getPhone());
                    }
                }
            }
        }
        Resource resource = new DefaultResourceLoader().getResource("/temple/temple.xlsx");
        File file2 = resource.getFile();
        FileOutputStream os = new FileOutputStream(file2);
        workbook.write(os);
        WordUtil.close(os);
        WordUtil.close(is);
    }

    private List<Demo> getDemoList() {
        Demo demo = new Demo("111", "222", "333");
        Demo demo1 = new Demo("222", "333", "444");
        List<Demo> demos = new ArrayList<>();
        demos.add(demo);
        demos.add(demo1);
        return demos;
    }
}
