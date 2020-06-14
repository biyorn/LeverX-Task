package com.leverx.blog.entity.converter;

import com.leverx.blog.entity.enumeration.ArticleStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ArticleStatusJpaConverter implements AttributeConverter<ArticleStatus, String> {

    @Override
    public String convertToDatabaseColumn(ArticleStatus attribute) {
        if(attribute == null) {
            return null;
        }
        return attribute.getName();
    }

    @Override
    public ArticleStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return ArticleStatus.valueOf(dbData.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
