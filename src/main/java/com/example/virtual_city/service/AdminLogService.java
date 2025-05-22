package com.example.virtual_city.service;

import com.example.virtual_city.model.AdminLog;
import com.example.virtual_city.repository.AdminLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminLogService {

    private final AdminLogRepository adminLogRepository;

    public AdminLogService(AdminLogRepository adminLogRepository) {
        this.adminLogRepository = adminLogRepository;
    }

    // Get all logs sorted by timestamp (newest first)
    public List<AdminLog> getAllLogs() {
        return adminLogRepository.findAllByOrderByTimestampDesc();
    }

    // Save a new log entry
    public void saveLog(AdminLog log) {
        adminLogRepository.save(log);
    }
}
