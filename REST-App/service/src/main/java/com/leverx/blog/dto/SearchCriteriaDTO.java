package com.leverx.blog.dto;

import com.leverx.blog.entity.enumeration.OrderBy;
import lombok.Data;

@Data
public class SearchCriteriaDTO {

    private int skip;
    private int limit;
    private String title;
    private int author;
    private String sort;
    private OrderBy order;
}
