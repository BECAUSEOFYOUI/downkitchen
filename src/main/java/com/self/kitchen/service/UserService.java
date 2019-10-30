package com.self.kitchen.service;



import com.self.kitchen.dto.UserDto;
import com.self.kitchen.vo.ResultVo;

public interface UserService {
    ResultVo login(UserDto userDto);

    ResultVo loginOut(String username);

    ResultVo userMessage(String username);
}
