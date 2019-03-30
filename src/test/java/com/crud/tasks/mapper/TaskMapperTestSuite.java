package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "my_task", "my_content");

        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(taskDto.getId(), mappedTask.getId());
        assertEquals(taskDto.getTitle(), mappedTask.getTitle());
        assertEquals(taskDto.getContent(), mappedTask.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(2L, "my_second_task", "my_content_2");

        //When
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(task.getId(), mappedTaskDto.getId());
        assertEquals(task.getTitle(), mappedTaskDto.getTitle());
        assertEquals(task.getContent(), mappedTaskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task = new Task(1L, "my_task", "my_content");
        Task task2 = new Task(2L, "my_second_task", "my_content_2");

        List<Task> tasks = new ArrayList<>();

        tasks.add(task);
        tasks.add(task2);

        //When
        List<TaskDto> mappedTasks = taskMapper.mapToTaskDtoList(tasks);

        //Then
        assertNotNull(mappedTasks);
        assertEquals(2, mappedTasks.size());

        mappedTasks.forEach(mappedTaskDto -> {
                    assertEquals(tasks.get(mappedTasks.indexOf(mappedTaskDto)).getId(), mappedTaskDto.getId());
                    assertEquals(tasks.get(mappedTasks.indexOf(mappedTaskDto)).getTitle(), mappedTaskDto.getTitle());
                    assertEquals(tasks.get(mappedTasks.indexOf(mappedTaskDto)).getContent(), mappedTaskDto.getContent());
                });
    }
}