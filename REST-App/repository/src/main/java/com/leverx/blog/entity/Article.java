package com.leverx.blog.entity;

import com.leverx.blog.entity.converter.ArticleStatusJpaConverter;
import com.leverx.blog.entity.enumeration.ArticleStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "text", nullable = false)
    private String text;
    @Convert(converter = ArticleStatusJpaConverter.class)
    @Column(name = "status", nullable = false)
    private ArticleStatus status;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @ManyToMany
    @JoinTable(name = "article_comment",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private List<Comment> comments;

}
