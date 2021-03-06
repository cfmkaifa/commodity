package org.forbes.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.ISpecificationValueService;
import org.forbes.dal.entity.SpecificationValue;
import org.forbes.dal.mapper.SpecificationValueMapper;
import org.springframework.stereotype.Service;

/**
 * @author lzw
 * @date 2019/12/14 17:56
 */
@Service
public class SpecificationValueServiceImpl extends ServiceImpl<SpecificationValueMapper, SpecificationValue> implements ISpecificationValueService {
}
