package com.wandering.trader.repositories;

import com.wandering.trader.domain.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long> {
    CustomUser findCustomUserByUsername(String username);
}
