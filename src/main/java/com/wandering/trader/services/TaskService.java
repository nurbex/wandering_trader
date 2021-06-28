package com.wandering.trader.services;

import com.wandering.trader.domain.CustomUser;
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

    public void removeTaskById(Long id){
        taskRepository.deleteById(id);
    }

    public List<Task> getTaskListByProjectIdAndOwnerAndStatus(Long id, CustomUser owner, Task.STATUS status){
        return taskRepository.findAllByProjectIdAndOwnerAndStatus(id, owner,status);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }
}
