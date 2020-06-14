package com.leverx.blog.specification.factory;

import com.leverx.blog.entity.SearchCriteria;
import com.leverx.blog.specification.ArticleSpecification;
import org.springframework.stereotype.Component;

@Component
public class SpecificationFactory {

    public ArticleSpecification getArticleSpecification(SearchCriteria criteria) {
        return new ArticleSpecification(criteria);
    }
}
