package org.forbes.dal.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Table: f_product_classify
 */
@Data
@ApiModel(description="商品分类信息")
@EqualsAndHashCode(callSuper = false)
@TableName("f_product_classify")
public class ProductClassify extends BaseEntity {
    private static final long serialVersionUID = 1756859705133117200L;
    /**
     * 分类编码
     * Table:     f_product_classify
     * Column:    classify_sn
     * Nullable:  true
     */
    private String classifySn;

    /**
     * 分类名称
     * Table:     f_product_classify
     * Column:    name
     * Nullable:  true
     */
    private String name;

    /**
     * Table:     f_product_classify
     * Column:    parent_id
     * Nullable:  true
     */
    private Long parentId;

    /**
     * 等级
     * Table:     f_product_classify
     * Column:    level
     * Nullable:  true
     */
    private Long level;

    /**
     * 状态:0未启用1-启用
     * Table:     f_product_classify
     * Column:    state
     * Nullable:  true
     */
    private String state;
}