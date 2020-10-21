package io.github.talelin.latticy.exception;

/**
 * Created by ace_yj_hu on 16/12/30.
 * Node: provide customize throws exception, the controller should
 *       add statement "throws Exception"
 */
public class BaseException extends Exception {

    public BaseException(String message){
        super(message);
    }
}
