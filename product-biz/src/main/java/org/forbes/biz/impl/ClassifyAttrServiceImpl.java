package org.forbes.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.ClassifyAttrService;
import org.forbes.dal.entity.ClassifyAttribute;
import org.forbes.dal.mapper.ClassifyAttributeMapper;
import org.springframework.stereotype.Service;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/12 18:22
 * @Version 1.0
 **/
@Service
public class ClassifyAttrServiceImpl extends ServiceImpl<ClassifyAttributeMapper,ClassifyAttribute> implements ClassifyAttrService{
}
