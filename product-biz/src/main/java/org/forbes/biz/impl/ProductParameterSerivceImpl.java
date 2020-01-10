package org.forbes.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.forbes.biz.IProductParameterSerivce;
import org.forbes.comm.constant.DataColumnConstant;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.enums.ProductStausEnum;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.ParameterBatchDto;
import org.forbes.comm.model.ParameterDto;
import org.forbes.comm.model.ProSpecficDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.dal.entity.*;
import org.forbes.dal.mapper.ProductParameterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lzw
 * @date 2020/1/9 15:05
 */
@Service
public class ProductParameterSerivceImpl extends ServiceImpl<ProductParameterMapper, ProductParameter> implements IProductParameterSerivce {

    @Autowired
    ProductParameterMapper productParameterMapper;

    /***
     * batchAdd方法概述:批量添加参数
     * @param parameterBatchDto
     * @return void
     * @创建人 Tom
     * @创建时间 2020/1/10 9:32
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAdd(ParameterBatchDto parameterBatchDto) {
        List<ParameterDto> parameterDtos=parameterBatchDto.getParameterDtos();
        //判断是否包含相同规格名称
        if(ConvertUtils.isNotEmpty(parameterDtos)){
            Long classifyId=parameterBatchDto.getClassifyId();
            Map<String,List<ParameterDto>> tempMmap = parameterDtos.stream().collect(Collectors.groupingBy(ParameterDto::getName));
            tempMmap.forEach((namestr,keyList) -> {
                int attrSize = keyList.size();
                if(attrSize > 1 ){
                    throw new ForbesException(namestr);
                }
                ParameterDto parameterDto=keyList.get(0);
                ProductParameter productParameter=new ProductParameter();
                productParameter.setClassifyId(classifyId);
                productParameter.setName(parameterDto.getName());
                productParameter.setOrderSorts(parameterDto.getOrderSorts());
                productParameterMapper.insert(productParameter);
            });
        }
    }

    /***批量删除
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        productParameterMapper.delete(new QueryWrapper<ProductParameter>().in(DataColumnConstant.PARAMETER_ID, idList));
        boolean delBool =  SqlHelper.delBool(baseMapper.deleteBatchIds(idList));
        return delBool;
    }
}
