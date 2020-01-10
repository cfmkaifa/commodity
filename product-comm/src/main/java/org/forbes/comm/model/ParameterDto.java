package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author lzw
 * @date 2020/1/10 9:17
 */
@Data
@ApiModel(description="分类属性")
@EqualsAndHashCode(callSuper = false)
public class ParameterDto implements Serializable{
    /**
     * id
     */
    @ApiModelProperty(value = "参数id",required = true,example = "0")
    private Long id;

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
