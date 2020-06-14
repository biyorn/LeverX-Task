package com.leverx.blog.service.comment;

import com.leverx.blog.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getComments(int articleId);

    CommentDTO getCommentById(int commentId);

    CommentDTO addComment(String email, int articleId, CommentDTO commentDTO);

    void deleteComment(String email, int articleID, int commentId);
}
