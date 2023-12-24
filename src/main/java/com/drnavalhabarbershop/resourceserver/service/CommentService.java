package com.drnavalhabarbershop.resourceserver.service;

import com.drnavalhabarbershop.resourceserver.domain.Comment;
import com.drnavalhabarbershop.resourceserver.mapper.CommentMapper;
import com.drnavalhabarbershop.resourceserver.repository.CommentRepository;
import com.drnavalhabarbershop.resourceserver.web.dto.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment findById(String id) {
        return commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found."));
    }

    public List<Comment> findByClientId(String clienteId) {

        List<Comment> comments = findAll();
        comments.stream().filter(comment -> {
            return comment.getClient().getId() == clienteId;
        });

        return comments;
    }

    public void delete(String id) {
        checkClientExists(id);
        commentRepository.deleteById(id);
    }

    private void checkClientExists(String id) {
        if(commentRepository.findById(id).isEmpty()){
            throw new RuntimeException("Client not found.");
        }
    }
}
