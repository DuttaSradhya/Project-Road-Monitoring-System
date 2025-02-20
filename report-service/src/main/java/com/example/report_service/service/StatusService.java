package com.example.report_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.report_service.model.ReportStatus;
import com.example.report_service.repository.ReportStatusRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusService {

    private final ReportStatusRepository statusRepository;

    public void addStatus(ReportStatus status){
        statusRepository.save(status);
        log.info("Status {} has been added", status.getStatus());
    }

    public List<ReportStatus> getAllStatus(){
        return statusRepository.findAll();
    }

    public void deleteById(String id) {
         Optional<ReportStatus> status = statusRepository.findById(id);
        if (status.isPresent()) {
            statusRepository.deleteById(id);
            log.info("Status {} has been deleted", status.get().getStatus());
        } else {
            log.warn("Category with ID {} not found", id);
        }
    }
    
}
