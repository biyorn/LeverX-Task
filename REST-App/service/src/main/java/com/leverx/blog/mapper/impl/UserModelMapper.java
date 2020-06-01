package com.leverx.blog.mapper.impl;

import com.leverx.blog.dto.UserEntityDTO;
import com.leverx.blog.entity.UserEntity;
import com.leverx.blog.mapper.CommonModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserModelMapper extends CommonModelMapper<UserEntity, UserEntityDTO> {

    public UserModelMapper(ModelMapper modelMapper) {
        super(modelMapper, UserEntity.class, UserEntityDTO.class);
    }
}
