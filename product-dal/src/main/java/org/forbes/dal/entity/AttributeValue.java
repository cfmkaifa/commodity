package org.forbes.dal.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Table: f_attribute_value
 */
@Data
@ApiModel(description="商品属性对象")
@EqualsAndHashCode(callSuper = false)
@TableName("f_attribute_value")
public class AttributeValue extends BaseEntity{

    private static final long serialVersionUID = -2615173738384945093L;
    /**
     * Table:     f_attribute_value
     * Column:    orders_sort
     * Nullable:  true
     */
    @ApiModelProperty(value = "排序",example = "0")
    private Integer ordersSort;

    /**
     * 分类ID
     * Table:     f_attribute_value
     * Column:    classify_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "分类ID",example = "0")
    private Long classifyId;

    /**
     * 商品ID
     * Table:     f_attribute_value
     * Column:    pro_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "商品ID",example = "0")
    private Long proId;

    /**
     * 属性值
     * Table:     f_attribute_value
     * Column:    attribute_value
     * Nullable:  true
     */
    @ApiModelProperty(value = "属性值",example = "0")
    private String attributeValue;
}