package com.leverx.blog.repository;

import com.leverx.blog.entity.Article;
import com.leverx.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByArticle(Article article);
}
