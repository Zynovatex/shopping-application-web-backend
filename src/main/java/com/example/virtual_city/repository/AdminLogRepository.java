// 📄 File: AdminLogRepository.java
package com.example.virtual_city.repository;

import com.example.virtual_city.model.AdminLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdminLogRepository extends JpaRepository<AdminLog, Long> {
    // ✅ Add this line to fix the error
    List<AdminLog> findAllByOrderByTimestampDesc();
}
