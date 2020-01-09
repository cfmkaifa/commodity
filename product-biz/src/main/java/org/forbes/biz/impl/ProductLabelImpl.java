package org.forbes.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.forbes.biz.IProductLabelService;
import org.forbes.comm.constant.DataColumnConstant;
import org.forbes.dal.entity.ProductLabel;
import org.forbes.dal.mapper.ProductLabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author lzw
 * @date 2020/1/8 10:46
 */
@Service
public class ProductLabelImpl extends ServiceImpl<ProductLabelMapper, ProductLabel> implements IProductLabelService {

    @Autowired
    ProductLabelMapper productLabelMapper;

    /***
     *  删除商品标签
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        boolean delBool =  SqlHelper.delBool(baseMapper.deleteById(id));
        return delBool;
    }

    /***批量删除商品标签
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        boolean delBool =  SqlHelper.delBool(baseMapper.deleteBatchIds(idList));
        return delBool;
    }

}
