package org.forbes.dal.entity;

import java.util.Date;
import lombok.Data;

/**
 * Table: f_classify_attribute
 */
@Data
public class ClassifyAttribute {
    /**
     * 主键
     * Table:     f_classify_attribute
     * Column:    id
     * Nullable:  false
     */
    private Long id;

    /**
     * Table:     f_classify_attribute
     * Column:    create_by
     * Nullable:  true
     */
    private String createBy;

    /**
     * 创建时间
     * Table:     f_classify_attribute
     * Column:    create_time
     * Nullable:  true
     */
    private Date createTime;

    /**
     * 更新人
     * Table:     f_classify_attribute
     * Column:    update_by
     * Nullable:  true
     */
    private String updateBy;

    /**
     * 更新时间
     * Table:     f_classify_attribute
     * Column:    update_time
     * Nullable:  true
     */
    private Date updateTime;

    /**
     * 名称
     * Table:     f_classify_attribute
     * Column:    name
     * Nullable:  true
     */
    private String name;

    /**
     * 属性编码
     * Table:     f_classify_attribute
     * Column:    attribute_sn
     * Nullable:  true
     */
    private String attributeSn;
}