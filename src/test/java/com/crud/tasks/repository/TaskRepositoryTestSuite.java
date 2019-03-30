package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskRepositoryTestSuite {
    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    @Test
    public void testFindAll() {
        //Given
        long sizeOfDatabaseBeforeTest = taskRepository.count();

        Task task = new Task("my_first_task", "content");
        Task task2 = new Task("my_second_task", "content2");

        taskRepository.save(task);
        taskRepository.save(task2);

        //When
        List<Task> tasks = taskRepository.findAll();

        //Then
        assertEquals(2L, tasks.size() - sizeOfDatabaseBeforeTest);
        assertTrue(tasks.contains(task));
        assertTrue(tasks.contains(task2));
    }

    @Transactional
    @Test
    public void testSave() {
        //Given
        long sizeOfDatabaseBeforeTest = taskRepository.count();

        Task task = new Task("my_first_task", "content");
        Task task2 = new Task("my_second_task", "content2");

        //When
        taskRepository.save(task);
        taskRepository.save(task2);

        //Then
        assertEquals(2L, taskRepository.count() - sizeOfDatabaseBeforeTest);
    }

    @Transactional
    @Test
    public void testFindById() {
        //Given
        Task task = new Task("my_first_task", "content");
        Task task2 = new Task("my_second_task", "content2");

        taskRepository.save(task);
        taskRepository.save(task2);

        //When
        Optional<Task> testTask = taskRepository.findById(task2.getId());

        //Then
        assertTrue(testTask.isPresent());
        assertEquals(Optional.of(task2), testTask);
        assertEquals("my_second_task", testTask.get().getTitle());
        assertEquals("content2", testTask.get().getContent());

    }

    @Transactional
    @Test
    public void testDeleteById() {
        //Given
        long sizeOfDatabaseBeforeTest = taskRepository.count();

        Task task = new Task("my_first_task", "content");
        Task task2 = new Task("my_second_task", "content2");

        taskRepository.save(task);
        taskRepository.save(task2);

        //When
        taskRepository.deleteById(task.getId());

        //Then
        assertEquals(1L, taskRepository.count() - sizeOfDatabaseBeforeTest);
        assertFalse(taskRepository.existsById(task.getId()));
    }
}