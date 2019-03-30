package com.crud.tasks.trello.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloConfigTestSuite {
    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    public void testGetters() {
        //Given

        //When
        String apiEndpoint = trelloConfig.getTrelloApiEndpoint();
        String appKey = trelloConfig.getTrelloAppKey();
        String token = trelloConfig.getTrelloToken();
        String username = trelloConfig.getTrelloUsername();

        //Then
        assertEquals("https://api.trello.com/1", apiEndpoint);
        assertEquals("6ac3fee129f380859829d1fdad3c4bf9", appKey);
        assertEquals("fbff0c2f6ead5fc2970a13949c95b3fd0946b273c7c111ae6eae8fba858a853f", token);
        assertEquals("ukaszmazurkiewicz4", username);
    }

}