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
 * @Date 2019/12/13 11:02
 * @Version 1.0
 **/
@Data
@ApiModel(description="分类属性分页dto")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ClassAttrPageDto implements Serializable {
    private static final long serialVersionUID = 8055341873746064011L;


    /**
     * id
     */
    @ApiModelProperty(value = "id",example = "0")
    private Long id;

    /**
     * 属性名称
     */
    @ApiModelProperty(value ="属性名称")
    private String name;

}
