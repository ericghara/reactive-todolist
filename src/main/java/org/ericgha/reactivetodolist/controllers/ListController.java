package org.ericgha.reactivetodolist.controllers;

import lombok.RequiredArgsConstructor;
import org.ericgha.reactivetodolist.model.ToDoItem;
import org.ericgha.reactivetodolist.model.ToDoList;
import org.ericgha.reactivetodolist.repository.ToDoListRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@RestController
@RequestMapping("api/list")
@RequiredArgsConstructor
public class ListController {

    private final ToDoListRepository toDoListRepository;

    @PostMapping("")
    Mono<ToDoList> addList(@RequestBody ToDoList toDoList) {
        return toDoListRepository.save(toDoList);
    }

    @GetMapping("")
    @CrossOrigin
    Flux<ToDoList> getLists(@RequestParam Long userId) {
        return toDoListRepository.findByUserId(userId);
    }

    @DeleteMapping("")
    Mono<Boolean> deleteItem(@RequestBody ToDoList list) {
        Sinks.One<Boolean> sink = Sinks.one();
        toDoListRepository.deleteById(list.getListId() )
                .timeout(Duration.ofSeconds(5) )
                .doOnSuccess(s -> sink.tryEmitValue(true) )
                .doOnError(e -> sink.tryEmitValue(false) )
                .subscribe();
        return sink.asMono();
    }
}