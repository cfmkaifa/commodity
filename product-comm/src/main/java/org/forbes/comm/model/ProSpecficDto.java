package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class ProSpecficDto {

    /**状态
     * 0-未启用,1-启用
     */
    @ApiModelProperty("状态")
    private Long state;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer orderSorts;

    /**
     */
    @ApiModelProperty("规格名称")
    private String name;

}
