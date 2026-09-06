package com.siemens.selfservice.application.usecase;

import com.siemens.selfservice.domain.exception.TicketNotFoundException;
import com.siemens.selfservice.domain.model.Comment;
import com.siemens.selfservice.domain.port.in.AddCommentUseCase;
import com.siemens.selfservice.domain.port.out.CommentRepositoryPort;
import com.siemens.selfservice.domain.port.out.TicketRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddCommentService implements AddCommentUseCase {

    private final TicketRepositoryPort ticketRepository;
    private final CommentRepositoryPort commentRepository;

    public AddCommentService(TicketRepositoryPort ticketRepository, CommentRepositoryPort commentRepository) {
        this.ticketRepository = ticketRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment addComment(UUID ticketId, String author, String body) {
        if (!ticketRepository.existsById(ticketId)) {
            throw new TicketNotFoundException(ticketId);
        }
        return commentRepository.save(Comment.createNew(ticketId, author, body));
    }
}
