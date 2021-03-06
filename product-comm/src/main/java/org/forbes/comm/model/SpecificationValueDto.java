package org.forbes.comm.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.forbes.comm.constant.UpdateValid;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lzw
 * @date 2019/12/13 13:45
 */
@Data
@ApiModel(description="规格参数")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SpecificationValueDto implements Serializable {

    private static final long serialVersionUID = 6023824965379172323L;

    /**
     * id
     */
    @ApiModelProperty(value = "id",example = "0")
    private Long id;

    /**
     * 分类ID
     * Table:     f_specification_value
     * Column:    classify_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "分类ID",example = "0")
    private Long classifyId;

    /**
     * 商品ID
     * Table:     f_specification_value
     * Column:    pro_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "商品ID",example = "0")
    private Long proId;

    /**
     * 规格ID
     * Table:     f_specification_value
     * Column:    spec_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "规格ID",example = "0")
    private Long specId;


    /***规格值
     */
    @ApiModelProperty("规格值")
    private String specVal;

    /***skuId
     */
    @ApiModelProperty(value = "库存Id",example = "0")
    private Long skuId;
}
