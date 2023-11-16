package com.testcase.pegasagro.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.testcase.pegasagro.service.GpsLogService;

@Service
public class GpsLogServiceImpl implements GpsLogService {

    private double totalDistance = 0.0;
    private double speed = 0.0;
    private double lastLat = 0.0;
    private double lastLon = 0.0;

    public void processFile(MultipartFile file) throws IOException {
        if (totalDistance != 0.0) {
            totalDistance = 0.0;
            speed = 0.0;
            lastLat = 0.0;
            lastLon = 0.0;
        }
        try (InputStream inputStream = file.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            reader.lines().forEach(this::processLine);
        }
    }

    private void processLine(String line) {
        String[] parts = line.split(",");
        if (parts[0].equals("$GPGGA")) {
            double lat = parseLatLon(parts[2]);
            double lon = parseLatLon(parts[4]);
            if (lastLat != 0.0 && lastLon != 0.0 && speed != 0.0) {
                totalDistance += calculateDistance(lastLat, lastLon, lat, lon);
            }
            lastLat = lat;
            lastLon = lon;
        } else if (parts[0].equals("$GNVTG")) {
            int kIndex = Arrays.asList(parts).indexOf("K");
            if (kIndex > 0 && kIndex != 1) {
                speed = Double.parseDouble(parts[kIndex - 1]);
            }
        }
    }

    private double parseLatLon(String str) {
        int degree;
        double minutes;
        if (str == null || str.isEmpty()) {
            degree = 0;
            minutes = 0;
        } else if (str.contains(".")) {
            degree = Integer.parseInt(str.substring(0, str.indexOf(".") - 2));
            minutes = Double.parseDouble(str.substring(str.indexOf(".") - 2));
        } else {
            degree = Integer.parseInt(str.substring(0, str.length() - 2));
            minutes = Double.parseDouble(str.substring(str.length() - 2));
        }
        return degree + minutes / 60;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        int earthRadiusKm = 6371;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadiusKm * c;
    }

    @Override
    public double getTotalDistance() {
        return totalDistance;
    }
}
