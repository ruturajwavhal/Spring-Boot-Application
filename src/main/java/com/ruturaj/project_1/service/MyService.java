package com.ruturaj.project_1.service;

import com.ruturaj.project_1.datakepper.DataKepper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MyService {

    public Map<Integer, List<String>> getByCategory(Map<Integer, List<String>> data, String categoryName) {
        return data.entrySet().stream()
                .filter(entry -> entry.getValue().get(0).equalsIgnoreCase(categoryName))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, List<String>> getFilterByNullValues(Map<Integer, List<String>> data) {
        Map<Integer, List<String>> sortedMap = data.entrySet().stream()
                .filter(entry -> {
                    String dob = entry.getValue().get(2);
                    return dob == null || dob.trim().isEmpty();
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
        return sortedMap;
    }

    public Map<Integer, List<String>> getFilterByCategoryNullValues(Map<Integer, List<String>> data, String category) {
        Map<Integer, List<String>> sortedMap = data.entrySet().stream()
                .filter(entry -> {
                    String dob = entry.getValue().get(2);
                    return (dob == null || dob.trim().isEmpty()) && entry.getValue().get(0).equalsIgnoreCase(category);
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
        return sortedMap;
    }

}
