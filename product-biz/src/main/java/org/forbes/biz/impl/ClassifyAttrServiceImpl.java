package org.forbes.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.ISClasAttrService;
import org.forbes.comm.model.ClassAttrDto;
import org.forbes.comm.model.ClassifyAttributeDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.dal.entity.ClassifyAttribute;
import org.forbes.dal.mapper.ClassifyAttributeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/12 18:22
 * @Version 1.0
 **/
@Service
public class ClassifyAttrServiceImpl extends ServiceImpl<ClassifyAttributeMapper,ClassifyAttribute> implements ISClasAttrService {

    @Autowired
    private ClassifyAttributeMapper clasAttrMapper;

    /***
     * 方法概述:批量添加分类属性
     * @param
     * @return
     * @创建人 xfx
     * @创建时间 2019/12/13
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    public void batchAdd(ClassAttrDto classAttrDto) {
        List<ClassifyAttributeDto> attrnames=classAttrDto.getAttrnames();
        if(ConvertUtils.isNotEmpty(attrnames)){
            Long classifyId=classAttrDto.getClassifyId();
            attrnames.stream().forEach(temp -> {
                ClassifyAttribute classifyAttribute=new ClassifyAttribute();
                classifyAttribute.setClassifyId(classifyId);
                classifyAttribute.setName(temp.getName());
                clasAttrMapper.insert(classifyAttribute);
            });
        }
    }
}
