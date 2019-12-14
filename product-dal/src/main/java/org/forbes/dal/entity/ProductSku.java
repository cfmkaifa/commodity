package org.forbes.dal.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Table: f_product_sku
 */
@Data
@ApiModel(description="库存信息")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("f_product_sku")
public class ProductSku extends BaseEntity {

    private static final long serialVersionUID = -1635539519945799634L;

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