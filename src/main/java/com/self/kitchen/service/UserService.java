package com.self.kitchen.service;



import com.self.kitchen.dto.UserDto;
import com.self.kitchen.entity.User;
import com.self.kitchen.vo.ResultVo;

public interface UserService {
    ResultVo login(UserDto userDto);

    ResultVo register(String username);

    ResultVo validateCode(String code);

    User selectUserByUsername(String username);

    void save(String username, String password);

    //ResultVo selectVegetableByCaiPin(User user);
}
