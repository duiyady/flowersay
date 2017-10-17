package test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class B {
	 public static void main(String[] args)  
		        throws JMSException  
		    {  
		        // ConnectionFactory ：连接工厂，JMS 用它创建连接  
		        ConnectionFactory connectionFactory =  
		            new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,  
		                "tcp://localhost:61616");  
		        //JMS 客户端到JMS Provider 的连接  
		        Connection connection = connectionFactory.createConnection();  
		        connection.start();  
		        // Session： 一个发送或接收消息的线程  
		        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);  
		        // Destination ：消息的目的地;消息发送给谁.  
		        // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置  
		        Destination destination = session.createQueue("my-queue");  
		        // 消费者，消息接收者  
		        MessageConsumer consumer = session.createConsumer(destination);  
		        while (true)  
		        {  
		            TextMessage message = (TextMessage)consumer.receive(1000);  
		            if (null != message)  
		                System.out.println("收到消息：" + message.getText());  
		            else  
		                break;  
		        }  
		        session.close();  
		        connection.close();  
		          
		    }  
}
