package com.example.springbootfirstdemo.com.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Excel2Txt {

    private static Map<Integer, String> keyMapName = new LinkedHashMap<Integer, String>();
    private static Map<String, String> keyMapContext = new LinkedHashMap<String, String>();

    static ConcurrentLinkedQueue<String> queues = new ConcurrentLinkedQueue<String>();

    static ObjectMapper objectMapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);


    public static void main(String[] args) {

        System.out.println("111111111111");

            HSSFWorkbook wb = null;
            POIFSFileSystem fs = null;

            String filePath = "E:\\Desktop\\111.xls";
        try {
            fs = new POIFSFileSystem(new FileInputStream(new File(filePath)));
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HSSFSheet sheet = wb.getSheetAt(0);
            Integer rowNum;
            HSSFRow rowName = sheet.getRow(0);
            for (int cellNum = rowName.getFirstCellNum(); cellNum < rowName.getLastCellNum(); cellNum++){
                keyMapName.put(cellNum, rowName.getCell(cellNum).getStringCellValue());
            }

            for(rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++){
                HSSFRow row = sheet.getRow(rowNum);
                if(row != null){
                    for (int cellNum = row.getFirstCellNum(); cellNum< row.getLastCellNum(); cellNum++){
                        if(row.getCell(cellNum).getStringCellValue() != null && !"".equals(row.getCell(cellNum).getStringCellValue())) {
                            keyMapContext.put(keyMapName.get(cellNum), row.getCell(cellNum).getStringCellValue());
                        }
                    }
                }
                if(keyMapContext != null && keyMapContext.size() > 0) {
                    try {
                        queues.add(objectMapper.writeValueAsString(keyMapContext));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            }

            File file = new File("E:\\Desktop\\111.txt");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        OutputStreamWriter outputStream = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStream);

            int i = 0;
            while (true){
                if(!queues.isEmpty()) {
                    String context = queues.poll();
                    if(context == null) continue;
                    try {
                        bufferedWriter.write(context);
                        bufferedWriter.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    i++;
                    System.out.println("total ：" + i);
                }
                if(queues.isEmpty()){
                    try{
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        outputStream.close();
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                    catch (Exception e){
                        e.printStackTrace(System.out);
                    }
                    break;
                }
            }
        }

}
