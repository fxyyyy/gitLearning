package com.fxy.assist;

/**
 * @Title: ErrEnum
 * @Description: 枚举辅助类( 由Msg的返回值来判断是否调用 )
 * @param: 
 * @author fxy 
 * @date 2018年3月5日
 */
public enum ErrEnum {
	
	/**
     * 登录密码错误
     */
	PASSWORD_LOGIN_ERROR("passwordLoginErr","密码错误次数+1，达到三次2分钟内不能登录！"),
	
	/**
     * 登录密码错误达到3次错误
     */								 
	MISS_LOGIN_ERROR("missLoginErr","密码错误三次禁止登录，请等待系统刷新时间！"),
	
	/**
     * 允许登录时间错误
     */
	ALLOW_LOGIN_ERROR("allowLoginErr","当前时间不允许登录！"),
	
	
    /**
     * 账号错误
     */
	NAME_LOGIN_ERROR("nameLoginErr","账号不存在，请重新输入！"),
	
	/**
	 * 添加数据到数据库错误
	 */
	ADD_ERROR("addErr","添加错误，请重新确认封装的实体类！");
    
    private String errorCode;
    private String errorMessage;
    
    private ErrEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
