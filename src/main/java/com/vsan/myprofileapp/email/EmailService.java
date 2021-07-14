package com.vsan.myprofileapp.email;




import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private Configuration config;
	
	
	public boolean send(String to, Map<String, Object> model) {
		
		try {
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			
			Template t = config.getTemplate("emailConfirmation.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
			
			helper.setSubject("Conferma il tuo account");
			helper.setFrom("userservice@outlook.it");
			helper.setTo(to);
			helper.setText(html, true);
			
			sender.send(message);
			log.info("Mail sended successfully to: "+to);
			
			return true;
			
			
		} catch (IOException | TemplateException | MessagingException e) {
			log.error("Mail sending failure: "+e.getMessage());
			return false;
		}
	}

}
