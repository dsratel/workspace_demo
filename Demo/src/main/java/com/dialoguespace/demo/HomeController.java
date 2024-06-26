package com.dialoguespace.demo;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dialoguespace.member.MemberDTO;
import com.dialoguespace.utils.EncryptionUtils;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private final EncryptionUtils encryptionUtils;
	
	@Autowired
	public HomeController(EncryptionUtils encryptionUtils) {
		this.encryptionUtils = encryptionUtils;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(String uri, Locale locale, Model model, HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {
		logger.info("Welcome home! The client locale is {}.", locale);
//		System.out.println("========== Home Controller 진입 ==========");
//		System.out.println("요청 uri : " + uri);
		
		MemberDTO dto = (MemberDTO)request.getSession().getAttribute("loginSession");
		String redirect = "";
		if(dto == null) {
			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
			
			String formattedDate = dateFormat.format(date);
			
			model.addAttribute("serverTime", formattedDate );
			model.addAttribute("requestURI", uri);
			
			// KeyPariGenerator 인스턴스 생성(RSA 알고리즘)
			encryptionUtils.genRsaInstance(model);
			
			redirect = "home";
		} else {
			if(dto.getId().equals("devvv") && dto.getId() != null) {
				redirect = "redirect:/master/home";
			} else {
				redirect = "redirect:/board/toList";
			}
		}
		return redirect;
	}
	
	@RequestMapping(value="/error")
	public String errorPage() {
		return "errorPage";
	}
	
}
