package org.forbes.dal.mapper;

import org.forbes.dal.entity.ProductBrand;

public interface ProductBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductBrand record);

    int insertSelective(ProductBrand record);

    ProductBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductBrand record);

    int updateByPrimaryKey(ProductBrand record);
}