package com.ruturaj.project_1;

import com.ruturaj.project_1.exceloperations.ReadExcel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Project1Application {

	public static void main(String[] args) {

        SpringApplication.run(Project1Application.class, args);
        try {
            ReadExcel.readExcel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
