package org.forbes.dal.mapper.ext;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.forbes.comm.model.ProductPageDto;
import org.forbes.comm.vo.ProductVo;
import org.forbes.dal.entity.Product;
import org.springframework.stereotype.Repository;

/**
 * @author lzw
 * @date 2019/12/11 15:38
 */
public interface ProductExtMapper extends BaseMapper<Product> {

    /***
     * selectProduct方法概述:分页查询商品信息
     * @param 
     * @return 
     * @创建人 Tom
     * @创建时间 2019/12/11 16:05
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    IPage<ProductVo> PageProduct(IPage<ProductVo> page, @Param("productDto") ProductPageDto productPageDto);

}
