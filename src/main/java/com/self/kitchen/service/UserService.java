package com.self.kitchen.service;



import com.self.kitchen.dto.UserDto;
import com.self.kitchen.entity.User;
import com.self.kitchen.vo.ResultVo;

public interface UserService {
    ResultVo login(UserDto userDto);

    ResultVo loginOut(String username);

    ResultVo userMessage(String username);

    ResultVo register(String username, String password);

    User selectUserByUsername(String username);

    ResultVo validateCode(String code);

    void save(String username, String password);
}
