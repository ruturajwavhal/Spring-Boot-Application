package com.ruturaj.project_1.exceloperations;

import com.ruturaj.project_1.datakepper.DataKepper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReadExcel {

    //Read all excelsheet data and converts it into map where id is key and remaining columns are values
    public static void readExcel() throws IOException {
        InputStream inputStream = ReadExcel.class.getResourceAsStream("/demo.xlsx");
        if (inputStream == null) {
            throw new RuntimeException("demo.xlsx not found in resources folder!");
        }
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> data = new HashMap<>();

        int rowCount = sheet.getPhysicalNumberOfRows();

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            List<String> rowData = new ArrayList<>();
            int key = 0;
            int cellCount = row.getPhysicalNumberOfCells();

            for (int j = 0; j < cellCount; j++) {
                Cell cell = row.getCell(j);
                if(j==2) {
                    key = (int) Double.parseDouble(cell.toString());
                }
                else {
                    if (cell == null) {
                        rowData.add("");
                    }
                    else {
                        rowData.add(cell.toString());
                    }
                }
            }
             data.put(key , rowData);
        }
        System.out.println(data);
        DataKepper.data = data;
    }

}
