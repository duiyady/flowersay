package com.duiya.mq;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.duiya.dao.OrderDao;
import com.duiya.model.Mail;
import com.duiya.utils.MailSender;
import com.duiya.utils.PhoneUtil;

public class QueueMessageListener implements MessageListener {
	private Logger logger = LoggerFactory.getLogger(QueueMessageListener.class);

	@Autowired
	private MailSender mailSender;

	// 当收到消息时，自动调用该方法
	public void onMessage(Message message) {
		TextMessage tm = (TextMessage) message;
		try {
			logger.info("ConsumerMessageListener收到了文本消息：\t" + tm.getText());
			JSONObject jb = JSONObject.parseObject(tm.getText());
			int code = Integer.valueOf(jb.get("code").toString());
			//11是发送短信
			if(code == 11) {
				Map map = (Map) jb.get("message");
				PhoneUtil.sendCodeSms(map);
			}else if(code == 1) {//code == 1 提醒发货
				String mess = jb.get("message").toString();
				String mailMsg = "订单号为：" + mess + "的金主爸爸催你发单了,快去处理吧";
				Mail mail = new Mail();
				mail.setContentText(mailMsg);
				//mail.setTo(orderInfo.get("managerMail").toString());
				mail.setSubject("金主催单了");
				mail.setTo("duiyady@163.com");
				mailSender.sendSimpleMail(mail);
				logger.info("---->成功消费");
			}else if(code == 2){//2为有新订单了
				String mess = jb.get("message").toString();
				String mailMsg = "有新订单了，订单号为:" + mess + ",快去处理吧！！！！";
				Mail mail = new Mail();
				mail.setContentText(mailMsg);
				//mail.setTo(orderInfo.get("managerMail").toString());
				mail.setSubject("有人下单 了");
				mail.setTo("duiyady@163.com");
				mailSender.sendSimpleMail(mail);
				logger.info("---->成功消费");
			}else if(code == 3) {//3有人退单
				String mess = jb.get("message").toString();
				String mailMsg = "有退单了，订单号为:" + mess + "取消了订单，快去处理吧";
				Mail mail = new Mail();
				mail.setContentText(mailMsg);
				//mail.setTo(orderInfo.get("managerMail").toString());
				mail.setSubject("有人退单 了");
				mail.setTo("duiyady@163.com");
				mailSender.sendSimpleMail(mail);
				logger.info("---->成功消费");
			}else if(code == 4) {//收货了
				String mess = jb.get("message").toString();
				String mailMsg = "订单号为：" + mess + "的订单确认到达客户手中了";
				Mail mail = new Mail();
				mail.setContentText(mailMsg);
				mail.setSubject("有人退单 了");
				mail.setTo("duiyady@163.com");
				mailSender.sendSimpleMail(mail);
				logger.info("---->成功消费");
			}
		} catch (JMSException e) {
			logger.error("消费消息失败了", e);
			e.printStackTrace();
		}
	}

}