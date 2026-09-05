package com.siemens.selfservice.domain.port.in;

import com.siemens.selfservice.domain.model.Comment;
import java.util.UUID;

public interface AddCommentUseCase {
    Comment addComment(UUID ticketId, String author, String body);
}
