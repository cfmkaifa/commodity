package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.forbes.dal.entity.SpecificationValue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author lzw
 * @date 2019/12/12 18:34
 */
@Data
@ApiModel(description="商品库存增加修改参数")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductSkuDto implements Serializable {

    List<SpecificationValue> specificationValues;

    /**
     * 分类ID
     * Table:     f_product_sku
     * Column:    classify_id
     * Nullable:  true
     */
    private Long classifyId;

    /**
     * 商品ID
     * Table:     f_product_sku
     * Column:    pro_id
     * Nullable:  true
     */
    private Long proId;

    /**
     * 编码
     * Table:     f_product_sku
     * Column:    sku_sn
     * Nullable:  true
     */
    private String skuSn;

    /**
     * 库存
     * Table:     f_product_sku
     * Column:    stock
     * Nullable:  true
     */
    private Integer stock;

    /**
     * 销售价
     * Table:     f_product_sku
     * Column:    sale_price
     * Nullable:  true
     */
    private BigDecimal salePrice;

    /**
     * 市场价
     * Table:     f_product_sku
     * Column:    market_price
     * Nullable:  true
     */
    private BigDecimal marketPrice;

    /**
     * 成本价
     * Table:     f_product_sku
     * Column:    cost_price
     * Nullable:  true
     */
    private BigDecimal costPrice;
}
