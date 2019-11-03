package com.self.kitchen.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class SmsService {
    public static void send(String phone,String key){

        //第二个参数为自己独有的accessKeyid，第三个参数为自己独有的accessKeySecret
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                "LTAI4FxWZtGDKqR2sW2DFDeZ","hFuohjqPAsmCmqV7p3zgIlnvEH4MDJ");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();//组装请求对象
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);//设置post提交
        request.setDomain("dysmsapi.aliyuncs.com");//短信API产品域名（接口地址固定，无需修改）
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "男人帮登录");//短信签名
        request.putQueryParameter("TemplateCode", "SMS_176525775");
        request.putQueryParameter("TemplateParam", "{code:"+key+"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    //阿里模板
    /*public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FxWZtGDKqR2sW2DFDeZ", "hFuohjqPAsmCmqV7p3zgIlnvEH4MDJ");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "15039112281");
        request.putQueryParameter("SignName", "男人帮登录");
        request.putQueryParameter("TemplateCode", "SMS_176525775");
        request.putQueryParameter("TemplateParam", "{\"code\":\"123456\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }*/

    /*public static void main(String[] args) {
        SmsService.send("15039112281", "123456");
    }*/
    /*public static void main(String[] args) {
        //发短信
        SmsService.send("15039112281", "1234");


        System.out.println("短信接口返回的数据----------------");

    }*/

    /**
     * 生成六位随机数
     *
     * @return
     */
    public static String createRandomVcode() {
        //验证码
        StringBuilder vcode = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            vcode.append((int) (Math.random() * 9));
        }
        return vcode.toString();
    }

    public static void main(String[] args) {
        SmsService.send("15090674580",SmsService.createRandomVcode());
    }
}
