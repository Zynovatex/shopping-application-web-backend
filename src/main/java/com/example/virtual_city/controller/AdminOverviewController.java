package com.example.virtual_city.controller;

import com.example.virtual_city.service.AdminOverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/overview-data") // ✅ changed path to avoid conflict
@RequiredArgsConstructor
public class AdminOverviewController {

    private final AdminOverviewService adminOverviewService;

    @GetMapping
    public ResponseEntity<?> getAdminOverview() {
        return ResponseEntity.ok(adminOverviewService.getAdminOverview());
    }
}
