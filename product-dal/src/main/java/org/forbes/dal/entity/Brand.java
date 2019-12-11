package org.forbes.dal.entity;

import java.util.Date;
import lombok.Data;

/**
 * Table: brand
 */
@Data
public class Brand extends BaseEntity{

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