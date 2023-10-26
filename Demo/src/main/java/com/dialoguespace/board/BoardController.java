package com.dialoguespace.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dialoguespace.common.CommonService;
import com.dialoguespace.member.MemberDTO;
import com.dialoguespace.vo.FileVO;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	HttpSession session;
	
	// 글 쓰기 페이지로 이동
	@GetMapping(value="/toWrite")
	public String toWritePage(Model model) {
		MemberDTO dto = (MemberDTO) session.getAttribute("loginSession");
		String loginId = dto.getId();
		System.out.println("로그인한 ID는 " + loginId + " 입니다.");
		
		System.out.println("t_filemeta에 있는 저장하지 않은 이미지 파일 DB 삭제");
		commonService.delFileByIdCat(loginId, "board");
		
		model.addAttribute("loginId", loginId);
		 
		return "/board/writeBoard";
	}
	
	@PostMapping(value="/write.do")
	public String writeArticle(BoardDTO boardDto, @RequestParam("upfile") MultipartFile[] multipartFiles, Model model, HttpServletRequest request) throws IOException {
		System.out.println("========== BoardController - writeBoard ==========");
		System.out.println("BoardDTO : " + boardDto.toString());
		// board DB 저장
		boardService.writeArticle(boardDto);
		String seq = "" + boardService.getLatestSeq(boardDto.getAuthor());
		
		// 첨부 파일이 있는 경우
		if(boardDto.getAttachfile().contentEquals("y")) {
		//// 파일 디렉토리 변수에 담기
		String contextRoot	= new HttpServletRequestWrapper(request).getRealPath("/");
		String path			= contextRoot + "resources/board/";
		
		List<FileVO> fileList = commonService.setFileList(multipartFiles, seq, "board", path);
		commonService.saveNewFiles(multipartFiles, fileList);
		}
		
		// 글 목록 불러오기
		return toListPage(model);
	}

	
	// 글 목록 페이지로 이동
	@GetMapping(value="/toList")
	public String toListPage(Model model) {
		model.addAttribute("list", boardService.selectAll());
		return "listArticle";
	}
	
	// 글 상세보기
	@GetMapping(value="/viewArticle")
	public String toViewPage(int seq, Model model) {
		System.out.println("========== Board Controller 진입 ==========");
		
		MemberDTO loginDto = (MemberDTO) session.getAttribute("loginSession");
		String loginId = "";
		if(loginDto != null) {
			loginId = loginDto.getId(); System.out.println("글 번호 : " + seq);			  
		} else {
			loginId = "";
		}
		System.out.println("로그인 아이디 : " + loginId);
		 
		
		// model에 dto 객체 담기
		//// 글 정보  
		BoardDTO dto = boardService.selArticleBySeq(seq);
		model.addAttribute("dto", dto);
		model.addAttribute("loginId", loginId);
		
		//// 파일 정보
		if(dto.getAttachfile().contentEquals("y")) {
			// "board"와 seq에 해당하는 파일들 가져오기
			model.addAttribute("files", commonService.SelFilePathById(""+seq));
		}
		
		return "/board/viewBoard";
	}
	

	
	// 게시글 삭제
	@ResponseBody
	@GetMapping(value="/delArticle")
	public String delArticle(int seq, char attachfile) {
		System.out.println("게시글 삭제 : seq - " + seq + " / attachfile - " + attachfile);
		boardService.delArticle(seq, attachfile);
		
		return "toList";
	}
	
	// 게시글 수정 페이지로 이동
	@GetMapping(value="/editArticle")
	public String toEditPage(int seq, Model model) {
		System.out.println("게시글 수정 페이지 이동 : seq - " + seq);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("loginSession");
		String loginId = dto.getId();
		
		model.addAttribute("dto", boardService.selArticleBySeq(seq));
		model.addAttribute("loginId", loginId);
		
		return "editArticle";
	}
	
	// 게시글 수정
//	@PostMapping(value="/edit.do")
//	public String editArticle(BoardDTO dto, Model model) {
//		System.out.println("========== BoardController - editArticle ==========");
//		System.out.println("BoardDTO : " + dto.toString());
//		// 게시글 db 수정
//		
//		// 이미지를 수정 했다면
//		// filemeta에 fileparent가 사용자 id인 걸 가져와서 content에 있는 걸 찾는다.		=> byId
//		
//		
//		// filemeta의 값과 content의 값이 같은 데이터는 바꿔주기
//		// 아닌 데이터는 물리파일 삭제, db삭제
//		
//	}
	
	/* 
	 * 
	 *썸머노트 글 작성
	@PostMapping(value="/writeSummernote.do")
	public String writeAtricle(BoardDTO dto, Model model) throws IOException {
		System.out.println("========== Board Controller 진입 ==========");
		System.out.println("BoardDTO : " + dto.toString());
		boardService.writeArticle(dto);
		System.out.println("글 저장 완료");
		
		// 파일 DB의 fileparent를 게시글 seq로 변경
		int seq = boardService.getLatestSeq(dto.getAuthor());
		commonService.modifyFileparent(dto.getAuthor(), "board", seq);
		
		 return toListPage(model);
	}
	
		// 썸머노트 이미지 저장
	@ResponseBody
	@PostMapping(value="/summernoteImgSave")
	public String summerNoteTest(@RequestParam("file") MultipartFile[] multipartFiles, String author, HttpServletRequest request) throws IOException{
		// 내부 경로로 저장
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		String filePath = contextRoot + "resources/summerNoteImg/";

		List<FileVO> fileList = commonService.setFileList(multipartFiles, author, "board", filePath);
		commonService.saveNewFiles(multipartFiles, fileList);
		

		System.out.println("썸머노트 이미지 저장");
		

		String path = "/" + filePath.substring(filePath.lastIndexOf("resources")) + fileList.get(0).getSysName();
		System.out.println("ajax로 넘겨줄 url path : " + path);
		
		return path;
	}
	*/
	
	
	
	
}