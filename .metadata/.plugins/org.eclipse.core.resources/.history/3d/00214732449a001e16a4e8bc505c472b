package com.dialoguespace.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dialoguespace.common.CommonService;
import com.dialoguespace.member.MemberDTO;
import com.dialoguespace.member.MemberService;

@Controller
@RequestMapping(value="/test")
public class TestController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	CommonService commonService;
	
	@GetMapping(value="")
	public String toTestView(String m_id, Model model, HttpServletRequest request) throws Exception {
		System.out.println("수정할 회원 ID : " + m_id);
		if(m_id != null) {
			MemberDTO dto = memberService.toEditMember(m_id);
			model.addAttribute("dto", dto);		
			System.out.println("request.getSession() : " + request.getSession());	// org.apache.catalina.session.StandardSessionFacade@1ba9e317
			System.out.println("root path : " + request.getSession().getServletContext().getRealPath("/"));	// D:\workspace_demo\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Demo\
			
			// 사진 경로
			String path = commonService.getPath(dto.getFileno());
			String filePath = path.substring(path.lastIndexOf("resources"));
			model.addAttribute("filePath", filePath);
			
			return "testView";
		} else {
			return "errorPage";
		}
	}
}
