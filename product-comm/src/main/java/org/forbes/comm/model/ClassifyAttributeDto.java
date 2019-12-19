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
 * @Date 2019/12/12 16:34
 * @Version 1.0
 **/
@Data
@ApiModel(description="分类属性")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ClassifyAttributeDto implements Serializable {

    private static final long serialVersionUID = 7045638268887777956L;


    /**
     * id
     */
    @ApiModelProperty("分类属性id")
    @NotEmpty(message = "分类属性不为空")
    private Long id;
    /**
     * 名称
     */
    @ApiModelProperty("分类属性名称")
    private String name;


    /**商品分类id
     */
    @ApiModelProperty("商品分类id")
    private Long classifyId;
}
