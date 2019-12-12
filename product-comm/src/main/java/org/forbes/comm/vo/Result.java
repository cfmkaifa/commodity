package org.forbes.comm.vo;

import lombok.Data;
import org.forbes.comm.constant.CommonConstant;

import java.io.Serializable;

/***
 * Result概要说明：统一返回错误
 * @author Huanghy
 */
@Data
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 成功标志
	 */
	private boolean success = true;

	/**
	 * 返回处理消息
	 */
	private String message = "操作成功！";
	/*****公共操作结果信息*****/
	public static final  String COMM_ACTION_MSG = "操作成功";
	public static final  String COMM_ACTION_ERROR_MSG = "操作失败";

	/******查询商品相关信息****/
	public static final  String SELECT_PRODUCT = "查询商品信息成功";
	public static final  String SELECT_ERROR_PRODUCT = "查询商品信息失败";

	/******添加商品相关信息****/
	public static final  String ADD_PRODUCT = "添加商品信息成功";
	public static final  String ADD_ERROR_PRODUCT = "添加商品信息失败";

	/******修改商品相关信息****/
	public static final  String UPDATE_PRODUCT = "添加商品信息成功";
	public static final  String UPDATE_ERROR_PRODUCT = "添加商品信息失败";

	/******查询分类相关信息****/
	public static final  String SELECT_CLASSIFY = "查询分类成功";
	public static final  String SELECT_ERROR_CLASSIFY = "查询分类失败";

	/******添加分类相关信息****/
	public static final  String ADD_CLASSIFY = "添加分类成功";
	public static final  String ADD_ERROR_CLASSIFY = "添加分类失败";

	/******删除分类相关信息****/
	public static final  String DEL_CLASSIFY = "删除分类成功";
	public static final  String DEL_ERROR_CLASSIFY = "删除分类失败";

	/******修改商品分类状态****/
	public static final  String UP_CLASSIFY_STATUS = "修改商品分类状态成功";
	public static final  String UP_CLASSIFY_ERROR_STATUS = "修改商品分类状态失败";

	/******修改商品分类****/
	public static final  String UP_CLASSIFY_NAME= "修改商品分类成功";
	public static final  String UP_CLASSIFY_ERROR_NAME = "修改商品分类失败";

	/**
	 * 返回代码
	 */
	private Integer code = CommonConstant.SC_OK_200;
	private String  bizCode = "0000";
	
	/**
	 * 返回数据对象 data
	 */
	private T result;

	public Result() {
		
	}
	
	/**
	 * 时间戳
	 */
	private long timestamp = System.currentTimeMillis();

	public void error500(String message) {
		this.message = message;
		this.code = CommonConstant.SC_INTERNAL_SERVER_ERROR_500;
		this.success = false;
	}

	public void success(String message) {
		this.message = message;
		this.code = CommonConstant.SC_OK_200;
		this.success = true;
	}
	
	public static Result<Object> error(String msg) {
		return error(CommonConstant.SC_INTERNAL_SERVER_ERROR_500, msg);
	}
	
	public static Result<Object> error(int code, String msg) {
		Result<Object> r = new Result<Object>();
		r.setCode(code);
		r.setMessage(msg);
		r.setSuccess(false);
		return r;
	}
	
	/***
	 * error方法慨述:
	 * @param bizCode
	 * @param msg
	 * @return Result<Object>
	 * @创建人 huanghy
	 * @创建时间 2019年12月7日 下午4:07:04
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	public  void error(String bizCode, String msg) {
		this.bizCode = bizCode;
		this.message = msg;
		this.success = false;
	}
	
	public static Result<Object> ok(String msg) {
		Result<Object> r = new Result<Object>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setMessage(msg);
		return r;
	}
	
	public static Result<Object> ok(Object data) {
		Result<Object> r = new Result<Object>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setResult(data);
		return r;
	}
}

