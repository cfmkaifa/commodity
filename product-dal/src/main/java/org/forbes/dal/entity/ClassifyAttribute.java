package org.forbes.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Table: f_classify_attribute
 */
@Data
@ApiModel(description="分类属性信息")
@EqualsAndHashCode(callSuper = false)
@TableName("f_classify_attribute")
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

    /**商品分类id
     * Table:     f_classify_attribute
     * Column:    parent_id
     * Nullable:  true
     */
    private Long classifyId;
}