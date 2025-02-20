package com.example.analysis_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.analysis_service.model.Report;

@Service
public class ReportService {

    @Autowired
    private ReportClient reportClient;

    public List<Report> fetchAllReports(){
        return reportClient.getAllReports();
    }
}
