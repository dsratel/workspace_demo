package com.dialoguespace.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	// 댓글 작성
	@ResponseBody
	@PostMapping(value="/write.do")
	public String writeComment(CommentDTO commentDto, Model model) {
		System.out.println("===== CommentController - writeComment =====");
		System.out.println(commentDto);
		
		int rs = commentService.wrtieComment(commentDto);
		
		switch(rs) {
		case 1:
			return "성공";
		default :
			return "실패";
			
		}
	}
	
	// 댓글 목록
	@ResponseBody
	@PostMapping(value="/list")
	public List<CommentDTO> cmtListByBoardseq(int boardseq, Model model) {
		System.out.println("===== CommentController - cmtListByBoardseq =====");
		System.out.println("boardseq : " + boardseq);
		
		List<CommentDTO> list = commentService.cmtListByBoardseq(boardseq);
		return list;
	}

}