package com.leverx.blog.service.article;

import com.leverx.blog.dto.ArticleDTO;

import java.util.List;

public interface ArticleService {

    List<ArticleDTO> getAllPublicArticles();

    ArticleDTO createArticle(String username, ArticleDTO articleDTO);
}
