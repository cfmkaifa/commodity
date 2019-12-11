package org.forbes.dal.mapper;

import org.forbes.dal.entity.AttributeValue;

public interface AttributeValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttributeValue record);

    int insertSelective(AttributeValue record);

    AttributeValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttributeValue record);

    int updateByPrimaryKey(AttributeValue record);
}