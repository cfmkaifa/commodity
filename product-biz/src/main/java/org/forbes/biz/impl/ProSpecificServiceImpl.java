package org.forbes.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.forbes.biz.IProSpecificService;
import org.forbes.comm.constant.PermsCommonConstant;
import org.forbes.dal.entity.ProductSpecification;
import org.forbes.dal.mapper.ProductSpecificationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/13 9:50
 * @Version 1.0
 **/
@Service
public class ProSpecificServiceImpl extends ServiceImpl<ProductSpecificationMapper,ProductSpecification> implements IProSpecificService {

    /***
     * 方法概述: 删除规格
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
    public boolean deleteProductSpecification(Long id) {
        QueryWrapper<ProductSpecification> qw=new  QueryWrapper<ProductSpecification>();
        baseMapper.delete(qw.eq(PermsCommonConstant.PRO_SPEC_CLASSIFY_ID,id));
        boolean delBool =  SqlHelper.delBool(baseMapper.deleteById(id));
        return delBool;
    }
}
