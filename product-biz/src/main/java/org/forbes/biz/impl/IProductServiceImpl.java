package org.forbes.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.IProductService;
import org.forbes.comm.constant.DataColumnConstant;
import org.forbes.comm.enums.ProductStausEnum;
import org.forbes.comm.model.ProductDto;
import org.forbes.comm.model.ProductPageDto;
import org.forbes.comm.model.ProductSkuDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.ProductVo;
import org.forbes.dal.entity.AttributeValue;
import org.forbes.dal.entity.Product;
import org.forbes.dal.entity.ProductSku;
import org.forbes.dal.entity.SpecificationValue;
import org.forbes.dal.mapper.AttributeValueMapper;
import org.forbes.dal.mapper.ProductMapper;
import org.forbes.dal.mapper.SpecificationValueMapper;
import org.forbes.dal.mapper.ext.ProductExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lzw
 * @date 2019/12/11 15:37
 */
@Service
public class IProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    ProductExtMapper productExtMapper;

    @Autowired
    AttributeValueMapper attributeValueMapper;

    @Autowired
    SpecificationValueMapper specificationValueMapper;

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
    public IPage<ProductVo> PageProduct(IPage<ProductVo> page, ProductPageDto productPageDto) {
        return productExtMapper.PageProduct(page,productPageDto);
    }

    /***
     * addProduct方法概述:
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/12 15:55
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor=Exception.class)
    public void addProduct(ProductDto productDto) {
        Product product=new Product();
        BeanCopier.create(ProductDto.class,Product.class ,false).copy(productDto, product, null);
        productDto.setState(ProductStausEnum.FREEZE.getCode());
        baseMapper.insert(product);
        List<AttributeValue> attributeValues = productDto.getAttributeValues();
        if(ConvertUtils.isNotEmpty(attributeValues)){
            Long proId = product.getId();
            attributeValues.stream().forEach(attributeValue -> {
                //判断输入的规格属性分类id是否和商品的分类id是否一致
                if(productDto.getClassifyId()==attributeValue.getClassifyId()){
                    AttributeValue  attributeValue1 = new AttributeValue();
                    attributeValue1.setProId(proId);
                    attributeValue1.setClassifyId(attributeValue.getClassifyId());
                    attributeValue1.setAttributeValue(attributeValue.getAttributeValue());
                    attributeValue1.setOrdersSort(attributeValue.getOrdersSort());
                    attributeValueMapper.insert(attributeValue1);
                }
            });
        }
        List<ProductSkuDto> productSkuDtos = productDto.getProductSkuDtos();
        if(ConvertUtils.isNotEmpty(productSkuDtos)){
            Long proId = product.getId();
            productSkuDtos.stream().forEach(productSkuDto ->{
                //判断输入的规格属性分类id是否和商品的分类id是否一致
                if(productDto.getClassifyId()==productSkuDto.getClassifyId()) {
                    ProductSkuDto productSku1 = new ProductSkuDto();
                    productSku1.setProId(proId);
                    productSku1.setClassifyId(productSkuDto.getClassifyId());
                    productSku1.setSkuSn(productSkuDto.getSkuSn());
                    productSku1.setStock(productSkuDto.getStock());
                    productSku1.setSalePrice(productSkuDto.getSalePrice());
                    productSku1.setMarketPrice(productSkuDto.getMarketPrice());
                    productSku1.setCostPrice(productSkuDto.getCostPrice());
                }
            });
        }


    }

    /***
     * updateProduct方法概述:修改商品信息
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/12 17:32
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor=Exception.class)
    public void updateProduct(ProductDto productDto) {
        Product product=new Product();
        BeanCopier.create(ProductDto.class,Product.class ,false).copy(productDto, product, null);
        baseMapper.updateById(product);
        attributeValueMapper.delete(new QueryWrapper<AttributeValue>().eq(DataColumnConstant.USER_ID, productDto.getId()));

    }

}
