package org.forbes.comm.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/11 13:21
 * @Version 1.0
 **/
@Data
@ApiModel(description="商品分类信息")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductClassifyDto  implements Serializable{

    private static final long serialVersionUID = 8503607684293277436L;

    /**
     *  id
     */
    @ApiModelProperty(value ="主键",example = "0")
    private Long id;

    /**
     * 创建人
     */
    @ApiModelProperty(value ="创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value ="更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date updateTime;

    /**
     * 分类编码
     */
    @ApiModelProperty(value ="分类编码",required = true)
    @NotEmpty(message = "分类编码不能为空")
    private String classifySn;

    /**
     * 分类名称
     */
    @ApiModelProperty(value ="分类名称",required = true)
    @NotEmpty(message = "分类名称不能为空")
    private String name;

    /**
     * 父级id
     */
    @ApiModelProperty(value ="父级id")
    private Long parentId;

    /**
     * 等级
     */
    @ApiModelProperty(value ="等级",required = true)
    @NotEmpty(message = "等级不能为空")
    private Long level;

    /**
     * 状态:0未启用1-启用
     */
    @ApiModelProperty(value ="状态",required = true)
    private Long state;

    @ApiModelProperty("添加分类关联分类属性信息")
    private List<ClassifyAttributeDto> classifyAttributeDtos;

    @ApiModelProperty("添加分类关联分类规格信息")
    private List<ProSpecficDto> proSpecficDtos;
}
