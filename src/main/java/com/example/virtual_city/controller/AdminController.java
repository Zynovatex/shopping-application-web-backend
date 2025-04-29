package com.example.virtual_city.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/overview")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<Map<String, Object>> getAdminOverview() {
        Map<String, Object> response = new HashMap<>();

        // 👤 Admin Stats (for StatCard)
        List<Map<String, String>> stats = List.of(
                Map.of("title", "Total Admins", "value", "23423"),
                Map.of("title", "Active Admins", "value", "12334"),
                Map.of("title", "Last 7 Days Activity", "value", "233"),
                Map.of("title", "Pending Invites", "value", "634")
        );

        // 📊 Pie Chart Data (for AMpieChart)
        List<Map<String, Object>> pageAccessBreakdown = List.of(
                Map.of("name", "Product", "value", 40),
                Map.of("name", "Order", "value", 30),
                Map.of("name", "Analytics", "value", 20),
                Map.of("name", "Seller", "value", 10)
        );

        // 📈 Line Chart Data (for AMlineChart)
        List<Map<String, Object>> activityTrend = List.of(
                Map.of("name", "Day 1", "total", 30, "active", 20, "pending", 10),
                Map.of("name", "Day 2", "total", 32, "active", 21, "pending", 11),
                Map.of("name", "Day 3", "total", 34, "active", 22, "pending", 12),
                Map.of("name", "Day 4", "total", 36, "active", 23, "pending", 13),
                Map.of("name", "Day 5", "total", 40, "active", 25, "pending", 15),
                Map.of("name", "Day 6", "total", 42, "active", 28, "pending", 14),
                Map.of("name", "Day 7", "total", 45, "active", 30, "pending", 15)
        );

        // 📊 Bar Chart Data (for AMbarChart)
        List<Map<String, Object>> rolesByActivity = List.of(
                Map.of("name", "Product Manager", "Actions", 5400),
                Map.of("name", "Order Admin", "Actions", 3900),
                Map.of("name", "Analytics Admin", "Actions", 2800),
                Map.of("name", "Seller Manager", "Actions", 3800),
                Map.of("name", "Support Admin", "Actions", 2300)
        );

        response.put("stats", stats);
        response.put("pageAccessBreakdown", pageAccessBreakdown);
        response.put("activityTrend", activityTrend);
        response.put("rolesByActivity", rolesByActivity);

        return ResponseEntity.ok(response);
    }


}
