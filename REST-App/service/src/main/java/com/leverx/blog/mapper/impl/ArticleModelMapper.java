package com.leverx.blog.mapper.impl;

import com.leverx.blog.dto.ArticleDTO;
import com.leverx.blog.entity.Article;
import com.leverx.blog.mapper.CommonModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ArticleModelMapper extends CommonModelMapper<Article, ArticleDTO> {

    public ArticleModelMapper(ModelMapper modelMapper) {
        super(modelMapper, Article.class, ArticleDTO.class);
    }
}
