package com.ruturaj.project_1.controller;

import com.ruturaj.project_1.datakepper.DataKepper;
import com.ruturaj.project_1.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/excel")
public class Project1Controller {

    private final MyService myService;

    @Autowired
    public Project1Controller(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/read/all")
    public String readAllExcelData(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search,
            Model model) {

        Map<Integer, List<String>> dataMap = DataKepper.data;

        if (category != null && !category.isEmpty()) {
            dataMap = dataMap.entrySet().stream()
                    .filter(entry -> entry.getValue().get(0).equalsIgnoreCase(category))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        if (search != null && !search.isEmpty()) {
            String searchLower = search.toLowerCase();
            dataMap = dataMap.entrySet().stream()
                    .filter(entry -> entry.getValue().stream()
                            .anyMatch(value -> value != null && value.toLowerCase().contains(searchLower)))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        // Optional: sort by ID
        dataMap = dataMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        model.addAttribute("dataMap", dataMap);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("searchText", search);

        return "display";
    }

    @GetMapping("/search")
    @ResponseBody
    public Map<Integer, List<String>> searchExcelData(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search) {

        Map<Integer, List<String>> dataMap = DataKepper.data;

        if (category != null && !category.isEmpty()) {
            dataMap = dataMap.entrySet().stream()
                    .filter(entry -> entry.getValue().get(0).equalsIgnoreCase(category))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        if (search != null && !search.isEmpty()) {
            String searchLower = search.toLowerCase();
            dataMap = dataMap.entrySet().stream()
                    .filter(entry -> entry.getValue().stream()
                            .anyMatch(value -> value != null && value.toLowerCase().contains(searchLower)))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        return dataMap;
    }
}
