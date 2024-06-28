package com.carla.hiringcase.controller;

import com.carla.hiringcase.entity.Compensation;
import com.carla.hiringcase.service.CompensationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compensation_data")
public class CompensationController {

    private final CompensationService compensationService;

    @GetMapping
    public List<Compensation> getCompensationData(
            @RequestParam Map<String, String> params,
            @RequestParam(required = false) String[] sort) {
        System.out.println("Received GET request with params: " + params + " and sort: " + Arrays.toString(sort));

        if (params.containsKey("sort")) {
            params.get("sort");
            params.remove("sort");
        }

        List<Compensation> compensations = compensationService.getCompensations(params);
        System.out.println("Returning " + compensations.size() + " compensations");
        return compensations;
    }

    @GetMapping("/single")
    public Map<String, Object> getSingleCompensationData(
            @RequestParam Map<String, String> params,
            @RequestParam(required = false) String[] fields) {
        System.out.println("Received GET request for single record with params: " + params + " and fields: " + Arrays.toString(fields));

        Compensation compensation = compensationService.getSingleCompensation(params);
        Map<String, Object> sparseCompensation = compensationService.getSparseCompensation(compensation, fields);

        System.out.println("Returning single compensation record with fields: " + sparseCompensation);
        return sparseCompensation;
    }
}