package org.forbes.dal.entity;

import java.util.Date;
import lombok.Data;

/**
 * Table: f_classify_attribute
 */
@Data
public class ClassifyAttribute extends BaseEntity{
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