package com.self.kitchen.vo;

import lombok.Data;

@Data
public class ResultVo {
    private Object data;
    private String msg;
    private int code;

    public static ResultVo setOK(Object obj){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(200);
        resultVo.setMsg("OK");
        resultVo.setData(obj);
        return resultVo;
    }
    public static ResultVo setERROR(){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(400);
        resultVo.setMsg("ERROR");
        resultVo.setData(null);
        return resultVo;
    }
    public static ResultVo setResult(boolean flag,Object obj) {
        if (flag) {
           return setOK(obj);
        } else {
            return setERROR();
        }
    }


}
