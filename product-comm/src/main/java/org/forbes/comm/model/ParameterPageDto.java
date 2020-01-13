package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author lzw
 * @date 2020/1/10 9:10
 */
@Data
@ApiModel(description="参数")
@EqualsAndHashCode(callSuper = false)
public class ParameterPageDto implements Serializable {

    private static final long serialVersionUID = 7563528926268961597L;

    /**
     * 名称
     */
    @ApiModelProperty("参数名称")
    private String name;

}
