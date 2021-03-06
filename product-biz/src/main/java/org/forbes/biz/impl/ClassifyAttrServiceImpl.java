package org.forbes.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.forbes.biz.IClasAttrService;
import org.forbes.comm.constant.PermsCommonConstant;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.ClassAttrDto;
import org.forbes.comm.model.ClassifyAttributeDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.dal.entity.ClassifyAttribute;
import org.forbes.dal.mapper.ClassifyAttributeMapper;
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
 * @Date 2019/12/12 18:22
 * @Version 1.0
 **/
@Service
public class ClassifyAttrServiceImpl extends ServiceImpl<ClassifyAttributeMapper,ClassifyAttribute> implements IClasAttrService {

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
    @Transactional(rollbackFor = Exception.class)
    public void batchAdd(ClassAttrDto classAttrDto) {
        List<ClassifyAttributeDto> attrnames=classAttrDto.getAttrnames();
        //判断是否包含相同分类属性名称
        if(ConvertUtils.isNotEmpty(attrnames)){
            Long classifyId=classAttrDto.getClassifyId();
            Map<String,List<ClassifyAttributeDto>> tempMap = attrnames.stream().collect(Collectors.groupingBy(ClassifyAttributeDto::getName));
            tempMap.forEach((namestr,keyList) -> {
                int attrSize = keyList.size();
                if(attrSize > 1 ){
                    throw new ForbesException(namestr);
                }
                ClassifyAttributeDto classifyAttributeDto=keyList.get(0);
                ClassifyAttribute classifyAttribute=new ClassifyAttribute();
                classifyAttribute.setClassifyId(classifyId);
                classifyAttribute.setAttributeSn(classifyAttributeDto.getAttributeSn());
                classifyAttribute.setName(classifyAttributeDto.getName());
                clasAttrMapper.insert(classifyAttribute);
            });
        }
    }

    /***
     * 方法概述: 删除属性
     * @param
     * @return
     * @创建人 xfx
     * @创建时间 2019/12/18
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     *
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteAttrbute(Long id) {
        QueryWrapper<ClassifyAttribute> qw=new  QueryWrapper<ClassifyAttribute>();
        clasAttrMapper.delete(qw.eq(PermsCommonConstant.CLASSIFY_ID,id));
        boolean delBool =  SqlHelper.delBool(baseMapper.deleteById(id));
        return delBool;
    }
}
