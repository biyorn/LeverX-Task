package com.leverx.blog.sort;

import com.leverx.blog.entity.SearchCriteria;
import org.springframework.data.domain.Pageable;


public interface PageableSort {

    Pageable toSort(SearchCriteria criteria);
}
