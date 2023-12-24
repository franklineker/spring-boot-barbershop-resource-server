package com.drnavalhabarbershop.resourceserver.web.controller;

import com.drnavalhabarbershop.resourceserver.domain.Client;
import com.drnavalhabarbershop.resourceserver.domain.Comment;
import com.drnavalhabarbershop.resourceserver.mapper.CommentMapper;
import com.drnavalhabarbershop.resourceserver.service.ClientService;
import com.drnavalhabarbershop.resourceserver.service.CommentService;
import com.drnavalhabarbershop.resourceserver.web.dto.CommentRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Comment> findAll() {
        return commentService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Comment findById(@Valid @PathVariable String id) {
        return commentService.findById(id);
    }

    @GetMapping(value = "/client/id", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> findByClientId(@Valid @PathVariable String id) {
        return commentService.findByClientId(id);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Comment save(@Valid @RequestBody CommentRequest request) {

        Client client = clientService.findClientById(request.getClientID());
        Comment comment = CommentMapper.toComment(request, client);

        return commentService.save(comment);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@Valid @PathVariable String id) {
        commentService.delete(id);
    }


}
