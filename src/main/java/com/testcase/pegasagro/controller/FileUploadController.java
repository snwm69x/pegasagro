package com.testcase.pegasagro.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.testcase.pegasagro.service.impl.GpsLogServiceImpl;

@RestController
public class FileUploadController {

    @Autowired
    private GpsLogServiceImpl gpsLogServiceImpl;

    @PostMapping("/upload")
    public Double uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            gpsLogServiceImpl.processFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gpsLogServiceImpl.getTotalDistance();
    }
}
