

# 📧 Spring Boot 邮件发送服务（wth-system-email-push）

## 📌 项目简介

本项目基于 **Spring Boot**，集成了邮件发送功能，支持通过 **SMTP 协议** 实现模板邮件发送，适用于系统通知、验证码发送、注册激活等场景。

同时结合 **FreeMarker** 模板引擎，实现动态渲染邮件内容，增强用户体验。

---

## 🛠️ 技术栈

| 技术 | 说明 |
|------|------|
| Spring Boot | 主体框架 |
| spring-boot-starter-mail | 邮件发送功能支持 |
| FreeMarker | 邮件模板渲染 |
| Druid | 阿里数据库连接池 |
| Lombok | 简化代码开发 |
| Maven | 项目管理与构建工具 |

---

## 📦 项目结构

```bash
wth-system-email-push/
├── src/
│   ├── main/
│   │   ├── java/              # Java 源码
│   │   ├── resources/
│   │   │   ├── templates/     # FreeMarker 邮件模板
│   │   │   └── application.yml # 项目配置文件
├── pom.xml                    # Maven 配置文件
```

---

## ⚙️ 配置说明（`application.yml` 示例）

```yaml
spring:
  mail:
    host: smtp.qq.com
    username: your_email@qq.com
    password: your_app_password
    port: 587
    protocol: smtp
    default-encoding: UTF-8
    properties:
      mail.smtp.auth: true
      mail.smtp.starttls.enable: true

  freemarker:
    suffix: .ftl
    charset: UTF-8
    template-loader-path: classpath:/templates/
```

> 📌 注意：`password` 是邮箱的 **SMTP 授权码**，不是邮箱登录密码！

---

## 🚀 快速开始

### 1. 克隆项目

```bash
git clone https://github.com/your-username/wth-system-email-push.git
cd wth-system-email-push
```

### 2. 修改配置

根据自己的邮箱服务提供商（如QQ邮箱、163邮箱、Gmail等）修改 `application.yml` 配置。

### 3. 构建运行

```bash
mvn clean install
java -jar target/wth-system-email-push-0.0.1.jar
```

---

## ✉️ 示例代码（发送模板邮件）

```java
@Autowired
private JavaMailSender mailSender;

@Autowired
private Configuration freemarkerConfig;

public void sendMail(String to, String subject, Map<String, Object> model) throws Exception {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setFrom("your_email@qq.com");
    helper.setTo(to);
    helper.setSubject(subject);

    Template template = freemarkerConfig.getTemplate("email-template.ftl");
    String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    helper.setText(html, true);

    mailSender.send(message);
}
```

---

## 📄 License

本项目采用 [MIT License](LICENSE) 许可证，欢迎使用和修改。

---

## 🙋‍♂️ 联系与贡献

如有问题或建议，欢迎提交 Issue 或 PR，我们一起优化项目！

