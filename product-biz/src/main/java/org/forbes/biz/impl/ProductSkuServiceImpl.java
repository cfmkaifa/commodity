package org.forbes.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.IProductSkuService;
import org.forbes.dal.entity.ProductSku;
import org.forbes.dal.mapper.ProductSkuMapper;
import org.springframework.stereotype.Service;

/**
 * @author lzw
 * @date 2019/12/18 11:03
 */
@Service
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSku> implements IProductSkuService {
}
