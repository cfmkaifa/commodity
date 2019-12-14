package org.forbes.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.ISProSpecficService;
import org.forbes.comm.model.ClassAttrDto;
import org.forbes.comm.model.ProSpecBatchDto;
import org.forbes.comm.model.ProSpecficDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.dal.entity.ClassifyAttribute;
import org.forbes.dal.entity.ProductSpecification;
import org.forbes.dal.mapper.ProductSpecificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /***
     * 方法概述:批量添加规格
     * @param
     * @return
     * @创建人 xfx
     * @创建时间 2019/12/14
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    public void batchAdd(ProSpecBatchDto proSpecBatchDto) {
        List<ProSpecficDto> proSpecficDtos=proSpecBatchDto.getProSpecficDtos();
        if(ConvertUtils.isEmpty(proSpecficDtos)){
            Long classifyId=proSpecBatchDto.getClassifyId();
            proSpecficDtos.stream().forEach(temp -> {
                ProductSpecification productSpecification=new ProductSpecification();
                productSpecification.setClassifyId(classifyId);
                productSpecification.setName(temp.getName());
                productSpecification.setState(temp.getState());
                productSpecification.setOrderSorts(temp.getOrderSorts());
                proSpecificMapper.insert(productSpecification);
            });
        }

    }
}
