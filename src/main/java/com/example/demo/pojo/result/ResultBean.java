package com.example.demo.pojo.result;

import com.example.demo.exception.ErrorEnum;
import com.example.demo.utils.IdUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author dongxu
 * @create 2023-04-05 下午2:30
 */
@Getter
@Setter
@Accessors(chain = true)
public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 200;
    public static final int FAIL = 400;
    private String message = "success";
    private int status = SUCCESS;
    private T data;
    private String requestId = IdUtil.generateRequestId();

    public ResultBean() {
    }

    public ResultBean(int status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.message = msg;
    }

    public boolean isSuccess() {
        return status == SUCCESS;
    }

    public static <T> ResultBean<T> success() {
        return new ResultBean<>();
    }

    public static <T> ResultBean<T> fail() {
        return new ResultBean<T>().setMessage("fail").setStatus(FAIL);
    }

    public static <T> ResultBean<T> fail(String msg) {
        return new ResultBean<T>().setMessage(msg).setStatus(FAIL);
    }

    public static <T> ResultBean<T> fail(ErrorEnum errorEnum) {
        return new ResultBean<T>().setMessage(errorEnum.getMessage()).setStatus(errorEnum.getCode());
    }

    public static <T> ResultBean<T> fail(Integer code, String msg) {
        return new ResultBean<T>().setMessage(msg).setStatus(code);
    }

    public static <T> ResultBean<T> ok(T data) {
        return new ResultBean<T>().setStatus(SUCCESS).setData(data);
    }

    /**
     * 判断是否有异常。如果有，则抛出异常
     * 如果没有，则返回数据
     */
    @JsonIgnore
    public T getCheckedData() {
        checkError();
        return data;
    }

    private void checkError() {
        if (status == SUCCESS) {
            return;
        }
        throw new RuntimeException(message);
    }

    public static void main(String[] args) {
        String data = "data";
        ResultBean ok = ResultBean.ok(data);
        String checkedData = ResultBean.ok(data).getCheckedData();
        System.out.println(ok.getStatus());
        System.out.println(ok.getData());
        System.out.println(ok.getMessage());
    }

}
