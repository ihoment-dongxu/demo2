package com.example.demo.pojo.result;

import com.example.demo.utils.IdUtil;
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

    public static ResultBean success(){
        return new ResultBean();
    }

    public static ResultBean fail(){
        return new ResultBean().setMessage("fail").setStatus(FAIL);
    }

    public static ResultBean fail(String msg){
        return new ResultBean().setMessage(msg).setStatus(FAIL);
    }

    public static <T> ResultBean ok(T data){
        return new ResultBean().setStatus(SUCCESS).setData(data);
    }


    public static void main(String[] args) {
        String data = "data";
        ResultBean ok = ResultBean.ok(data);
        System.out.println(ok.getStatus());
        System.out.println(ok.getData());
        System.out.println(ok.getMessage());
    }

}
