package com.leverx.blog.service.article.impl;

import com.leverx.blog.dto.ArticleDTO;
import com.leverx.blog.entity.Article;
import com.leverx.blog.entity.enumeration.ArticleStatus;
import com.leverx.blog.mapper.CommonModelMapper;
import com.leverx.blog.repository.ArticleRepository;
import com.leverx.blog.repository.UserRepository;
import com.leverx.blog.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ArticleServiceImpl implements ArticleService {

    private final CommonModelMapper<Article, ArticleDTO> articleModelMapper;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Override
    public List<ArticleDTO> getAllPublicArticles() {
        List<Article> articles = articleRepository.findAllByStatus(ArticleStatus.PUBLIC);
        return articleRepository.findAllByStatus(ArticleStatus.PUBLIC).stream()
                .map(articleModelMapper::toDto)
                .collect(Collectors.toList());
    }

    // Maybe, need to throw exception if user did not find!
    @Override
    @Transactional
    public ArticleDTO createArticle(String username, ArticleDTO articleDTO) {
        Article article = articleModelMapper.toEntity(articleDTO);
        userRepository.findByEmail(username).ifPresent(article::setUserEntity);
        LocalDateTime time = getCurrentTime();
        article.setCreatedAt(time);
        article.setUpdatedAt(time);
        return articleModelMapper.toDto(articleRepository.save(article));
    }

    private LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

}
