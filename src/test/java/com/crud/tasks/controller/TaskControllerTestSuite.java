package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void testGetTasks() throws Exception {
        //Given
        Task task = new Task("aa", "bb");
        Task task2 = new Task("cc", "dd");

        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        tasks.add(task2);

        TaskDto taskDto = new TaskDto("aa", "bb");
        TaskDto taskDto2 = new TaskDto("cc", "dd");

        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(taskDto);
        taskDtos.add(taskDto2);

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskDtos);

        //When & Then
        mockMvc.perform(get("/v1/task").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("aa")))
                .andExpect(jsonPath("$[0].content", is("bb")))
                .andExpect(jsonPath("$[1].title", is("cc")))
                .andExpect(jsonPath("$[1].content", is("dd")));
    }

    @Test
    public void testGetTask() throws Exception {
        //Given
        Task task = new Task(1L,"aa", "bb");
        TaskDto taskDto = new TaskDto(1L,"aa", "bb");

        when(dbService.getTask(1L)).thenReturn(Optional.ofNullable(task));
        when(taskMapper.mapToTaskDto(dbService.getTask(1L).orElseThrow(RuntimeException::new))).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("aa")))
                .andExpect(jsonPath("$.content", is("bb")));
    }

    @Test
    public void testDeleteTask() throws Exception {
        //Given

        //When & Then
        mockMvc.perform(delete("/v1/task/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateTask() throws Exception {
        //Given
        Task task = new Task(1L, "aa", "bb");
        TaskDto taskDto = new TaskDto( 2L,"cc","dd");

        when(dbService.saveTask(taskMapper.mapToTask(taskDto))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto( 2L,"aa","bb");
        TaskDto updatedTaskDto = new TaskDto( 2L,"cc","dd");

        when(taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(updatedTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/task")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.title", is("cc")))
                .andExpect(jsonPath("$.content", is("dd")));
    }
}