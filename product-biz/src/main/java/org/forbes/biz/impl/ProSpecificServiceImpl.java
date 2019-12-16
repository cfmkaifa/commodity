package org.forbes.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.IProSpecificService;
import org.forbes.dal.entity.ProductSpecification;
import org.forbes.dal.mapper.ProductSpecificationMapper;
import org.springframework.stereotype.Service;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/13 9:50
 * @Version 1.0
 **/
@Service
public class ProSpecificServiceImpl extends ServiceImpl<ProductSpecificationMapper,ProductSpecification> implements IProSpecificService {

}
