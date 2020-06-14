package com.leverx.blog.controller;

import com.leverx.blog.dto.CommentDTO;
import com.leverx.blog.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles/{articleId}/comments")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CommentController {

    private static final String ROLE_USER = "ROLE_USER";

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable int articleId) {
        return ResponseEntity.ok(commentService.getComments(articleId));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable int commentId) {
        return ResponseEntity.ok(commentService.getCommentById(commentId));
    }

    @PostMapping
    @Secured(ROLE_USER)
    public ResponseEntity<CommentDTO> addComment(@PathVariable int articleId, @RequestBody CommentDTO commentDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentService.addComment(authentication.getName(), articleId, commentDTO));
    }

    @DeleteMapping("/{commentId}")
    @Secured(ROLE_USER)
    public void deleteComment(@PathVariable int articleId, @PathVariable int commentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        commentService.deleteComment(authentication.getName(), articleId, commentId);
    }
}
