package org.forbes.dal.mapper;

import org.forbes.dal.entity.ProductAttach;

public interface ProductAttachMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductAttach record);

    int insertSelective(ProductAttach record);

    ProductAttach selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductAttach record);

    int updateByPrimaryKey(ProductAttach record);
}