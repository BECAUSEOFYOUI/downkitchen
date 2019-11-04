package com.self.kitchen.service;



import com.self.kitchen.dto.UserDto;
import com.self.kitchen.dto.UserMesDto;

import com.self.kitchen.vo.ResultVo;

public interface UserService {
    ResultVo login(UserDto userDto);

    ResultVo loginOut(String USERTOKEN);

    ResultVo userMessage(String USERTOKEN);

    ResultVo updateUserMessage(UserMesDto userMesDto, String USERTOKEN);

    ResultVo register(String username, String password);

    ResultVo validateCode(String code);

    ResultVo updatePwd(String usertoken,String password);
}
