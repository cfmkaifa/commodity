package org.forbes.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.IProductClassifyService;
import org.forbes.comm.enums.ClassifyStausEnum;
import org.forbes.comm.enums.YesNoEnum;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.ClassifyAttributeDto;
import org.forbes.comm.model.ProSpecficDto;
import org.forbes.comm.model.ProductClassifyDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.dal.entity.ClassifyAttribute;
import org.forbes.dal.entity.ProductClassify;
import org.forbes.dal.entity.ProductSpecification;
import org.forbes.dal.mapper.ClassifyAttributeMapper;
import org.forbes.dal.mapper.ProductClassifyMapper;
import org.forbes.dal.mapper.ProductSpecificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/11 11:55
 * @Version 1.0
 **/
@Service
public class ProductClassifyServiceImpl extends ServiceImpl<ProductClassifyMapper, ProductClassify> implements IProductClassifyService {



    @Autowired
    private ClassifyAttributeMapper classifyAttributeMapper;

    @Autowired
    private ProductSpecificationMapper productSpecificationMapper;
    /***
     * 方法概述: 添加商品分类+添加分类属性
     * @param productClassifyDto
     * @return
     * @创建人 xfx
     * @创建时间 2019/12/12
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void addClassify(ProductClassifyDto productClassifyDto) {
        ProductClassify productClassify=new ProductClassify();
        BeanCopier.create(ProductClassifyDto.class,ProductClassify.class ,false)
                .copy(productClassifyDto, productClassify, null);
        Long parentId=productClassifyDto.getParentId();
        if(ConvertUtils.isNotEmpty(parentId)){//添加的是一级分类
            productClassify.setParentId(0L);
        }
        productClassify.setState(YesNoEnum.NO.getCode());
        baseMapper.insert(productClassify);
        List<ClassifyAttributeDto> classifyAttributeDtos=productClassifyDto.getClassifyAttributeDtos();
        Long classifyId=productClassify.getId();
        //判断是否包含相同属性名称+添加属性
        if(ConvertUtils.isNotEmpty(classifyAttributeDtos)){
            Map<String,List<ClassifyAttributeDto>> classifyAttrMmap = classifyAttributeDtos.stream().collect(Collectors.groupingBy(ClassifyAttributeDto::getName));
            classifyAttrMmap.forEach((namestr,keyList) -> {
                int attrSize = keyList.size();
                if(attrSize > 0 ){
                    throw new ForbesException(namestr);
                }
                ClassifyAttributeDto classifyAttributeDto=keyList.get(0);
                ClassifyAttribute classifyAttribute = new ClassifyAttribute();
                classifyAttribute.setName(classifyAttributeDto.getName());
                classifyAttribute.setClassifyId(classifyId);
                classifyAttributeMapper.insert(classifyAttribute);
            });
        }
        //判断是否包含相同规格名称+添加规格
        List<ProSpecficDto> proSpecficDtos=productClassifyDto.getProSpecficDtos();
        if(ConvertUtils.isNotEmpty(proSpecficDtos)){
            Map<String,List<ProSpecficDto>> classifyAttrMmap = proSpecficDtos.stream().collect(Collectors.groupingBy(ProSpecficDto::getName));
            classifyAttrMmap.forEach((namestr,keyList) -> {
                int attrSize = keyList.size();
                if(attrSize > 0 ){
                    throw new ForbesException(namestr);
                }
                ProSpecficDto proSpecficDto = keyList.get(0);
                ProductSpecification productSpecification=new ProductSpecification();
                productSpecification.setName(proSpecficDto.getName());
                productSpecification.setOrderSorts(proSpecficDto.getOrderSorts());
                productSpecification.setState(ClassifyStausEnum.FREEZE.getCode().toString());
                productSpecification.setClassifyId(classifyId);
                productSpecificationMapper.insert(productSpecification);
            });
        }
    }
}
