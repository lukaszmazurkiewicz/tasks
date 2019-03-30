package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {
    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {
        //Given
        Task task = new Task("my_task", "my_content");
        Task task2 = new Task("my_second_task", "content");

        List<Task> tasks = new ArrayList<>();

        tasks.add(task);
        tasks.add(task2);

        when(taskRepository.findAll()).thenReturn(tasks);
        //When
        List<Task> testTasks = dbService.getAllTasks();

        //Then
        assertEquals(2, testTasks.size());
        assertTrue(testTasks.contains(task));
        assertTrue(testTasks.contains(task2));
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task("my_task", "my_content");
        Task task2 = new Task("my_second_task", "content");
        Task task3 = new Task("my_third_task", "content3");

        when(taskRepository.save(any(Task.class))).thenReturn(task);
        //When
        Task testTask = dbService.saveTask(task2);
        Task testTask2 = dbService.saveTask(task3);

        //Then
        assertEquals(task.getId(), testTask.getId());
        assertEquals(task.getTitle(), testTask.getTitle());
        assertEquals(task.getContent(), testTask.getContent());

        assertEquals(task.getId(), testTask2.getId());
        assertEquals(task.getTitle(), testTask2.getTitle());
        assertEquals(task.getContent(), testTask2.getContent());
    }

    @Test
    public void testGetTask() {
        //Given
        Task task = new Task("my_task", "my_content");

        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));

        //When
        Optional<Task> testTask = dbService.getTask(1L);

        //Then
        assertEquals(task.getId(), testTask.get().getId());
        assertEquals(task.getTitle(), testTask.get().getTitle());
        assertEquals(task.getContent(), testTask.get().getContent());
    }
}