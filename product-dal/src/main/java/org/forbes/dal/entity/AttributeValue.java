package org.forbes.dal.entity;

import java.util.Date;
import lombok.Data;

/**
 * Table: f_attribute_value
 */
@Data
public class AttributeValue extends BaseEntity{

    /**
     * Table:     f_attribute_value
     * Column:    orders_sort
     * Nullable:  true
     */
    private Integer ordersSort;

    /**
     * 分类ID
     * Table:     f_attribute_value
     * Column:    classify_id
     * Nullable:  true
     */
    private Long classifyId;

    /**
     * 商品ID
     * Table:     f_attribute_value
     * Column:    pro_id
     * Nullable:  true
     */
    private Long proId;

    /**
     * 属性值
     * Table:     f_attribute_value
     * Column:    attribute_value
     * Nullable:  true
     */
    private String attributeValue;
}