package io.github.talelin.latticy.exception;

/**
 * Created by Ranz
 * Date:  2017/12/4 11:36
 * Remark:code:0 表示请求成功
 *        code:1 表示请求异常，页面弹窗报错
 *        code:2 表示异常捕获后弹出可选择的弹出框
 */
public class ConfirmException extends Exception {
    private int code;
    public ConfirmException(int code,String message){
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
