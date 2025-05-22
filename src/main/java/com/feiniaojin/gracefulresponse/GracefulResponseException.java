package com.feiniaojin.gracefulresponse;

import com.feiniaojin.gracefulresponse.data.ResponseStatus;

/**
 * 组件内部通用异常
 *
 * @author qinyujie
 */
public class GracefulResponseException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    /**
     * 响应码
     */
    private String code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * HTTP状态码
     */
    private int httpStatus = 500;

    public GracefulResponseException() {
    }

    public GracefulResponseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public GracefulResponseException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public GracefulResponseException(String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
    }

    public GracefulResponseException(String code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public GracefulResponseException(ResponseStatus responseStatus, Throwable cause) {
        super(responseStatus.getMsg(), cause);
        this.code = responseStatus.getCode();
        this.msg = responseStatus.getMsg();
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    /**
     * Get HTTP status code
     * @return HTTP status code
     */
    public int getHttpStatus() {
        return httpStatus;
    }

    /**
     * Set HTTP status code
     * @param httpStatus HTTP status code
     */
    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
}
