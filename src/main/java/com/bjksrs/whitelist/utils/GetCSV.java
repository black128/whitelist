package com.bjksrs.whitelist.utils;

import com.csvreader.CsvReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class GetCSV {
    //读取csv文件
    public List<String[]> readCsv(String filePath) throws Exception {
        List<String[]> csvList = new ArrayList<String[]>();
        if (isCsv(filePath)) {
            CsvReader reader = new CsvReader(filePath, ',', Charset.forName("GBK"));
            reader.readHeaders(); // 跳过表头   如果需要表头的话，不要写这句。
            while (reader.readRecord()) { //逐行读入除表头的数据
                csvList.add(reader.getValues());
            }
            reader.close();
        } else {
            System.out.println("此文件不是CSV文件！");
        }
        return csvList;
    }
    //判断是否是csv文件
    private boolean isCsv(String fileName) {
        return fileName.matches("^.+\\.(?i)(csv)$");
    }
    //方法测试
    public static void main(String[] args) throws Exception {
        String filepath = "C:/群组.csv";
//        CsvUtil su = new CsvUtil();
//        List<String[]> list = su.readCsv(filepath);
//        for (int r = 0; r < list.size(); r++) {
//            for (int c = 0; c < list.get(r).length; c++) {
//                String cell = list.get(r)[c];
//                System.out.print(cell + "\t");
//            }
//            System.out.print("\n");
//        }
    }
}