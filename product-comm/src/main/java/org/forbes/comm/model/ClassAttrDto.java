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
 * @ClassName 分类属性dto
 * @Description TODO
 * @Author
 * @Date 2019/12/13 11:58
 * @Version 1.0
 **/
@Data
@ApiModel(description="分类属性")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ClassAttrDto implements Serializable{

    private static final long serialVersionUID = -2720585649050208895L;
    /**商品分类id
     */
    @ApiModelProperty(value = "商品分类id",required = true,example = "0")
    @NotEmpty(message = "商品分类id不为空")
    private Long classifyId;

    /**
     *分类属性名称
     **/
    @ApiModelProperty(value = "分类属性名称集合",required = true,example = "0")
    @NotEmpty(message = "分类属性名称不能为空")
    private List<ClassifyAttributeDto> attrnames;

}
