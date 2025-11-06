package com.ruturaj.project_1.exceloperations;

import com.ruturaj.project_1.datakepper.DataKepper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReadExcel {
    public static void readExcel() throws IOException {
        FileInputStream file = new FileInputStream(new File("D:\\demo.xlsx"));
        Workbook workbook = new XSSFWorkbook(file);
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
