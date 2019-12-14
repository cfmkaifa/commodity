package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/11 14:01
 * @Version 1.0
 **/
@Data
@ApiModel(description="商品分类分页dto")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductClassifyPageDto implements Serializable{

    private static final long serialVersionUID = -9171872919800583064L;

    /**
     * 分类名称
     */
    @ApiModelProperty(value ="分类名称")
    private String name;

    /**
     * 等级
     */
    @ApiModelProperty(value ="等级",example = "1")
    private Long level;

}
