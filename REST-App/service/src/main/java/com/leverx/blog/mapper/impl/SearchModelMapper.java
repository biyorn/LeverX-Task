package com.leverx.blog.mapper.impl;

import com.leverx.blog.dto.SearchCriteriaDTO;
import com.leverx.blog.entity.SearchCriteria;
import com.leverx.blog.mapper.CommonModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SearchModelMapper extends CommonModelMapper<SearchCriteria, SearchCriteriaDTO> {

    public SearchModelMapper(ModelMapper modelMapper) {
        super(modelMapper, SearchCriteria.class, SearchCriteriaDTO.class);
    }
}
