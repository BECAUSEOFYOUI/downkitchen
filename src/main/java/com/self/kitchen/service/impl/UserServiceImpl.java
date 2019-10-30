package com.self.kitchen.service.impl;

import com.self.kitchen.dao.FoodDao;
import com.self.kitchen.dao.MariableDao;
import com.self.kitchen.dao.UserDao;
import com.self.kitchen.dto.UserDto;
import com.self.kitchen.entity.User;
import com.self.kitchen.service.UserService;
import com.self.kitchen.utils.SmsService;
import com.self.kitchen.vo.ResultVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    @Autowired
    FoodDao foodDao;

    @Autowired
    MariableDao mariableDao;

    @Override
    public ResultVo login(UserDto userDto) {
        int result = userDao.login(userDto);
        if(result==1){
            //创建主题对象
            Subject subject = SecurityUtils.getSubject();
            //创建令牌，用户名密码和令牌
            UsernamePasswordToken token = new UsernamePasswordToken(userDto.getUsername(),userDto.getPassword());

            //登陆告诉shiro登陆成功
            subject.login(token);
            return ResultVo.setOK("OK");
        }
        return ResultVo.setERROR();
    }

    @Override
    public ResultVo register(String username) {

        String code = SmsService.createRandomVcode();
        //创建对象
        Subject subject = SecurityUtils.getSubject();
        //将验证码先存在session中，用于校验登录;  后面改为存在Redis中
        subject.getSession().setAttribute("code", code);

        //手机号不为空时，发送短信验证码
        if(username != null) {
            SmsService.send(username, code);
            return ResultVo.setOK("OK");
        }

        return ResultVo.setERROR();
    }

    @Override
    public ResultVo validateCode(String code) {
        System.out.println(code);
        Subject subject = SecurityUtils.getSubject();
        //别老是忘转换数据类型
        String code1 = (String) subject.getSession().getAttribute("code");
        System.out.println(code1);

        //根据页面修改以下代码
        if (code.equals(code1)) {
            return ResultVo.setOK("OK");
        }

        return ResultVo.setERROR();
    }

    @Override
    public User selectUserByUsername(String username) {

        return userDao.selectUserByUsername(username);

    }

    @Override
    public void save(String username, String password) {
        userDao.save(username, password);
    }

    /*@Override
    public ResultVo selectVegetableByCaiPin(User user) {
        Integer uid = user.getId();
        //System.out.println(uid);
        List<Foods> foods = foodDao.selectFoodByUid(uid);//通过用户id得到菜品
        System.out.println(foods);

        HashMap<String, List<Mariable>> map = new HashMap<>();
        List<Mariable> mariables = new ArrayList<>();

        String foodname = null;
        for(Foods food : foods) {//循环菜
            foodname = food.getFoodsName();//菜品名
            int foodid = food.getId();//才品id

            mariables = mariableDao.selectMariableByFoodId(foodid);
            map.put(foodname, mariables);//菜名和对应的材料
        }

        return ResultVo.setOK(map);
    }*/
}
