package org.forbes.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.IProductAttachService;
import org.forbes.dal.entity.ProductAttach;
import org.forbes.dal.mapper.ProductAttachMapper;
import org.springframework.stereotype.Service;

/**
 * @author lzw
 * @date 2019/12/14 17:57
 */
@Service
public class ProductAttachServiceImpl extends ServiceImpl<ProductAttachMapper, ProductAttach> implements IProductAttachService {
}
