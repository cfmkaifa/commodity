package org.forbes.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.IAttributeValueService;
import org.forbes.biz.IProductService;
import org.forbes.dal.entity.AttributeValue;
import org.forbes.dal.entity.Product;
import org.forbes.dal.mapper.AttributeValueMapper;
import org.forbes.dal.mapper.ProductMapper;

/**
 * @author lzw
 * @date 2019/12/14 17:56
 */
public class AttributeValueServiceImpl extends ServiceImpl<AttributeValueMapper, AttributeValue> implements IAttributeValueService {
}
