package org.forbes.dal.mapper;

import org.forbes.dal.entity.SpecificationValue;

public interface SpecificationValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpecificationValue record);

    int insertSelective(SpecificationValue record);

    SpecificationValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpecificationValue record);

    int updateByPrimaryKey(SpecificationValue record);
}