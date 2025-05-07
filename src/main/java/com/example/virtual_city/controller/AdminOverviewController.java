package com.example.virtual_city.controller;

import com.example.virtual_city.service.AdminOverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/overview-data") // ✅ Updated path to match frontend & security config
@RequiredArgsConstructor
public class AdminOverviewController {

    private final AdminOverviewService adminOverviewService;

    @GetMapping
    public ResponseEntity<?> getAdminOverview() {
        return ResponseEntity.ok(adminOverviewService.getAdminOverview());
    }
}
