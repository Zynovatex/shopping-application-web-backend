package com.example.virtual_city.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminOverviewService {

    public Map<String, Object> getAdminOverview() {
        Map<String, Object> response = new HashMap<>();

        // 1. Basic stats
        List<Map<String, String>> stats = List.of(
                createStat("Total Admins", "23423"),
                createStat("Active Admins", "12334"),
                createStat("Last 7 Days Activity", "233"),
                createStat("Pending Invites", "634")
        );
        response.put("stats", stats);

        // 2. Admin activity trend (for line chart)
        List<Map<String, Integer>> activityTrend = List.of(
                createTrend("Day 1", 30, 20, 10),
                createTrend("Day 2", 32, 21, 11),
                createTrend("Day 3", 34, 22, 12),
                createTrend("Day 4", 36, 23, 13),
                createTrend("Day 5", 40, 25, 15),
                createTrend("Day 6", 42, 28, 14),
                createTrend("Day 7", 45, 30, 15)
        );
        response.put("activityTrend", activityTrend);

        // 3. Roles by activity (for bar chart)
        List<Map<String, Object>> rolesByActivity = List.of(
                createRoleActivity("Product Manager", 5400),
                createRoleActivity("Order Admin", 3900),
                createRoleActivity("Analytics Admin", 2800),
                createRoleActivity("Seller Manager", 3800),
                createRoleActivity("Support Admin", 2300)
        );
        response.put("rolesByActivity", rolesByActivity);

        // 4. Page access breakdown (for pie chart)
        List<Map<String, Object>> pageAccessBreakdown = List.of(
                createPageAccess("Product", 40),
                createPageAccess("Order", 30),
                createPageAccess("Analytics", 20),
                createPageAccess("Seller", 10)
        );
        response.put("pageAccessBreakdown", pageAccessBreakdown);

        return response;
    }

    // Helper methods for cleaner creation
    private Map<String, String> createStat(String title, String value) {
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("value", value);
        return map;
    }

    private Map<String, Integer> createTrend(String day, int total, int active, int pending) {
        Map<String, Integer> map = new HashMap<>();
        map.put("total", total);
        map.put("active", active);
        map.put("pending", pending);
        map.put("name", day.hashCode()); // Just a trick to keep unique key
        return map;
    }

    private Map<String, Object> createRoleActivity(String role, int actions) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", role);
        map.put("Actions", actions);
        return map;
    }

    private Map<String, Object> createPageAccess(String page, int percentage) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", page);
        map.put("value", percentage);
        return map;
    }
}
