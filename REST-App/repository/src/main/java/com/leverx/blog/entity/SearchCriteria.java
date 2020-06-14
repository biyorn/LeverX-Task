package com.leverx.blog.entity;

import com.leverx.blog.entity.enumeration.OrderBy;
import lombok.Data;

@Data
public class SearchCriteria {

    private int skip;
    private int limit;
    private String title;
    private int author;
    private String sort;
    private OrderBy order;

}
