package com.self.kitchen.service;



import com.self.kitchen.dto.UserDto;
import com.self.kitchen.dto.UserMesDto;

import com.self.kitchen.vo.ResultVo;

public interface UserService {
    ResultVo login(UserDto userDto);

    ResultVo loginOut(String username);

    ResultVo userMessage(String username);

    ResultVo updateUserMessage(UserMesDto userMesDto, String username);

    ResultVo register(String username, String password);

    ResultVo validateCode(String code);
}
