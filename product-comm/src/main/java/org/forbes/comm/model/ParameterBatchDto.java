package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author lzw
 * @date 2020/1/10 9:15
 */
@Data
@ApiModel(description="分类属性")
@EqualsAndHashCode(callSuper = false)
public class ParameterBatchDto implements Serializable{

    private static final long serialVersionUID = 2807384356612013204L;

    /**商品分类id
     */
    @ApiModelProperty(value = "商品分类id",required = true,example = "0")
    @NotEmpty(message = "商品分类id不能为空")
    private Long classifyId;

    /**
     *规格相关信息集合
     **/
    @ApiModelProperty(value = "参数相关信息集合",required = true)
    @NotEmpty(message = "参数不能为空")
    private List<ParameterDto> parameterDtos;
}
