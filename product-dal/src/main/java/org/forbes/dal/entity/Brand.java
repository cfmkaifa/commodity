package org.forbes.dal.entity;

import java.util.Date;
import lombok.Data;

/**
 * Table: brand
 */
@Data
public class Brand{
    /**
     * 主键
     * Table:     brand
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * Table:     brand
     * Column:    create_by
     * Nullable:  true
     */
    private String createBy;

    /**
     * 创建时间
     * Table:     brand
     * Column:    create_time
     * Nullable:  true
     */
    private Date createTime;

    /**
     * 更新人
     * Table:     brand
     * Column:    update_by
     * Nullable:  true
     */
    private String updateBy;

    /**
     * 更新时间
     * Table:     brand
     * Column:    update_time
     * Nullable:  true
     */
    private Date updateTime;

    /**
     * 分类ID
     * Table:     brand
     * Column:    classify_id
     * Nullable:  true
     */
    private Long classifyId;

    /**
     * Table:     brand
     * Column:    name
     * Nullable:  true
     */
    private String name;

    /**
     * Table:     brand
     * Column:    intr
     * Nullable:  true
     */
    private String intr;
}