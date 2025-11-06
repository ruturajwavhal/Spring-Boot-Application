package com.ruturaj.project_1.controller;

import com.ruturaj.project_1.datakepper.DataKepper;
import com.ruturaj.project_1.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/excel")
public class Project1Controller {

    Map<Integer, List<String>> data = new HashMap<>();
    private final MyService myService;
    @Autowired
    public Project1Controller(MyService myService)
    {
        this.myService = myService;
    }

    @GetMapping("/read/all")
    public String readAllExcelData(
            @RequestParam(required = false) String category,
            Model model) {

        Map<Integer, List<String>> dataMap = DataKepper.data;


        if (category != null && !category.isEmpty()) {
            dataMap = dataMap.entrySet().stream()
                    .filter(entry -> entry.getValue().get(0).equalsIgnoreCase(category))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        model.addAttribute("dataMap", dataMap);
        model.addAttribute("selectedCategory", category);

        return "display";
    }

//    @GetMapping("/read/{category}")
//    public Map<Integer, List<String>> readByCategory(@PathVariable String category) {
//        return myService.getByCategory(DataKepper.data, category);
//    }
//
//    @GetMapping("/filter/null")
//    public Map<Integer, List<String>> filterByNullValues() {
//        return myService.getFilterByNullValues(DataKepper.data);
//    }
//
//    @GetMapping("/filter/{category}/null")
//    public Map<Integer, List<String>> filterByCategoryNullValues(@PathVariable String category) {
//        return myService.getFilterByCategoryNullValues(DataKepper.data, category);
//    }

}
