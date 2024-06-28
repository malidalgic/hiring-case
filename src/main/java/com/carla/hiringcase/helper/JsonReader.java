package com.carla.hiringcase.helper;

import com.carla.hiringcase.entity.Compensation;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonReader {
    public static List<Compensation> readCompensationData(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Compensation> compensations = objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, Compensation.class));
        System.out.println("Loaded " + compensations.size() + " compensations from " + filePath);
        return compensations;
    }
}