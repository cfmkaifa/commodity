package org.forbes.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.forbes.biz.IProductService;
import org.forbes.comm.constant.DataColumnConstant;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.enums.ProductStausEnum;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.*;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.ProductAttvalueVo;
import org.forbes.comm.vo.ProductVo;
import org.forbes.dal.entity.*;
import org.forbes.dal.mapper.*;
import org.forbes.dal.mapper.ext.ProductExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author lzw
 * @date 2019/12/11 15:37
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    ProductExtMapper productExtMapper;

    @Autowired
    ProductAttachMapper productAttachMapper;

    @Autowired
    AttributeValueMapper attributeValueMapper;

    @Autowired
    ProductSkuMapper productSkuMapper;

    @Autowired
    SpecificationValueMapper specificationValueMapper;

    /***
     * selectProduct方法慨述:分页查询用户信息
     * @param page
     * @param productPageDto
     * @return IPage < ProductVo>
     * @创建人 lzw
     * @创建时间 2019年12月11日
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public IPage<ProductAttvalueVo> pageProduct(IPage<ProductAttvalueVo> page, ProductPageDto productPageDto) {
        return productExtMapper.pageProduct(page,productPageDto);
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
        Long proId = product.getId();
        //添加附件值
        List<ProductAttachDto> productAttachDtos = productDto.getProductAttachDtos();
        if(ConvertUtils.isNotEmpty(productAttachDtos)){
            productAttachDtos.stream().forEach(productAttachDto -> {
                ProductAttach productAttach = new ProductAttach();
                productAttach.setDataId(proId);
                productAttach.setOrdersSort(productAttach.getOrdersSort());
                productAttach.setSuffix(productAttach.getSuffix());
                productAttach.setCnName(productAttach.getCnName());
                productAttach.setEnName(productAttach.getEnName());
                productAttach.setFilePath(productAttach.getFilePath());
                productAttach.setType(productAttach.getType());
                productAttachMapper.insert(productAttach);
            });
        }

        //添加属性值
        List<AttributeValueDto> attributeValues = productDto.getAttributeValueDtos();
        if(ConvertUtils.isNotEmpty(attributeValues)){
            long classIdCount =  attributeValues.stream().filter(attributeValue -> !productDto.getClassifyId().equals(attributeValue.getClassifyId())).count();
            if(classIdCount > 0 ){
                throw new ForbesException(BizResultEnum.CLASSIFY_ATTRIBUTE_ID_NOT_EXIST.getBizCode()
                        ,String.format(BizResultEnum.CLASSIFY_ATTRIBUTE_ID_NOT_EXIST.getBizMessage()));
            }
            attributeValues.stream().forEach(attributeValue->{
                AttributeValue  attributeNewValue = new AttributeValue();
                attributeNewValue.setProId(proId);
                attributeNewValue.setClassifyId(attributeValue.getClassifyId());
                attributeNewValue.setAttributeValue(attributeValue.getAttributeValue());
                attributeNewValue.setOrdersSort(attributeValue.getOrdersSort());
                attributeValueMapper.insert(attributeNewValue);
            });
        }

        //添加库存规格值
        List<ProductSkuDto> productSkus = productDto.getProductSkuDtos();
        if(ConvertUtils.isNotEmpty(productSkus)){
            long classIdCount =  productSkus.stream().filter(productSku -> !productDto.getClassifyId().equals(productSku.getClassifyId())).count();
            if(classIdCount > 0 ){
                throw new ForbesException(BizResultEnum.CLASSIFY_ATTRIBUTE_ID_NOT_EXIST.getBizCode()
                        ,String.format(BizResultEnum.CLASSIFY_ATTRIBUTE_ID_NOT_EXIST.getBizMessage()));
            }
            productSkus.stream().forEach(productSku ->{
                    ProductSku productSku1 = new ProductSku();
                    productSku1.setProId(proId);
                    productSku1.setClassifyId(productSku.getClassifyId());
                    productSku1.setSkuSn(productSku.getSkuSn());
                    productSku1.setStock(productSku.getStock());
                    productSku1.setSalePrice(productSku.getSalePrice());
                    productSku1.setMarketPrice(productSku.getMarketPrice());
                    productSku1.setCostPrice(productSku.getCostPrice());
                    productSkuMapper.insert(productSku1);

                    //添加规格值
                    List<SpecificationValueDto> specificationValueDtos = productSku.getSpecificationValueDtos();
                    if(ConvertUtils.isNotEmpty(specificationValueDtos)){
                        long classIdCount1 =  specificationValueDtos.stream().filter(specificationValueDto -> !productDto.getClassifyId().equals(specificationValueDto.getClassifyId())).count();
                        if(classIdCount1 > 0 ){
                            throw new ForbesException(BizResultEnum.CLASSIFY_ATTRIBUTE_ID_NOT_EXIST.getBizCode()
                                    ,String.format(BizResultEnum.CLASSIFY_ATTRIBUTE_ID_NOT_EXIST.getBizMessage()));
                        }
                        specificationValueDtos.stream().forEach(specificationValueDto ->{
                            SpecificationValue specificationValue=new SpecificationValue();
                            specificationValue.setProId(proId);
                            specificationValue.setClassifyId(specificationValueDto.getClassifyId());
                            specificationValue.setSpecId(specificationValueDto.getSpecId());
                            specificationValue.setSpecVal(specificationValueDto.getSpecVal());
                            specificationValue.setSkuId(productSku1.getId());
                            specificationValueMapper.insert(specificationValue);
                        });
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
        //删除
        productAttachMapper.delete(new QueryWrapper<ProductAttach>().eq(DataColumnConstant.PROID, productDto.getId()));
        attributeValueMapper.delete(new QueryWrapper<AttributeValue>().eq(DataColumnConstant.PROID, productDto.getId()));
        productSkuMapper.delete(new QueryWrapper<ProductSku>().eq(DataColumnConstant.PROID, productDto.getId()));
        specificationValueMapper.delete(new QueryWrapper<SpecificationValue>().eq(DataColumnConstant.PROID, productDto.getId()));
        Long proId = product.getId();
        //添加附件值
        List<ProductAttachDto> productAttachDtos = productDto.getProductAttachDtos();
        if(ConvertUtils.isNotEmpty(productAttachDtos)){
            productAttachDtos.stream().forEach(productAttachDto -> {
                ProductAttach productAttach = new ProductAttach();
                productAttach.setDataId(proId);
                productAttach.setOrdersSort(productAttach.getOrdersSort());
                productAttach.setSuffix(productAttach.getSuffix());
                productAttach.setCnName(productAttach.getCnName());
                productAttach.setEnName(productAttach.getEnName());
                productAttach.setFilePath(productAttach.getFilePath());
                productAttach.setType(productAttach.getType());
                productAttachMapper.insert(productAttach);
            });
        }
        //添加属性值
        List<AttributeValueDto> attributeValues = productDto.getAttributeValueDtos();
        if(ConvertUtils.isNotEmpty(attributeValues)){
            long classIdCount =  attributeValues.stream().filter(attributeValue -> !productDto.getClassifyId().equals(attributeValue.getClassifyId())).count();
            if(classIdCount > 0 ){
                throw new ForbesException(BizResultEnum.CLASSIFY_ATTRIBUTE_ID_NOT_EXIST.getBizCode()
                        ,String.format(BizResultEnum.CLASSIFY_ATTRIBUTE_ID_NOT_EXIST.getBizMessage()));
            }
            attributeValues.stream().forEach(attributeValue->{
                AttributeValue  attributeNewValue = new AttributeValue();
                attributeNewValue.setProId(proId);
                attributeNewValue.setClassifyId(attributeValue.getClassifyId());
                attributeNewValue.setAttributeValue(attributeValue.getAttributeValue());
                attributeNewValue.setOrdersSort(attributeValue.getOrdersSort());
                attributeValueMapper.insert(attributeNewValue);
            });
        }
        //添加库存规格值
        List<ProductSkuDto> productSkus = productDto.getProductSkuDtos();
        if(ConvertUtils.isNotEmpty(productSkus)){
            long classIdCount =  productSkus.stream().filter(productSku -> !productDto.getClassifyId().equals(productSku.getClassifyId())).count();
            if(classIdCount > 0 ){
                throw new ForbesException(BizResultEnum.CLASSIFY_ATTRIBUTE_ID_NOT_EXIST.getBizCode()
                        ,String.format(BizResultEnum.CLASSIFY_ATTRIBUTE_ID_NOT_EXIST.getBizMessage()));
            }
            productSkus.stream().forEach(productSku ->{
                ProductSku productSku1 = new ProductSku();
                productSku1.setProId(proId);
                productSku1.setClassifyId(productSku.getClassifyId());
                productSku1.setSkuSn(productSku.getSkuSn());
                productSku1.setStock(productSku.getStock());
                productSku1.setSalePrice(productSku.getSalePrice());
                productSku1.setMarketPrice(productSku.getMarketPrice());
                productSku1.setCostPrice(productSku.getCostPrice());
                productSkuMapper.insert(productSku1);
                //添加规格值
                List<SpecificationValueDto> specificationValueDtos = productSku.getSpecificationValueDtos();
                if(ConvertUtils.isNotEmpty(specificationValueDtos)){
                    long classIdCount1 =  specificationValueDtos.stream().filter(specificationValueDto -> !productDto.getClassifyId().equals(specificationValueDto.getClassifyId())).count();
                    if(classIdCount1 > 0 ){
                        throw new ForbesException(BizResultEnum.CLASSIFY_ATTRIBUTE_ID_NOT_EXIST.getBizCode()
                                ,String.format(BizResultEnum.CLASSIFY_ATTRIBUTE_ID_NOT_EXIST.getBizMessage()));
                    }
                    specificationValueDtos.stream().forEach(specificationValueDto ->{
                        SpecificationValue specificationValue=new SpecificationValue();
                        specificationValue.setProId(proId);
                        specificationValue.setClassifyId(specificationValueDto.getClassifyId());
                        specificationValue.setSpecId(specificationValueDto.getSpecId());
                        specificationValue.setSpecVal(specificationValueDto.getSpecVal());
                        specificationValue.setSkuId(productSku1.getId());
                        specificationValueMapper.insert(specificationValue);
                    });
                }
            });
        }
    }

    /***
     * deleteProduct方法概述:根据id删除商品信息
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/16 10:55
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteProduct(Long id) {
        return productExtMapper.deleteProduct(id);
    }

    /**
     * 批量删除
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        List<Product> products =  baseMapper.selectBatchIds(idList);
        if(ConvertUtils.isEmpty(products)){
            throw new ForbesException(BizResultEnum.ENTITY_EMPTY.getBizCode()
                    ,String.format(BizResultEnum.ENTITY_EMPTY.getBizMessage()));
        }
        productAttachMapper.delete(new QueryWrapper<ProductAttach>().eq(DataColumnConstant.DATAID, idList));
        attributeValueMapper.delete(new QueryWrapper<AttributeValue>().eq(DataColumnConstant.PROID, idList));
        productSkuMapper.delete(new QueryWrapper<ProductSku>().eq(DataColumnConstant.PROID, idList));
        specificationValueMapper.delete(new QueryWrapper<SpecificationValue>().eq(DataColumnConstant.PROID, idList));
        boolean delBool =  SqlHelper.delBool(baseMapper.deleteBatchIds(idList));
        return delBool;
    }

    /***
     * selectProducts方法概述:通过id查询商品详细信息
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/16 9:52
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public ProductAttvalueVo selectProducts(Long id) {
        return productExtMapper.selectProducts(id);
    }

}
