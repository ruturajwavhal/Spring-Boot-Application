package com.ruturaj.project_1.controller;

import com.ruturaj.project_1.datakepper.DataKepper;
import com.ruturaj.project_1.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
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
    public Map<Integer, List<String>> readAllExelData() {
        return DataKepper.data;
    }

    @GetMapping("/read/{category}")
    public Map<Integer, List<String>> readByCategory(@PathVariable String category) {
        return myService.getByCategory(DataKepper.data, category);
    }

    @GetMapping("/filter/null")
    public Map<Integer, List<String>> filterByNullValues() {
        return myService.getFilterByNullValues(DataKepper.data);
    }

    @GetMapping("/filter/{category}/null")
    public Map<Integer, List<String>> filterByCategoryNullValues(@PathVariable String category) {
        return myService.getFilterByCategoryNullValues(DataKepper.data, category);
    }

}
