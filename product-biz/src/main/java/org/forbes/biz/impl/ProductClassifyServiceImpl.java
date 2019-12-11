package org.forbes.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.IProductClassifyService;
import org.forbes.dal.entity.ProductClassify;
import org.forbes.dal.mapper.ProductClassifyMapper;
import org.forbes.dal.mapper.ext.ProductClassifyExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/11 11:55
 * @Version 1.0
 **/
@Service
public class ProductClassifyServiceImpl extends ServiceImpl<ProductClassifyMapper, ProductClassify> implements IProductClassifyService{

    @Autowired
    private ProductClassifyExtMapper productClassifyExtMapper;


}
