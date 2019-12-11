package org.forbes.dal.mapper;

import org.forbes.dal.entity.ClassifyAttribute;

public interface ClassifyAttributeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassifyAttribute record);

    int insertSelective(ClassifyAttribute record);

    ClassifyAttribute selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClassifyAttribute record);

    int updateByPrimaryKey(ClassifyAttribute record);
}