package org.forbes.comm.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/11 13:20
 * @Version 1.0
 **/
@Data
@ApiModel(description="商品分类对象")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductClassifyVo implements Serializable{

    private static final long serialVersionUID = -7279164568566078343L;

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
    @ApiModelProperty(value ="分类编码")
    private String classifySn;

    /**
     * 分类名称
     */
    @ApiModelProperty(value ="分类名称")
    private String name;

    /**
     * 父级id
     */
    @ApiModelProperty(value ="父级id",example = "0")
    private Long parentId;

    /**
     * 等级
     */
    @ApiModelProperty(value ="等级",example = "1")
    private Long level;

    /**
     * 状态:0未启用1-启用
     */
    @ApiModelProperty(value ="状态",example = "0")
    private Long state;

    @ApiModelProperty(value = "父级分类下子集分类",required = true)
    private List<ProductClassifyVo> productClassifyVoList;
}
