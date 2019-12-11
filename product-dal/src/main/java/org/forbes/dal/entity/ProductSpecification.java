package org.forbes.dal.entity;

import java.util.Date;
import lombok.Data;

/**
 * Table: f_product_specification
 */
@Data
public class ProductSpecification extends BaseEntity {
    /**
     * 主键
     * Table:     f_product_specification
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * Table:     f_product_specification
     * Column:    create_by
     * Nullable:  true
     */
    private String createBy;

    /**
     * 创建时间
     * Table:     f_product_specification
     * Column:    create_time
     * Nullable:  true
     */
    private Date createTime;

    /**
     * 更新人
     * Table:     f_product_specification
     * Column:    update_by
     * Nullable:  true
     */
    private String updateBy;

    /**
     * 更新时间
     * Table:     f_product_specification
     * Column:    update_time
     * Nullable:  true
     */
    private Date updateTime;

    /**
     * 0-未启用,1-启用
     * Table:     f_product_specification
     * Column:    state
     * Nullable:  true
     */
    private Long state;

    /**
     * 排序
     * Table:     f_product_specification
     * Column:    order_sorts
     * Nullable:  true
     */
    private Integer orderSorts;

    /**
     * Table:     f_product_specification
     * Column:    name
     * Nullable:  true
     */
    private String name;

    /**
     * 父级规格ID
     * Table:     f_product_specification
     * Column:    parent_id
     * Nullable:  true
     */
    private Long parentId;

    /**
     * 规格编码
     * Table:     f_product_specification
     * Column:    spec_sn
     * Nullable:  true
     */
    private String specSn;
}