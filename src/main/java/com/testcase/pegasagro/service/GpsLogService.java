package com.testcase.pegasagro.service;

import org.springframework.web.multipart.MultipartFile;

public interface GpsLogService {
    double calculateDistance(MultipartFile file);
}
