package com.carla.hiringcase.service;

import com.carla.hiringcase.entity.Compensation;
import com.carla.hiringcase.helper.JsonReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CompensationService {

    private final List<Compensation> compensations = new ArrayList<>();

    public CompensationService() {
        try {
            compensations.addAll(JsonReader.readCompensationData("src/main/resources/data/salary_survey-1.json"));
            compensations.addAll(JsonReader.readCompensationData("src/main/resources/data/salary_survey-2.json"));
            compensations.addAll(JsonReader.readCompensationData("src/main/resources/data/salary_survey-3.json"));
            log.info("Loaded compensations: " + compensations.size());
        } catch (IOException e) {
            log.error("Error loading compensations: " + e.getMessage());
        }
    }

    public List<Compensation> getCompensations(Map<String, String> filters) {
        filters.remove("sort");

        log.info("Applying filters: " + filters);
        List<Compensation> filteredCompensations = compensations.stream()
                .filter(createPredicate(filters))
                .sorted(createComparator())
                .collect(Collectors.toList());
        log.info("Filtered Compensation Count: " + filteredCompensations.size());
        return filteredCompensations;
    }

    public Compensation getSingleCompensation(Map<String, String> filters) {
        return compensations.stream()
                .filter(createPredicate(filters))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Object> getSparseCompensation(Compensation compensation, String[] fields) {
        if (compensation == null || fields == null || fields.length == 0) {
            return compensation.getProperties();
        }

        Map<String, Object> sparseProperties = new LinkedHashMap<>();
        for (String field : fields) {
            if (compensation.getProperties().containsKey(field)) {
                sparseProperties.put(field, compensation.getProperties().get(field));
            }
        }
        return sparseProperties;
    }

    private Predicate<Compensation> createPredicate(Map<String, String> filters) {
        return compensation -> {
            for (Map.Entry<String, String> filter : filters.entrySet()) {
                String key = filter.getKey();
                String value = filter.getValue();
                Object propertyValue = compensation.getProperties().get(key);

                if (propertyValue == null || !propertyValue.toString().equalsIgnoreCase(value)) {
                    return false;
                }
            }
            return true;
        };
    }

    private Comparator<Compensation> createComparator() {
        return Comparator.comparing(c -> parseTimestamp(c.getProperties().get("Timestamp").toString()));
    }

    private LocalDateTime parseTimestamp(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy H:mm:ss");
        return LocalDateTime.parse(timestamp, formatter);
    }
}