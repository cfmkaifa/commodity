package org.forbes.dal.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * Table: f_product_sku
 */
@Data
public class ProductSku extends BaseEntity {
    /**
     * 主键
     * Table:     f_product_sku
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * Table:     f_product_sku
     * Column:    create_by
     * Nullable:  true
     */
    private String createBy;

    /**
     * 创建时间
     * Table:     f_product_sku
     * Column:    create_time
     * Nullable:  true
     */
    private Date createTime;

    /**
     * 更新人
     * Table:     f_product_sku
     * Column:    update_by
     * Nullable:  true
     */
    private String updateBy;

    /**
     * 更新时间
     * Table:     f_product_sku
     * Column:    update_time
     * Nullable:  true
     */
    private Date updateTime;

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