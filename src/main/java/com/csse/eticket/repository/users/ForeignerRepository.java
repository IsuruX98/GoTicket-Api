package com.csse.eticket.repository.users;

import com.csse.eticket.model.users.Foreigner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForeignerRepository extends JpaRepository<Foreigner, Integer> {
}
