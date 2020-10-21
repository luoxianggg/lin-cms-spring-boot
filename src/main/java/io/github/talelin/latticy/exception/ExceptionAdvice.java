package io.github.talelin.latticy.exception;

import io.github.talelin.latticy.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ace_yj_hu on 16/12/30.
 * Node: provide global exception handler, include Default Exception and Customize Exception
 */

@RestController
@ControllerAdvice
public class ExceptionAdvice {

    private static Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    //包失效
    private static String PKG_INVALID = "出了点意外系统报错了（代码：00010），麻烦移步ITSM系统提交报障信息，我们将尽快解决。";
    //Redis服务器链接异常
    private static String REDIS_INVALID = "出了点意外系统报错了（代码：00020），麻烦移步ITSM系统提交报障信息，我们将尽快解决。";
    //服务运行异常
    private static String SERVER_ERROR = "出了点意外系统报错了（代码：00030），麻烦移步ITSM系统提交报障信息，我们将尽快解决。";
    //数据库执行异常
    private static String DB_PROCESS_ERROR = "出了点意外系统报错了（代码：00040），麻烦移步ITSM系统提交报障信息，我们将尽快解决。";
    //SQL执行异常
    private static String SQL_PROCESS_INVALID = "出了点意外系统报错了（代码：00050），麻烦移步ITSM系统提交报障信息，我们将尽快解决。";


    /**
     * Customize Exception
     */
    @ExceptionHandler(value = BaseException.class)
    public Response baseErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("---AlertException Handler---", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        return new Response().failure(e.getMessage());
    }

    /**
     * Customize Exception
     */
    @ExceptionHandler(value = ConfirmException.class)
    public Response confirmErrorHandler(HttpServletRequest req, ConfirmException e) throws Exception {
        logger.error("---ConfirmException Handler---", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        return new Response().ConfirmException(e.getCode(), e.getMessage());
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败", e);
        return new Response().failure("参数解析失败，请检查参数格式及类型是否正确。");
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidationException(MethodArgumentNotValidException e) {
        logger.error("参数验证失败", e);
        BindingResult bindingResult = e.getBindingResult();
        String msg = "";
        List<ObjectError> objErrorList = bindingResult.getAllErrors();
        for (ObjectError error : objErrorList) {
            msg += error.getDefaultMessage() + "/";
        }
        return new Response().failure(msg);
    }

    /**
     * 404
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Response handleNotFoundException(NoHandlerFoundException e) {
        logger.error("不支持当前请求url", e);
        return new Response().failure("resource_not_available");
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法", e);
        return new Response().failure("request_method_not_supported");
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Response handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型", e);
        return new Response().failure("content_type_not_supported");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BadSqlGrammarException.class)
    public Response handleBadSqlGrammarException(BadSqlGrammarException e) {
        logger.error("SQL执行异常");
        return new Response().failure(SQL_PROCESS_INVALID);
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        logger.error("服务运行异常", e);
        //return new Response().failure(e.getMessage());
        return new Response().failure(SERVER_ERROR);
    }

}