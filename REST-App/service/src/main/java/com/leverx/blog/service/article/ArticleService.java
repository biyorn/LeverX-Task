package com.leverx.blog.service.article;

import com.leverx.blog.dto.ArticleDTO;
import com.leverx.blog.dto.SearchCriteriaDTO;

import java.util.List;

public interface ArticleService {

    List<ArticleDTO> getArticles(SearchCriteriaDTO searchCriteriaDTO);

    ArticleDTO createArticle(String email, ArticleDTO articleDTO);

    ArticleDTO updateArticle(String email, ArticleDTO articleDTO);

    List<ArticleDTO> getOwnArticles(String email);

    void deleteArticle(String email, int id);
}
