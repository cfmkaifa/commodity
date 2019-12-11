package org.forbes.biz.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.IProductService;
import org.forbes.comm.model.ProductDto;
import org.forbes.comm.model.ProductPageDto;
import org.forbes.comm.vo.ProductVo;
import org.forbes.dal.entity.Product;
import org.forbes.dal.mapper.ProductMapper;
import org.forbes.dal.mapper.ext.ProductExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lzw
 * @date 2019/12/11 15:37
 */
@Service
public class IProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    ProductExtMapper productExtMapper;

    /***
     * selectProduct方法慨述:分页查询用户信息
     * @param page
     * @param productPageDto
     * @return IPage<ProductVo>
     * @创建人 lzw
     * @创建时间 2019年12月11日
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public IPage<ProductVo> selectProduct(IPage<ProductVo> page, ProductPageDto productPageDto) {
        return productExtMapper.selectProduct(page,productPageDto);
    }
}
