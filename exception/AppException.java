package com.lc.lcserve.exception;

import com.lc.lcserve.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
public class AppException extends RuntimeException {

    private int code = 500;
    private String msg = "服务器异常";

    @ExceptionHandler(value = ServiceException.class)
    public R serviceException(Exception e) {
        log.error("业务异常", e);
        return R.error(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public R excertionError(Exception e) {
        log.error("系统错误", e);
        return R.error("系统错误");
    }

    public AppException(AppExceptionCodeMsg appExceptionCodeMsg){
        super();
        this.code = appExceptionCodeMsg.getCode();
        this.msg = appExceptionCodeMsg.getMsg();

    }

    public AppException(int code,String msg){
        super();
        this.code = code;
        this.msg = msg;

    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
