package com.wandering.trader.repositories;

import com.wandering.trader.domain.CustomUser;
import com.wandering.trader.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByProjectIdAndOwnerAndStatus(Long id, CustomUser owner, Task.STATUS status);
}
