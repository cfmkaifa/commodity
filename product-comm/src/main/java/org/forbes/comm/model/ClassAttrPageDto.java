package org.forbes.comm.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/13 11:02
 * @Version 1.0
 **/
@Data
public class ClassAttrPageDto implements Serializable {
    private static final long serialVersionUID = 8055341873746064011L;

    /**
     * 属性名称
     */
    @ApiModelProperty(value ="属性名称")
    private String name;

}
