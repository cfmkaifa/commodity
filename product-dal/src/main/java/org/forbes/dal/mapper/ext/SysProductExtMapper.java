package org.forbes.dal.mapper.ext;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.forbes.comm.model.ProductPageDto;
import org.forbes.comm.vo.ProductSysPageVo;
import org.forbes.dal.entity.Product;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/16 16:26
 * @Version 1.0
 **/
public interface SysProductExtMapper extends BaseMapper<Product> {
    /***
     * 方法概述:总后台商品分页查询
     * @param
     * @return
     * @创建人 xfx
     * @创建时间 2019/12/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    IPage<ProductSysPageVo> sysPage(IPage<ProductSysPageVo> page, @Param("productPageDto") ProductPageDto productPageDto);

    /***
     * 方法概述:查询商品详情+图片+分类
     * @param
     * @return
     * @创建人 xfx
     * @创建时间 2019/12/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    ProductSysPageVo detail(Long id);
}
