package org.forbes.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Table: f_product_parameter
 */
@Data
@ApiModel(description="属性")
@EqualsAndHashCode(callSuper = false)
@TableName("f_product_parameter")
public class ProductParameter extends BaseEntity{

    private static final long serialVersionUID = -8081550802040175473L;

    /**
     * 排序
     * Table:     f_product_specification
     * Column:    order_sorts
     * Nullable:  true
     */
    @ApiModelProperty(value = "排序",example = "0")
    private Integer orderSorts;

    /**
     * Table:     f_product_specification
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "属性名称")
    private String name;

    /**商品分类id
     * Table:     f_product_specification
     * Column:    classify_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "商品分类id",example = "0")
    private Long classifyId;
}