package org.forbes.comm.enums;
/***
 * BizResultEnum概要说明：业务系统错误代码
 * @author Huanghy
 */
public enum BizResultEnum {
	/***
	 * 002-商品模块
	 * 功能暂无-表示通用异常
	 * 001-商品模块下商品分类管理
	 */
	ENTITY_EMPTY("001001000","对象为空",""),
	/*******商品信息*****/
	PRODUCT_CODE_EXIST("001001001","商品编码存在","%s对应商品编码存在"),

	/*******商品分类信息*****/
	EMPTY("002001","参数为空","%s为空"),
	PRODUCT_CLASSIFY_EXIST("002001001","商品分类已存在","%s对应商品分类已存在"),
	PRODUCT_CLASSIFY_NOT_EXIST("002001002","商品分类不存在","s%对应商品分类不存在"),
	PRODUCT_CHILD_EXISTS("002001003","商品分类下包含子级分类","s%对应商品分类下包含子级分类"),
	CLASSIFY_STATUS_NOT_EXIST("002001004","商品分类状态不存在","s%对应商品分类状态不存在"),;

	/**错误编码业务系统代码+功能编码+错误代码**/
	private String bizCode;
	/**错误描述****/
	private String bizMessage;
	/**带格式错误描述****/
	private String bizFormateMessage;

	/***
	 * 构造函数:
	 * @param bizCode
	 * @param bizMessage
	 * @param bizFormateMessage
	 */
	BizResultEnum(String bizCode, String bizMessage, String bizFormateMessage){
		this.bizCode = bizCode;
		this.bizMessage = bizMessage;
		this.bizFormateMessage = bizFormateMessage;
	}

	/** 
	 * @return bizCode 
	 */
	public String getBizCode() {
		return bizCode;
	}

	/** 
	 * @param bizCode 要设置的 bizCode 
	 */
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	/** 
	 * @return bizMessage 
	 */
	public String getBizMessage() {
		return bizMessage;
	}

	/** 
	 * @param bizMessage 要设置的 bizMessage 
	 */
	public void setBizMessage(String bizMessage) {
		this.bizMessage = bizMessage;
	}

	/** 
	 * @return bizFormateMessage 
	 */
	public String getBizFormateMessage() {
		return bizFormateMessage;
	}

	/** 
	 * @param bizFormateMessage 要设置的 bizFormateMessage 
	 */
	public void setBizFormateMessage(String bizFormateMessage) {
		this.bizFormateMessage = bizFormateMessage;
	}
}
