package org.forbes.comm.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/***
 * BasePageDto概要说明：页码抽象类
 * @author Huanghy
 */
@Data
public class BasePageDto {

	/***当前页码
	 */
	@ApiModelProperty(value="当前页码",example="1")
	@NotNull(message="当前页码为空")
	private Integer pageNo=1;
	/**每页显示记录数**/
	@ApiModelProperty(value="每页显示记录数",example="10")
	@NotNull(message="每页显示记录数为空")
	private Integer pageSize=10;
	
}
