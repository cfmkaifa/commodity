package org.forbes.comm.enums;
/***
 * BizResultEnum概要说明：业务系统错误代码
 * @author Huanghy
 */
public enum BizResultEnum {
	/***
	 * 004-商品模块
	 * 功能暂无-表示通用异常
	 * 001-商品模块下商品分类管理
	 */
	ENTITY_EMPTY("004001000","对象为空",""),
	/*******商品信息*****/
	PRODUCT_CODE_EXIST("004001001","商品编码存在","%s对应商品编码存在"),
	PRODUCT_STATUS_NO_EXISTS("004001002","商品状态不存在","%s对应商品状态不存在"),

	/*******商品分类信息*****/
	EMPTY("004001","参数为空","%s为空"),
	PRODUCT_CLASSIFY_EXIST("004001001","商品分类已存在","%s对应商品分类已存在"),
	PRODUCT_CLASSIFY_NOT_EXIST("004001002","商品分类不存在","s%对应商品分类不存在"),
	PRODUCT_CHILD_EXISTS("004001003","商品分类下包含子级分类","s%对应商品分类下包含子级分类"),
	CLASSIFY_STATUS_NOT_EXIST("004001004","商品分类状态不存在","s%对应商品分类状态不存在"),
	CLASSIFY_SN_EXIST("004001004","商品分类编码已存在","s%对应商品分类编码已存在"),
	PRO_CLAS_NAME_SAME("004001005","存在相同商品分类名称","s%对应存在相同商品分类名称"),


	/******分类属性信息*****/
	CLASSIFY_ATTRIBUTE_EXIST("004002001","商品分类属性存在","%s对应的商品分类属性存在"),
	CLASSIFY_ATTRIBUTE_ID_EXIST("004002003","商品分类ID不存在","%s对应的商品分类ID不存在"),
	BATCH_ADD_ERROR("004002004","分类属性不存在","s%对应分类属性不存在"),
	REPETITION_ATTR("004002005","存在相同名称分类属性","s%对应属性名称重复"),


	/******规格信息*****/
	PRO_SPEC_CLASSIFY_ID("004003001","商品分类ID不存在","s%对应商品分类ID不存在"),
	PRO_SPEC_EXIST("004003002","存在相关规格","s%存在对应相关规格"),
	PRO_SPEC_CLASSFY_ID_EXIST("004002003","商品分类ID不存在","%s对应的商品分类ID不存在"),
	PRO_SPEC_NAME_EXIST("004002004","规格名称已存在","s%对应规格名称已存在"),
	PRO_SPEC_NAME_SAME("004002005","存在相同规格名称","s%对应存在相同规格名称"),
	;

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
