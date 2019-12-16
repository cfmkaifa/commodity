package org.forbes.comm.vo;

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
 * @Date 2019/12/16 10:53
 * @Version 1.0
 **/

@Data
@ApiModel(description="商品附件")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductAttachVo implements Serializable{

    private static final long serialVersionUID = -1433192387031980700L;

    /**
     * 数据ID
     */
    @ApiModelProperty(value = "数据id")
    private Long dataId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer ordersSort;

    /**
     * 后缀
     */
    @ApiModelProperty(value = "后缀")
    private String suffix;

    /**
     * 中文名称
     */
    @ApiModelProperty(value = "中文名称")
    private String cnName;

    /**
     * 英文名称
     */
    @ApiModelProperty(value = "英文名称")
    private String enName;

    /**
     * 文件地址
     */
    @ApiModelProperty(value = "文件地址")
    private String filePath;

    /**
     * 0-大图，1-小图，3-中图
     */
    @ApiModelProperty(value = "附件类型0-大图，1-小图，3-中图")
    private String type;

}
