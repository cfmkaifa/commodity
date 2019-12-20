package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/13 15:54
 * @Version 1.0
 **/
@Data
@ApiModel(description="规格")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProSpecficDto implements Serializable{

    private static final long serialVersionUID = 1977858035105779148L;



    /**
     * id
     */
    @ApiModelProperty(value = "规格id",required = true)
    private Long id;

    /**状态
     * 0-未启用,1-启用
     */
    @ApiModelProperty("状态")
    private String state;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序",required = true)
    private Integer orderSorts;

    /**
     */
    @ApiModelProperty(value = "规格名称",required = true)
    private String name;

    /**商品分类id
     */
    @ApiModelProperty(value = "商品分类id",required = true)
    private Long classifyId;
}
