
package com.duiya.utils;

import java.io.File;
import java.util.Date;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.duiya.model.Mail;


@Component
public class MailSender {
//	private Logger logger = LoggerFactory.getLogger(MailSender.class);
	@Autowired
	// 注入 配置文件中的 javaMailSender
	private JavaMailSender javaMailSender;

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	/**
	 * 发送简单的 文字邮件
	 * 
	 * @param mail
	 */

	public void sendSimpleMail(Mail mail) {
		SimpleMailMessage simpleMail = new SimpleMailMessage();
		simpleMail.setFrom(mail.getFrom());
		simpleMail.setTo(mail.getTo());
		simpleMail.setSubject(mail.getSubject());
		simpleMail.setSentDate(new Date());
		simpleMail.setText(mail.getContentText());
		javaMailSender.send(simpleMail);
	}

	/**
	 * 发送 html 形式的 邮件 未启用
	 * 
	 * @param mail
	 */
	public void sendHtmlMail(Mail mail) {
		MimeMessage messageMail = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(messageMail, true, "UTF-8");
			helper.setFrom(mail.getFrom());
			helper.setTo(new InternetAddress("\"" + MimeUtility.encodeText("qq邮箱") + "\"<" + mail.getTo() + ">"));
			helper.setSentDate(new Date());
			helper.setReplyTo(mail.getFrom());
			helper.setSubject(mail.getSubject());// 主题
			helper.setText(mail.getContentText(), true);
			// 设置邮件内容！！！
			javaMailSender.send(messageMail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送 html形式的 带文件的 邮件
	 * 
	 * @param mail
	 */
	public void sendFileMail(Mail mail) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom("joecqupt@126.com");
			helper.setTo(new InternetAddress("\"" + MimeUtility.encodeText("qq邮箱") + "\"<469391363@qq.com>"));
			helper.setSentDate(new Date());
			helper.setReplyTo("joecqupt@126.com");
			helper.setSubject("html mail test");// 主题
			helper.setText("<html><head><meta charset='utf-8'/></head><body><h1>hello i am joe!</h1></body></html>",
					true);
			helper.addAttachment(MimeUtility.encodeText("附件.jpg"), new File("images/ys.jpg"));
			// helper.addInline(MimeUtility.encodeText("pic01.jpg"), new
			// File("images/ys.jpg"));
			// 现在一般不这样内嵌图片了！！ 一般用图片的线上的地址
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
