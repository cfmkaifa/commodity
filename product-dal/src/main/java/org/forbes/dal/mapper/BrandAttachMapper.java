package org.forbes.dal.mapper;

import org.forbes.dal.entity.BrandAttach;

public interface BrandAttachMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BrandAttach record);

    int insertSelective(BrandAttach record);

    BrandAttach selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BrandAttach record);

    int updateByPrimaryKey(BrandAttach record);
}