package com.example.mapStudy.controller;

import com.example.mapStudy.utils.WordUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.controller
 * @Author: Noah
 * @CreateTime: 2022-11-11  21:30
 * @Description: word导出
 * @Version: 1.0
 */
@RestController
@RequestMapping("/export/word")
public class ExportWordController {

    @RequestMapping("/test")
    public void test() throws Exception {
        Map<String, Object> params = new HashMap<>(16);
        params.put("maven", "maven");
        params.put("svn", "svn");
        params.put("name", "张三");
        params.put("age", "33");
        params.put("work", "擦车");
        params.put("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        params.put("tableList", getTableList());
        InputStream is = this.getClass().getResourceAsStream("/temple/temple.docx");
        assert is != null;
        XWPFDocument doc = new XWPFDocument(is);
        WordUtil.replaceInPara(doc, params);
        WordUtil.replaceInTable(doc, params);
        WordUtil.replaceInTable2(doc, (List<String[]>) params.get("tableList"), 0);
        Resource resource = new DefaultResourceLoader().getResource("/temple/temple.docx");
        File file2 = resource.getFile();
        FileOutputStream os = new FileOutputStream(file2);
        doc.write(os);
        WordUtil.close(os);
        WordUtil.close(is);
    }

    private List<String[]> getTableList() {
        ArrayList<String[]> list = new ArrayList<>();
        String[] s1 = {"s1", "小鸡炖蘑菇了不起1", "1", "T1", "501", "2000.00", "300000.00", "1"};
        String[] s2 = {"s2", "小鸡炖蘑菇了不起2", "2", "T2", "502", "2000.00", "300000.00", "2"};
        String[] s3 = {"s3", "小鸡炖蘑菇了不起3", "3", "T3", "503", "2000.00", "300000.00", "3"};
        list.add(s1);
        list.add(s2);
        list.add(s3);
        return list;
    }
}


