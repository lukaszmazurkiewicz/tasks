package com.crud.tasks.service;

import com.crud.tasks.domain.AttachmentByType;
import com.crud.tasks.domain.BadgesDto;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Trello;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Test
    public void shouldFetchEmptyTrelloBoards() {
        //Given

        when(trelloClient.getTrelloBoards()).thenReturn(new ArrayList<>());
        //When
        List<TrelloBoardDto> testBoards = trelloService.fetchTrelloBoards();
        //Then
        assertNotNull(testBoards);
        assertEquals(0L, testBoards.size());

    }

    @Test
    public void shouldCreateNewCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test task", "Test Description", "top", "test_id");

        Trello trello = new Trello(2,10);
        AttachmentByType attachmentByType = new AttachmentByType(trello);
        BadgesDto badgesDto = new BadgesDto(5, attachmentByType);

        CreatedTrelloCardDto mockedCreatedTrelloCardDto = new CreatedTrelloCardDto("1", "Test task", "http://test.com", badgesDto);

        when(trelloClient.createNewCard(any(TrelloCardDto.class))).thenReturn(mockedCreatedTrelloCardDto);
        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloService.createdTrelloCard(trelloCardDto);

        //Then
        assertEquals(mockedCreatedTrelloCardDto.getId(), createdTrelloCardDto.getId());
        assertEquals(mockedCreatedTrelloCardDto.getName(), createdTrelloCardDto.getName());
        assertEquals(mockedCreatedTrelloCardDto.getShortUrl(), createdTrelloCardDto.getShortUrl());
        assertEquals(mockedCreatedTrelloCardDto.getBadgesDto().getVotes(), createdTrelloCardDto.getBadgesDto().getVotes());
        assertEquals(mockedCreatedTrelloCardDto.getBadgesDto().getAttachmentByType().getTrello().getBoard(),
                        createdTrelloCardDto.getBadgesDto().getAttachmentByType().getTrello().getBoard());
        assertEquals(mockedCreatedTrelloCardDto.getBadgesDto().getAttachmentByType().getTrello().getCard(),
                        createdTrelloCardDto.getBadgesDto().getAttachmentByType().getTrello().getCard());
    }

}