package com.example.mapStudy.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.utils
 * @Author: Noah
 * @CreateTime: 2022-11-11  21:24
 * @Description: word导出工具类
 * @Version: 1.0
 */
public class WordUtil {

    /**
     * 替换段落里面的变量
     *
     * @param doc    要替换的文档
     * @param params 参数
     */
    public static void replaceInPara(XWPFDocument doc, Map<String, Object> params) {
        Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
        XWPFParagraph para;
        while (iterator.hasNext()) {
            para = iterator.next();
            replaceInPara(para, params);
        }
    }

    /**
     * 替换段落里面的变量
     *
     * @param para   要替换的段落
     * @param params 参数
     */
    public static void replaceInPara(XWPFParagraph para, Map<String, Object> params) {

        if (matcher(para.getParagraphText()).find()) {
            List<XWPFRun> runs = para.getRuns();
            for (XWPFRun run : runs) {
                String runText = run.toString();
                List<String> keywords = getKeywords(runText);
                for (String keyword : keywords) {
                    String key = keyword.replace("${", "").replace("}", "");
                    String keyValue = (String) params.get(key);
                    if (StringUtils.isEmpty(keyValue)) {
                        keyValue = "";
                    }
                    runText = runText.replace(keyword, keyValue);
                }
                run.setText(runText, 0);
            }
        }
    }

    /**
     * 替换表格里面的变量
     *
     * @param doc    要替换的文档
     * @param params 参数
     */
    public static void replaceInTable(XWPFDocument doc, Map<String, Object> params) {
        Iterator<XWPFTable> iterator = doc.getTablesIterator();
        XWPFTable table;
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        List<XWPFParagraph> paras;
        while (iterator.hasNext()) {
            table = iterator.next();
            rows = table.getRows();
            for (XWPFTableRow row : rows) {
                cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    paras = cell.getParagraphs();
                    for (XWPFParagraph para : paras) {
                        replaceInPara(para, params);
                    }
                }
            }
        }
    }

    /**
     * 语句中是否有${}
     *
     * @param str 字符串
     */
    private static Matcher matcher(String str) {
        String regex = "\\$\\{(.+?)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(str);
    }

    /**
     * 关闭输入流
     *
     * @param is 输入流
     */
    public static void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭输出流
     *
     * @param os 输出流
     */
    public static void close(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 匹配出对象的关键字
     *
     * @param source 匹配内容
     * @date 2022/11/12 9:46
     * @return: null
     */
    private static List<String> getKeywords(String source) {
        String regStr = "\\$\\{[a-zA-Z0-9]+}";
        List<String> matchStars = new ArrayList<>();
        Pattern patten = Pattern.compile(regStr);
        Matcher matcher = patten.matcher(source);
        while (matcher.find()) {
            matchStars.add(matcher.group());
        }
        return matchStars;
    }

    /**
     * @param doc       文档
     * @param tableList 要增添的list
     * @param index     增添的表格序号
     * @date 2022/11/12 10:24
     * @return: void
     */
    public static void replaceInTable2(XWPFDocument doc, List<String[]> tableList, int index) {
        List<XWPFTable> tables = doc.getTables();
        XWPFTable table = tables.get(index);
        for (int i = 1; i <= tableList.size(); i++) {
            XWPFTableRow newRow = table.createRow();
            List<XWPFTableCell> cells = newRow.getTableCells();
            for (int j = 0; j < cells.size(); j++) {
                XWPFTableCell cell = cells.get(j);
                cell.setText(tableList.get(i - 1)[j]);
            }
        }
    }
}


