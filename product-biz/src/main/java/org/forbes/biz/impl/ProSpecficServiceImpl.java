package org.forbes.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.ISProSpecficService;
import org.forbes.dal.entity.ProductSpecification;
import org.forbes.dal.mapper.ProductSpecificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/13 16:06
 * @Version 1.0
 **/
@Service
public class ProSpecficServiceImpl extends ServiceImpl<ProductSpecificationMapper,ProductSpecification> implements ISProSpecficService {

    @Autowired
    private ProductSpecificationMapper proSpecificMapper;

}
