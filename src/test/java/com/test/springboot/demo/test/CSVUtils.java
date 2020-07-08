package com.test.springboot.demo.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    
    /**
     * 生成为CVS文件
     *
     * @param exportData 源数据List
     * @param path 文件路径
     * @return
     */
    public File createCSVFile(List<List<String>> exportData, String path) {
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            csvFile = Files.createFile(Paths.get(path)).toFile();
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), StandardCharsets.UTF_8), 1024);
            for (List<String> exportDatum : exportData) {
                writeRow(exportDatum, csvFileOutputStream);
                csvFileOutputStream.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (csvFileOutputStream != null) {
                    csvFileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * 写一行数据
     *
     * @param row 数据列表
     * @param csvWriter
     * @throws IOException
     */
    private void writeRow(List<String> row, BufferedWriter csvWriter) throws IOException {
        int i = 0;
        for (String data : row) {
            csvWriter.write(DelQuota(data));
            if (i != row.size() - 1) {
                csvWriter.write(",");
            }
            i++;
        }
    }

    /**
     * 剔除特殊字符
     *
     * @param str 数据
     */
    public String DelQuota(String str) {
        String[] strQuota = { "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "`", ";", "'", ",", ".", "/", ":", "/,", "<", ">", "?" };
        for(String v : strQuota){
            if(str.contains(v)){
                str = str.replace(v, "");
            }
        }
        return str;
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        CSVUtils csvUtils = new CSVUtils();
        List<List<String>> listList = new ArrayList<List<String>>();
        List<String> list = null;
        for (int i = 0; i < 5; i++) {
            list = new ArrayList<String>();// 一个List为一行
            list.add("测试");
            list.add("测试");
            list.add("测试");
            listList.add(list);
        }
        csvUtils.createCSVFile(listList, "D://csv//test.csv");
    }

}
