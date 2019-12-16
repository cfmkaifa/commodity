package org.forbes.biz.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.IProductSysService;
import org.forbes.comm.model.ProductPageDto;
import org.forbes.comm.vo.ProductSysPageVo;
import org.forbes.dal.entity.Product;
import org.forbes.dal.mapper.ProductMapper;
import org.forbes.dal.mapper.ext.SysProductExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/16 16:17
 * @Version 1.0
 **/
@Service
public class ProductSysServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductSysService{

    @Autowired
    SysProductExtMapper sysProductExtMapper;
    /***
     * 方法概述:总后台商品分页查询
     * @param
     * @return
     * @创建人 xfx
     * @创建时间 2019/12/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    public IPage<ProductSysPageVo> sysPage(IPage<ProductSysPageVo> page, ProductPageDto productPageDto) {
        return sysProductExtMapper.sysPage(page,productPageDto);
    }

    /***
     * 方法概述:查询商品详情+图片+分类
     * @param
     * @return
     * @创建人 xfx
     * @创建时间 2019/12/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    public ProductSysPageVo detail(Long id) {
        return sysProductExtMapper.detail(id);
    }
}
