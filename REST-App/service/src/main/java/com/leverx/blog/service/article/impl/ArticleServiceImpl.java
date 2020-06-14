package com.leverx.blog.service.article.impl;

import com.leverx.blog.dto.ArticleDTO;
import com.leverx.blog.dto.SearchCriteriaDTO;
import com.leverx.blog.entity.Article;
import com.leverx.blog.entity.SearchCriteria;
import com.leverx.blog.entity.UserEntity;
import com.leverx.blog.entity.enumeration.ArticleStatus;
import com.leverx.blog.exception.FailedDeleteObjectException;
import com.leverx.blog.exception.FailedUpdateObjectException;
import com.leverx.blog.exception.NotFoundObjectException;
import com.leverx.blog.mapper.CommonModelMapper;
import com.leverx.blog.repository.ArticleRepository;
import com.leverx.blog.repository.UserRepository;
import com.leverx.blog.service.article.ArticleService;
import com.leverx.blog.sort.PageableSort;
import com.leverx.blog.specification.factory.SpecificationFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ArticleServiceImpl implements ArticleService {

    private final CommonModelMapper<Article, ArticleDTO> articleModelMapper;
    private final CommonModelMapper<SearchCriteria, SearchCriteriaDTO> searchModelMapper;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final SpecificationFactory specificationFactory;
    private final PageableSort pageableSort;

    @Override
    public List<ArticleDTO> getArticles(SearchCriteriaDTO searchCriteriaDTO) {
        SearchCriteria criteria = searchModelMapper.toEntity(searchCriteriaDTO);
        return articleRepository.findAll(specificationFactory.getArticleSpecification(criteria),
                pageableSort.toSort(criteria))
                .stream()
                .map(articleModelMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ArticleDTO createArticle(String username, ArticleDTO articleDTO) {
        Article article = articleModelMapper.toEntity(articleDTO);
        userRepository.findByEmail(username).ifPresentOrElse(article::setUserEntity,
                () -> {
                    throw new NotFoundObjectException("User does not exist");
                });
        LocalDateTime time = getCurrentTime();
        article.setCreatedAt(time);
        article.setUpdatedAt(time);
        return articleModelMapper.toDto(articleRepository.save(article));
    }

    @Override
    @Transactional
    public ArticleDTO updateArticle(String email, ArticleDTO articleDTO) {
        Article article = articleModelMapper.toEntity(articleDTO);
        UserEntity userEntity = getUserByEmail(email);
        Article dbArticle = articleRepository.findById(article.getId())
                .orElseThrow(() -> new FailedUpdateObjectException("Something went wrong"));
        if (dbArticle.getUserEntity().equals(userEntity)) {
            article = updateArticleFields(dbArticle, article);
            articleRepository.save(article);
        }
        return articleModelMapper.toDto(article);
    }

    @Override
    @Transactional
    public List<ArticleDTO> getOwnArticles(String email) {
        UserEntity userEntity = getUserByEmail(email);
        return articleRepository.findByUserEntity(userEntity).stream()
                .map(articleModelMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteArticle(String email, int id) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundObjectException("User does not exist"));
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NotFoundObjectException("Article did not find"));
        if (article.getUserEntity().equals(userEntity)) {
            articleRepository.delete(article);
        } else {
            throw new FailedDeleteObjectException("User does not have such an article");
        }
    }

    private UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundObjectException("User does not exist"));
    }

    private Article updateArticleFields(Article current, Article updated) {
        String title = updated.getTitle();
        if (Objects.nonNull(title)) {
            current.setTitle(title);
        }
        String text = updated.getText();
        if (Objects.nonNull(text)) {
            current.setText(text);
        }
        ArticleStatus status = updated.getStatus();
        if (Objects.nonNull(status)) {
            current.setStatus(status);
        }
        current.setUpdatedAt(getCurrentTime());
        return current;
    }

    private LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
}
