package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/14 11:25
 * @Version 1.0
 **/
@Data
@ApiModel(description="规格")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProSpecBatchDto implements Serializable{

    private static final long serialVersionUID = 9138233952667774803L;

    /**商品分类id
     */
    @ApiModelProperty(value = "商品分类id",required = true)
    @NotEmpty(message = "商品分类id不能为空")
    private Long classifyId;

    /**
     *规格相关信息集合
     **/
    @ApiModelProperty(value = "规格相关信息集合",required = true)
    @NotEmpty(message = "规格不能为空")
    private List<ProSpecficDto> proSpecficDtos;

}
