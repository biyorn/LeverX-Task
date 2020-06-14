package com.leverx.blog.repository;

import com.leverx.blog.entity.Article;
import com.leverx.blog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer>,
        JpaSpecificationExecutor<Article> {


    List<Article> findByUserEntity(UserEntity userEntity);
}
