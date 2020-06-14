package com.leverx.blog.sort.impl;

import com.leverx.blog.entity.SearchCriteria;
import com.leverx.blog.entity.enumeration.OrderBy;
import com.leverx.blog.sort.PageableSort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PageableSortImpl implements PageableSort {

    private static final int DEFAULT_PAGE_VALUE = 0;
    private static final int DEFAULT_PAGE_SIZE = 5;
    private static final int INITIAL_VALUE = 0;
    private static final String DEFAULT_SORT = "id";

    @Override
    public Pageable toSort(SearchCriteria criteria) {
        int page = DEFAULT_PAGE_VALUE;
        if(criteria.getSkip() > INITIAL_VALUE) {
            page = criteria.getSkip();
        }
        int size = DEFAULT_PAGE_SIZE;
        if(criteria.getLimit() > INITIAL_VALUE) {
            size = criteria.getLimit();
        }
        Sort sort = Sort.by(DEFAULT_SORT).ascending();
        if(Objects.nonNull(criteria.getSort())) {
            sort = Sort.by(criteria.getSort());
            sort = setOrderBy(sort, criteria.getOrder());
        }
        return PageRequest.of(page, size, sort);
    }

    private Sort setOrderBy(Sort sort, OrderBy orderBy) {
        return orderBy.isAsc() ? sort.ascending() : sort.descending();
    }
}
