package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
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
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1","aaa",false);
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(trelloListDto);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("2","bbb", trelloLists);
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoardDto);

        //When
        List<TrelloBoard> mappedBoards = trelloMapper.mapToBoards(trelloBoards);

        //Then
        assertEquals(trelloBoards.get(0).getId(), mappedBoards.get(0).getId());
        assertEquals(trelloBoards.get(0).getName(), mappedBoards.get(0).getName());
        assertEquals(trelloBoards.get(0).getLists().get(0).getId(), mappedBoards.get(0).getLists().get(0).getId());
        assertEquals(trelloBoards.get(0).getLists().get(0).getName(), mappedBoards.get(0).getLists().get(0).getName());
        assertEquals(trelloBoards.get(0).getLists().get(0).isClosed(), mappedBoards.get(0).getLists().get(0).isClosed());

    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "aaa", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("2", "bbb", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);

        //When
        List<TrelloBoardDto> mappedBoards = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(trelloBoards.get(0).getId(), mappedBoards.get(0).getId());
        assertEquals(trelloBoards.get(0).getName(), mappedBoards.get(0).getName());
        assertEquals(trelloBoards.get(0).getLists().get(0).getId(), mappedBoards.get(0).getLists().get(0).getId());
        assertEquals(trelloBoards.get(0).getLists().get(0).getName(), mappedBoards.get(0).getLists().get(0).getName());
        assertEquals(trelloBoards.get(0).getLists().get(0).isClosed(), mappedBoards.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1","aaa",false);
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(trelloListDto);

        //When
        List<TrelloList> mappedList = trelloMapper.mapToList(trelloLists);

        //Then
        assertEquals(trelloLists.get(0).getId(), mappedList.get(0).getId());
        assertEquals(trelloLists.get(0).getName(), mappedList.get(0).getName());
        assertEquals(trelloLists.get(0).isClosed(), mappedList.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "aaa", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        //When
        List<TrelloListDto> mappedList = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(trelloLists.get(0).getId(), mappedList.get(0).getId());
        assertEquals(trelloLists.get(0).getName(), mappedList.get(0).getName());
        assertEquals(trelloLists.get(0).isClosed(), mappedList.get(0).isClosed());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("aaa", "bbb", "top", "1");

        //When
        TrelloCardDto mappedCard = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(trelloCard.getName(), mappedCard.getName());
        assertEquals(trelloCard.getDescription(), mappedCard.getDescription());
        assertEquals(trelloCard.getPos(), mappedCard.getPos());
        assertEquals(trelloCard.getListId(), mappedCard.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("aaa", "bbb", "top", "1");

        //When
        TrelloCard mappedCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloCardDto.getName(), mappedCard.getName());
        assertEquals(trelloCardDto.getDescription(), mappedCard.getDescription());
        assertEquals(trelloCardDto.getPos(), mappedCard.getPos());
        assertEquals(trelloCardDto.getListId(), mappedCard.getListId());
    }

}