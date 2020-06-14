package com.leverx.blog.service.comment.impl;

import com.leverx.blog.dto.CommentDTO;
import com.leverx.blog.entity.Article;
import com.leverx.blog.entity.Comment;
import com.leverx.blog.entity.UserEntity;
import com.leverx.blog.exception.FailedUpdateObjectException;
import com.leverx.blog.exception.NotFoundObjectException;
import com.leverx.blog.mapper.CommonModelMapper;
import com.leverx.blog.repository.ArticleRepository;
import com.leverx.blog.repository.CommentRepository;
import com.leverx.blog.repository.UserRepository;
import com.leverx.blog.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final CommonModelMapper<Comment, CommentDTO> commentModelMapper;

    @Override
    public List<CommentDTO> getComments(int articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundObjectException("Article does not exist"));
        return commentRepository.findAllByArticle(article).stream()
                .map(commentModelMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(int commentId) {
        return commentModelMapper.toDto(commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundObjectException("Comment does not exist")));
    }

    @Override
    public CommentDTO addComment(String email, int articleId, CommentDTO commentDTO) {
        Comment comment = commentModelMapper.toEntity(commentDTO);
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundObjectException("User did not find"));
        comment.setUserEntity(userEntity);
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundObjectException("Article did not find"));
        comment.setArticle(article);
        comment.setCreatedAt(getCurrentTime());
        return commentModelMapper.toDto(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(String email, int articleID, int commentId) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundObjectException("Comment does not exist"));
        Article article = articleRepository.findById(articleID)
                .orElseThrow(() -> new NotFoundObjectException("Article did not find"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundObjectException("Comment does not exist"));
        if (isAccessible(comment, userEntity, article)) {
            commentRepository.delete(comment);
        } else {
            throw new FailedUpdateObjectException("You have no rights");
        }
    }

    private boolean isAccessible(Comment comment, UserEntity userEntity, Article article) {
        UserEntity commentUser = comment.getUserEntity();
        UserEntity articleUser = article.getUserEntity();
        return userEntity.equals(commentUser) || userEntity.equals(articleUser);
    }

    private LocalDateTime getCurrentTime() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }
}
