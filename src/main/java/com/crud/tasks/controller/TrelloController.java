package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {
    private final TrelloClient trelloClient;

    @Autowired
    public TrelloController(TrelloClient trelloClient) {
        this.trelloClient = trelloClient;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        Optional<TrelloBoardDto> result = trelloBoards.stream()
                .filter(trelloBoardDto -> !trelloBoardDto.getId().isEmpty() && !trelloBoardDto.getName().isEmpty())
                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
                .findAny();

        System.out.println(result.get().getId() + " " + result.get().getName());
    }
}