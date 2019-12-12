package org.forbes.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Table: f_specification_value
 */
@Data
@ApiModel(description="分类规格")
@EqualsAndHashCode(callSuper = false)
@TableName("f_specification_value")
public class SpecificationValue extends BaseEntity{
    
	private static final long serialVersionUID = 8259619795373167378L;

	/**
     * 分类ID
     * Table:     f_specification_value
     * Column:    classify_id
     * Nullable:  true
     */
    private Long classifyId;

    /**
     * 商品ID
     * Table:     f_specification_value
     * Column:    pro_id
     * Nullable:  true
     */
    private Long proId;

    /**
     * 规格ID
     * Table:     f_specification_value
     * Column:    spec_id
     * Nullable:  true
     */
    private Long specId;


    /***规格值
     */
    private String specVal;
    
    /***skuId
     */
    private Long skuId;
}