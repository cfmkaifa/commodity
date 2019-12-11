package org.forbes.dal.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * Table: f_specification_value
 */
@Data
public class SpecificationValue extends BaseEntity{
    /**
     * 分类ID
     * Table:     f_specification_value
     * Column:    classify_id
     * Nullable:  true
     */
    private Long classifyId;

    /**
     * 商品ID
     * Table:     f_specification_value
     * Column:    pro_id
     * Nullable:  true
     */
    private Long proId;

    /**
     * 规格ID
     * Table:     f_specification_value
     * Column:    spec_id
     * Nullable:  true
     */
    private Long specId;

    /**
     * 编码
     * Table:     f_specification_value
     * Column:    spec_sn
     * Nullable:  true
     */
    private String specSn;

    /**
     * 库存
     * Table:     f_specification_value
     * Column:    stock
     * Nullable:  true
     */
    private Integer stock;

    /**
     * 销售价
     * Table:     f_specification_value
     * Column:    sale_price
     * Nullable:  true
     */
    private BigDecimal salePrice;

    /**
     * 市场价
     * Table:     f_specification_value
     * Column:    market_price
     * Nullable:  true
     */
    private BigDecimal marketPrice;

    /**
     * 成本价
     * Table:     f_specification_value
     * Column:    cost_price
     * Nullable:  true
     */
    private BigDecimal costPrice;
}