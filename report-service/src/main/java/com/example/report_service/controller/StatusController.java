package com.example.report_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.report_service.model.ReportStatus;
import com.example.report_service.service.StatusService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/report/status")
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReportStatus> getAllStatus() {
        return statusService.getAllStatus();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addStatus(@RequestBody ReportStatus status) {
        statusService.addStatus(status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable String id) {
        statusService.deleteById(id);
    }
    
    
}
