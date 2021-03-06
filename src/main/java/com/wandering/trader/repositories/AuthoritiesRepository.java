package com.wandering.trader.repositories;

import com.wandering.trader.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authority, Long> {
    Authority findAuthorityByAuthority(String authority);
}
