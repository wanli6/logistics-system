package util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class MailUtil {
    public static boolean sendMailCode(String mail,String code){
        try {
            // 设置SSL连接、邮件环境
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            Properties props = System.getProperties();
            props.setProperty("mail.smtp.host", "smtp.qq.com");
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.setProperty("mail.smtp.auth", "true");

            // 建立邮件会话
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                // 身份认证
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("3263597424@qq.com", "btuzqxiwgxvjchid"); // 账户 授权码
                }
            });

            // 建立邮件对象
            MimeMessage message = new MimeMessage(session);
            // 设置邮件的发件人、收件人、主题
            // 附带发件人名字
            message.setFrom(new InternetAddress("3263597424@qq.com"));
            message.setRecipients(Message.RecipientType.TO, mail);
            message.setSubject("物流管理系统邮箱验证码");
            // 文本部分
            String s = "您的验证码为\n"+code;
            message.setContent(s, "text/html;charset=UTF-8");
            message.saveChanges();
            // 发送邮件
            Transport.send(message);
            // 打印成功信息
            System.out.println("Send complete");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}