package com.leverx.blog.mapper.impl;

import com.leverx.blog.dto.CommentDTO;
import com.leverx.blog.entity.Comment;
import com.leverx.blog.mapper.CommonModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentModelMapper extends CommonModelMapper<Comment, CommentDTO> {

    public CommentModelMapper(ModelMapper modelMapper) {
        super(modelMapper, Comment.class, CommentDTO.class);
    }
}
