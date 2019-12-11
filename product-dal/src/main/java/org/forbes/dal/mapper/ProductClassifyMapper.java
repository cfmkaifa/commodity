package org.forbes.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.forbes.dal.entity.ProductClassify;

public interface ProductClassifyMapper extends BaseMapper<ProductClassify> {
    int deleteByPrimaryKey(Long id);

    int insert(ProductClassify record);

    int insertSelective(ProductClassify record);

    ProductClassify selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductClassify record);

    int updateByPrimaryKey(ProductClassify record);
}