package com.self.kitchen.vo;

import lombok.Data;

@Data
public class ResultVo<T> {
    private T data;
    private String msg;
    public ResultVo(){

    }
    public ResultVo(T data,String msg){
        this.data=data;
        this.msg=msg;
    }
    private static ResultVo setOK(Object data){
        ResultVo resultVo = new ResultVo();
        resultVo.setData(data);
        return resultVo;
    }
}
