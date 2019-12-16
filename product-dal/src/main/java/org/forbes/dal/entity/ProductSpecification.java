package org.forbes.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Table: f_product_specification
 */
@Data
@ApiModel(description="分类规格")
@EqualsAndHashCode(callSuper = false)
@TableName("f_product_specification")
public class ProductSpecification extends BaseEntity{
  
	private static final long serialVersionUID = -856092185658314266L;

	/**
     * 0-未启用,1-启用
     * Table:     f_product_specification
     * Column:    state
     * Nullable:  true
     */
	@ApiModelProperty(value = "状态 0-未启用,1-启用 ")
    private String state;

    /**
     * 排序
     * Table:     f_product_specification
     * Column:    order_sorts
     * Nullable:  true
     */
    @ApiModelProperty(value = "排序")
    private Integer orderSorts;

    /**
     * Table:     f_product_specification
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "规格名称")
    private String name;

    /**商品分类id
     * Table:     f_product_specification
     * Column:    classify_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "商品分类id")
    private Long classifyId;
}