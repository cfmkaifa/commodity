package org.forbes.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.ISProSpecficService;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.ClassifyAttributeDto;
import org.forbes.comm.model.ProSpecBatchDto;
import org.forbes.comm.model.ProSpecficDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.dal.entity.ProductSpecification;
import org.forbes.dal.mapper.ProductSpecificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/13 16:06
 * @Version 1.0
 **/
@Service
public class ProductSpecficServiceImpl extends ServiceImpl<ProductSpecificationMapper,ProductSpecification> implements ISProSpecficService {

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
    @Transactional(rollbackFor = Exception.class)
    public void batchAdd(ProSpecBatchDto proSpecBatchDto) {
        List<ProSpecficDto> proSpecficDtos=proSpecBatchDto.getProSpecficDtos();
        //判断是否包含相同规格名称
        if(proSpecficDtos.size()>0){
            Long classifyId=proSpecBatchDto.getClassifyId();
            Map<String,List<ProSpecficDto>> tempMmap = proSpecficDtos.stream().collect(Collectors.groupingBy(ProSpecficDto::getName));
            tempMmap.forEach((namestr,keyList) -> {
                int attrSize = keyList.size();
                if(attrSize > 0 ){
                    throw new ForbesException(namestr);
                }
                ProductSpecification productSpecification=new ProductSpecification();
                productSpecification.setClassifyId(classifyId);
                productSpecification.setName(keyList.get(0).getName());
                productSpecification.setState(keyList.get(0).getState());
                productSpecification.setOrderSorts(keyList.get(0).getOrderSorts());
                proSpecificMapper.insert(productSpecification);
            });
        }
    }
}
