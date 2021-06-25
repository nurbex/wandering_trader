package com.wandering.trader.services;

import com.wandering.trader.domain.Task;
import com.wandering.trader.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public Optional<Task> findTaskById(Long id){
        return taskRepository.findById(id);
    }

    public void saveOrUpdateTask(Task task){
        taskRepository.save(task);
    }

    public List<Task> getTaskListByStatus(Task.STATUS status){
        return taskRepository.findAllByStatus(status);
    }
}
