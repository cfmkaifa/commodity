package org.forbes.dal.mapper;

import org.forbes.dal.entity.ProductSpecification;

public interface ProductSpecificationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductSpecification record);

    int insertSelective(ProductSpecification record);

    ProductSpecification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductSpecification record);

    int updateByPrimaryKey(ProductSpecification record);
}