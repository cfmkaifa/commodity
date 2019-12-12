package org.forbes.dal.entity;

import java.util.Date;
import lombok.Data;

/**
 * Table: f_product_brand
 */
@Data
public class ProductBrand extends BaseEntity {
    /**
     * 主键
     * Table:     f_product_brand
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * Table:     f_product_brand
     * Column:    create_by
     * Nullable:  true
     */
    private String createBy;

    /**
     * 创建时间
     * Table:     f_product_brand
     * Column:    create_time
     * Nullable:  true
     */
    private Date createTime;

    /**
     * 更新人
     * Table:     f_product_brand
     * Column:    update_by
     * Nullable:  true
     */
    private String updateBy;

    /**
     * 更新时间
     * Table:     f_product_brand
     * Column:    update_time
     * Nullable:  true
     */
    private Date updateTime;

    /**
     * 分类ID
     * Table:     f_product_brand
     * Column:    classify_id
     * Nullable:  true
     */
    private Long classifyId;

    /**
     * Table:     f_product_brand
     * Column:    name
     * Nullable:  true
     */
    private String name;

    /**
     * Table:     f_product_brand
     * Column:    intr
     * Nullable:  true
     */
    private String intr;
}