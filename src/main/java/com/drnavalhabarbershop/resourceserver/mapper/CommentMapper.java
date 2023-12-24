package com.drnavalhabarbershop.resourceserver.mapper;

import com.drnavalhabarbershop.resourceserver.domain.Client;
import com.drnavalhabarbershop.resourceserver.domain.Comment;
import com.drnavalhabarbershop.resourceserver.web.dto.CommentRequest;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class CommentMapper {

    public static Comment toComment(CommentRequest request, Client client) {

        return request != null ? Comment
                .builder()
                .client(client)
                .content(request.getContent())
                .createdDate(LocalDateTime.now())
                .build() : null;
    }
}
