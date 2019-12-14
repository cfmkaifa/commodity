package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
    @ApiModelProperty("商品分类id")
    private Long classifyId;

    /**
     *规格相关信息集合
     **/
    @ApiModelProperty("规格相关信息集合")
    private List<ProSpecficDto> proSpecficDtos;

}
