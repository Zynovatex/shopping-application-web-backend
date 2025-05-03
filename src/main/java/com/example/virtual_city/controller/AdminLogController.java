package com.example.virtual_city.controller;

import com.example.virtual_city.model.AdminLog;
import com.example.virtual_city.service.AdminLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/logs")
public class AdminLogController {

    private final AdminLogService adminLogService;

    public AdminLogController(AdminLogService adminLogService) {
        this.adminLogService = adminLogService;
    }

    // ✅ Fetch all logs (Super Admin only)
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<AdminLog>> getAllLogs() {
        List<AdminLog> logs = adminLogService.getAllLogs();
        return ResponseEntity.ok(logs);
    }

    // 🔄 Optional: Save a log manually (internal use or testing only)
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<String> createLog(@RequestBody AdminLog log) {
        adminLogService.saveLog(log);
        return ResponseEntity.ok("Log saved successfully");
    }
}
