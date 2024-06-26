package com.dialoguespace.master;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dialoguespace.board.BoardController;
import com.dialoguespace.comment.CommentController;
import com.dialoguespace.common.CommonService;
import com.dialoguespace.member.MemberController;
import com.dialoguespace.member.MemberDTO;
import com.dialoguespace.member.MemberService;

@Controller
@RequestMapping(value="/master")
public class MasterController {
	
	private final MasterService masterService;
	private final CommonService commonService;
	private final CommentController commentController;
	private final BoardController boardController;
	private final MemberController memberController;
	private final MemberService memberService;
	private final HttpSession session;
	
	@Autowired
	public MasterController(MasterService masterService, CommonService commonService, CommentController commentController, BoardController boardController 
			, MemberController memberController, MemberService memberService, HttpSession session) {
		this.masterService = masterService;
		this.commonService = commonService;
		this.commentController = commentController;
		this.boardController = boardController;
		this.memberController = memberController;
		this.memberService = memberService;
		this.session = session;
	}
	
	@ModelAttribute
	public void loginInfo(Model model, String login) {
		MemberDTO loginInfo = (MemberDTO)session.getAttribute("loginSession");
		if(loginInfo != null) {
			model.addAttribute("loginId", loginInfo.getId());
			model.addAttribute("masteryn", loginInfo.getMasteryn());			
		}
		model.addAttribute("login", login);
	}
	
	@GetMapping(value="/home")
	public String home(Model model) {
		return "/master/masterHome";
	}
	
	@GetMapping(value="/toMemberList")
	public String memberList(@RequestParam(defaultValue = "0") String searchType, @RequestParam(defaultValue = "")String searchKeyword,
			 @RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "10") int pageSize, 
			 @RequestParam(defaultValue = "1") String status, Model model) throws Exception {
		// 만약 세션에 저장되어 있는 로그인 정보가 관리자라면
		MemberDTO loginInfo = commonService.getLoginInfo();
		if(loginInfo.getMasteryn() == 'y') {
			return memberController.toMemberList(searchType, searchKeyword, curPage, pageSize, status, model);
		} else {
			return "redirect:/master/home";
		}
	}
	
	@GetMapping(value="/toBoardList")
	public String boardList(@RequestParam(defaultValue = "free") String category, @RequestParam(defaultValue="0")String searchType
			, @RequestParam(defaultValue = "")String searchKeyword, @RequestParam(defaultValue="10")int pageSize
			, @RequestParam(defaultValue = "1")int curPage, Model model) throws Exception {
		// 만약 세션에 저장되어 있는 로그인 정보가 관리자라면
		MemberDTO loginInfo = commonService.getLoginInfo();
		if(loginInfo.getMasteryn() == 'y') {
			return boardController.toListPage(category, searchType, searchKeyword, pageSize, curPage, model);
		} else {
			return "redirect:/master/home";
		}
	}
	
	@GetMapping(value="/toCommentList")
	public String commentList(@RequestParam(defaultValue="0")String searchType, @RequestParam(defaultValue = "")String searchKeyword
			, @RequestParam(defaultValue="10")int pageSize, @RequestParam(defaultValue = "1")int curPage, String id, Model model) {
		MemberDTO loginInfo = commonService.getLoginInfo();
		// 만약 세션에 저장되어 있는 로그인 정보가 관리자라면
		if(loginInfo.getMasteryn() == 'y') {
//			Map<String, Object> srchInfo = commentService.makeSrchInfo(loginInfo.getId(), "0", "");
//			List<CommentDTO> list = commentService.commentList(srchInfo);
//			model.addAttribute("list", list);
			return commentController.myList(searchType, searchKeyword, pageSize, curPage, id, model);
		} else {
			return "redirect:/master/home";
		}
	}
}
