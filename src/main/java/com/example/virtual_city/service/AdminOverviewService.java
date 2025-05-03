package com.example.virtual_city.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminOverviewService {

    public Map<String, Object> getAdminOverview() {
        Map<String, Object> response = new HashMap<>();

        List<Map<String, Object>> stats = List.of(
                createStat("Total Admins", "500", "↑ 12.5%", "Compared to last year", true),
                createStat("Active Admins", "320", "↑ 8.4%", "Compared to last year", true),
                createStat("Last 7 Days Activity", "45", "↓ 2.1%", "Compared to last week", false),
                createStat("Pending Invites", "12", "", "", false)
        );
        response.put("stats", stats);

        List<Map<String, Object>> activityTrend = List.of(
                createTrend("Day 1", 10, 7, 3),
                createTrend("Day 2", 15, 10, 5),
                createTrend("Day 3", 12, 8, 4),
                createTrend("Day 4", 18, 14, 4),
                createTrend("Day 5", 20, 16, 4),
                createTrend("Day 6", 22, 18, 4),
                createTrend("Day 7", 25, 20, 5)
        );
        response.put("activityTrend", activityTrend);

        List<Map<String, Object>> rolesByActivity = List.of(
                createRoleActivity("Product Manager", 1200),
                createRoleActivity("Order Admin", 950),
                createRoleActivity("Analytics Admin", 850),
                createRoleActivity("Seller Manager", 780),
                createRoleActivity("Support Admin", 600)
        );
        response.put("rolesByActivity", rolesByActivity);

        List<Map<String, Object>> pageAccessBreakdown = List.of(
                createPageAccess("Product", 45),
                createPageAccess("Order", 30),
                createPageAccess("Analytics", 15),
                createPageAccess("Seller", 10)
        );
        response.put("pageAccessBreakdown", pageAccessBreakdown);

        return response;
    }

    private Map<String, Object> createStat(String title, String value, String statusChange, String status, boolean isPositive) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("value", value);
        map.put("statusChange", statusChange);
        map.put("status", status);
        map.put("isPositive", isPositive);
        return map;
    }

    private Map<String, Object> createTrend(String name, int total, int active, int pending) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("total", total);
        map.put("active", active);
        map.put("pending", pending);
        return map;
    }

    private Map<String, Object> createRoleActivity(String name, int actions) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("Actions", actions);
        return map;
    }

    private Map<String, Object> createPageAccess(String name, int value) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("value", value);
        return map;
    }
}
