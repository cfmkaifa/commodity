package org.forbes.biz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.model.ProductDto;
import org.forbes.comm.model.ProductPageDto;
import org.forbes.comm.vo.ProductAttvalueVo;
import org.forbes.comm.vo.ProductVo;
import org.forbes.dal.entity.Product;

/**
 * @author lzw
 * @date 2019/12/11 15:36
 */
public interface IProductService extends IService<Product> {

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
    IPage<ProductAttvalueVo> pageProduct(IPage<ProductAttvalueVo> page, ProductPageDto productPageDto);

    /***
     * addProduct方法概述:新增商品信息
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/12 15:55
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void addProduct(ProductDto productDto);

    /***
     * updateProduct方法概述:修改商品信息
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/12 17:32
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void updateProduct(ProductDto productDto);

    /***
     * deleteProduct方法概述:根据id删除商品信息
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/16 10:55
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    boolean deleteProduct(Long id);

    /***
     * selectProducts方法概述:通过id查询商品详细信息
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/16 9:52
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    ProductAttvalueVo selectProducts(Long id);

}
