package com.csse.eticket.repository.users;

import com.csse.eticket.model.users.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
}
