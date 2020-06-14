package com.leverx.blog.specification;

import com.leverx.blog.entity.Article;
import com.leverx.blog.entity.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ArticleSpecification implements Specification<Article> {

    private static final int INITIAL_VALUE = 0;
    private static final String TITLE = "title";
    private static final String USER = "userEntity";
    private final SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        String title = criteria.getTitle();
        if(Objects.nonNull(title)) {
            predicates.add(builder.equal(root.get(TITLE), title));
        }
        int authorId = criteria.getAuthor();
        if(authorId > INITIAL_VALUE) {
            predicates.add(builder.equal(root.get(USER), authorId));
        }
        query.where(predicates.toArray(new Predicate[]{}));
        return query.getRestriction();
    }
}
