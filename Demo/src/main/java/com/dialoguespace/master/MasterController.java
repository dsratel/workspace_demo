package com.dialoguespace.master;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dialoguespace.board.BoardController;
import com.dialoguespace.comment.CommentDTO;
import com.dialoguespace.comment.CommentService;
import com.dialoguespace.common.CommonService;
import com.dialoguespace.member.MemberController;
import com.dialoguespace.member.MemberDTO;
import com.dialoguespace.member.MemberService;

@Controller
@RequestMapping(value="/master")
public class MasterController {
	
	private final MasterService masterService;
	private final CommonService commonService;
	private final CommentService commentService;
	private final BoardController boardController;
	private final MemberController memberController;
	private final MemberService memberService;
	private final HttpSession session;
	
	@Autowired
	public MasterController(MasterService masterService, CommonService commonService, CommentService commentService, BoardController boardController 
			, MemberController memberController, MemberService memberService, HttpSession session) {
		this.masterService = masterService;
		this.commonService = commonService;
		this.commentService = commentService;
		this.boardController = boardController;
		this.memberController = memberController;
		this.memberService = memberService;
		this.session = session;
	}
	
	@GetMapping(value="/home")
	public String home(Model model) {
		return "/master/masterHome";
	}
	
	@GetMapping(value="/toMemberList")
	public String memberList(@RequestParam(defaultValue = "0") String searchType, @RequestParam(defaultValue = "")String searchKeyword,
			 @RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "10") int pageSize, Model model) throws Exception {
		// 만약 세션에 저장되어 있는 로그인 정보가 관리자라면
		MemberDTO loginInfo = (MemberDTO)session.getAttribute("loginSession");
		if(loginInfo.getMasteryn() == 'y') {
			return memberController.toMemberList(searchType, searchKeyword, curPage, pageSize, model);
		} else {
			return "redirect:/master/home";
		}
	}
	
	@GetMapping(value="/toBoardList")
	public String boardList(@RequestParam(defaultValue = "free") String category, @RequestParam(defaultValue="0")String searchType
			, @RequestParam(defaultValue = "")String searchKeyword, @RequestParam(defaultValue="10")int pageSize
			, @RequestParam(defaultValue = "1")int curPage, Model model) throws Exception {
		// 만약 세션에 저장되어 있는 로그인 정보가 관리자라면
		MemberDTO loginInfo = (MemberDTO)session.getAttribute("loginSession");
		if(loginInfo.getMasteryn() == 'y') {
			return boardController.toListPage(category, searchType, searchKeyword, pageSize, curPage, model);
		} else {
			return "redirect:/master/home";
		}
	}
	
	@GetMapping(value="/toCommentList")
	public String commentList(Model model) {
		// 만약 세션에 저장되어 있는 로그인 정보가 관리자라면
		MemberDTO loginInfo = (MemberDTO)session.getAttribute("loginSession");
		if(loginInfo.getMasteryn() == 'y') {
			List<CommentDTO> list = commentService.cmtList(loginInfo.getId());
			model.addAttribute("list", list);
			return "/comment/commentList";
		} else {
			return "redirect:/master/home";
		}
	}
}
