package com.testcase.pegasagro.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface GpsLogService {
    void processFile(MultipartFile file) throws IOException;

    double getTotalDistance();
}
