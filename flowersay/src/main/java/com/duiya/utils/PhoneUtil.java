package com.duiya.utils;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;


/**
 * 发送短信
 * @author duiya
 *
 */
public class PhoneUtil {
	private static final Logger logger = LoggerFactory.getLogger(PhoneUtil.class);
	 //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = AlidayuSet.accessKeyId;
    static final String accessKeySecret = AlidayuSet.accessKeySecret;

    /**
     * 发送短信
     * @param map
     * @throws ClientException
     */
    public static boolean sendSms(Map<String,String> map){
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(map.get("phoneNumber"));
        request.setSignName(AlidayuSet.signName);
        request.setTemplateCode(map.get("templateCode"));
        request.setTemplateParam("{\"name\":\"" + map.get("name") + "\"}" );

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse;
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
			if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
	        	return true;
	        }else {
	        	logger.error("failed to send message,the error is：" + sendSmsResponse.getCode());
	        	return false;
	        }
		} catch (ServerException e) {
			logger.error("failed to send message", e);
		} catch (ClientException e) {
			logger.error("failed to send message", e);
		}
		return false;
    }
    public static void main(String[] args) throws ClientException {
		Map<String,String> map = new HashedMap();
		map.put("phoneNumber", "13637743693");
		map.put("templateCode", AlidayuSet.TemplateCode5);
		map.put("name", "大鱼测试");
		System.out.println(PhoneUtil.sendSms(map));
	}
}
