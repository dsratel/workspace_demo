package com.dialoguespace.board;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dialoguespace.comment.CommentDTO;
import com.dialoguespace.comment.CommentService;
import com.dialoguespace.common.CommonService;
import com.dialoguespace.member.MemberDTO;
import com.dialoguespace.vo.FileVO;
import com.dialoguespace.vo.PaginationVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/board")
@Slf4j
public class BoardController {
	
	private final BoardService boardService;
	private final CommonService commonService;
	private final CommentService commentService;
	private final HttpSession session;
	
	@Value("${board_dir}")
	private String path;
	
	@Autowired
	public BoardController(BoardService boardService, CommonService commonService, CommentService commentService, HttpSession session) {
		this.boardService = boardService;
		this.commonService = commonService;
		this.commentService = commentService;
		this.session = session;
	}
	
	@ModelAttribute
	public void loginInfo(Model model, String login) {
		MemberDTO loginInfo = (MemberDTO)session.getAttribute("loginSession");
		if(loginInfo != null) {
			model.addAttribute("loginId", loginInfo.getId());
			model.addAttribute("masteryn", loginInfo.getMasteryn());
			if(loginInfo.getFileno() > 0) {
				String pfPath = "/repository/member/" + commonService.getSysNameBySeq(loginInfo.getFileno(), "member");
				model.addAttribute("pfPath", pfPath);
			}
		}
		model.addAttribute("login", login);
	}
	
	// 글 쓰기 페이지로 이동
	@GetMapping(value="/toWrite")
	public String toWritePage(@RequestParam(defaultValue = "0")int pid, @RequestParam(defaultValue = "")String title
			, @RequestParam(defaultValue = "")String category, Model model) {
		log.info("pid : " + pid + " / title : " + title + " / category : " + category);
		
//		log.info("t_filemeta에 있는 저장하지 않은 이미지 파일 DB 삭제");
//		commonService.delFileByIdCat(loginId, "board");
		if(pid > 0) {
			model.addAttribute("pid", pid);
			model.addAttribute("orgTitle", title);
			model.addAttribute("category", category);
		}
		 
		return "/board/writeBoard";
	}
	
	// 게시글 작성
	@Transactional
	@PostMapping(value="/write.do")
	public String writeArticle(BoardDTO boardDto, @RequestParam("upfile") MultipartFile[] multipartFiles, Model model, HttpServletRequest request) throws IOException {
		log.info("========== BoardController - writeBoard ==========");
		log.info("BoardDTO : " + boardDto.toString());
		// board DB 저장
		boardService.writeArticle(boardDto);
		String seq = "" + boardService.getLatestSeq(boardDto.getAuthor(), boardDto.getPid());
		
		// 첨부 파일이 있는 경우
		if(boardDto.getAttachfile().contentEquals("y")) {
			
			log.info("게시글 첨부파일 저장경로 : " + this.path);
		
			String category = (boardDto.getPid() > 0) ? "board_reply" : "board";
			List<FileVO> fileList = commonService.setFileList(multipartFiles, seq, category, this.path);
			commonService.saveNewFiles(multipartFiles, fileList);
		}
		
		return "redirect:/board/toList";
	}

	
	// 글 목록 페이지로 이동
	@GetMapping(value="/toList")
	public String toListPage(@RequestParam(defaultValue = "free") String category, @RequestParam(defaultValue="0")String searchType
			, @RequestParam(defaultValue = "")String searchKeyword, @RequestParam(defaultValue="10")int pageSize
			, @RequestParam(defaultValue = "1")int curPage, Model model) throws Exception {
		
		// 검색요건 추가하여 리스트 출력
		Map<String, Object> srchInfo = boardService.makeSrchInfo(category, searchType, searchKeyword);
		int listCnt = boardService.countList(srchInfo);
		if(listCnt > 0) {
			srchInfo.put("pagination", new PaginationVO(listCnt,curPage,pageSize));
			// 검색할 글 시퀀스
			List<Integer> seqList = boardService.getSeqList(srchInfo);
			srchInfo.put("seqList", seqList);
			// 페이징 내용 추가
			model.addAttribute("list", boardService.selectArticle(srchInfo));
			model.addAttribute("srchInfo", srchInfo);
		}
		
		return "/board/listArticle";
	}
	
	// 글 상세보기
	@GetMapping(value="/viewArticle")
	public String toViewPage(int seq, int pid, Model model) {
		char replyYn = pid > 0 ? 'y' : 'n';		 
		
		// model에 dto 객체 담기
		//// 글 정보  
		BoardDTO dto = boardService.selArticleBySeq(seq, pid);
		model.addAttribute("dto", dto);
		model.addAttribute("replyBoard", replyYn);
		
		//// 파일 정보
		if(dto.getAttachfile().contentEquals("y")) {
			// "board"와 seq에 해당하는 파일들 가져오기
			List<String> files = commonService.SelFilePathById(""+seq, pid);
			for(int i = 0; i < files.size(); i++) {
				files.set(i, this.path.replace("D:/demo_", "/") + files.get(i));
			}
			
			model.addAttribute("files", files);
		}
		
		// 댓글 정보
		List<CommentDTO> cmtList = commentService.cmtListByBoardseq(dto.getSeq(), replyYn);
		if(cmtList != null) {
			model.addAttribute("cmtList", cmtList);
		} else {
			model.addAttribute("cmtList", "noComment");
		}
		
		// 게시글 조회수 증가
		boardService.addViewCnt(seq);
		
		return "/board/viewBoard";
	}
	

	// 게시글 선택 삭제
	@PostMapping(value="selDelArticle")
	public String selDelArticle(String[] delList) {
		log.info("========== BoardController - selDelArticle ==========");
		if(delList != null) {	// null check
			for(String str : delList) {
				String delInfo[] = str.split("#");
				log.info(Arrays.toString(delInfo));
				delArticle(Integer.parseInt(delInfo[0]), Integer.parseInt(delInfo[1]), delInfo[2].charAt(0), delInfo[3].charAt(0));
			}			
		}
		
		return "redirect:/board/toList";
	}
	
	// 게시글 삭제
	@GetMapping(value="/delArticle")
	public String delArticle(int seq, int pid, char attachfile, char cmtYn) {
		log.info("게시글 삭제 : seq - " + seq + " / pid : " + pid + " / attachfile - " + attachfile + " / cmtYn : " + cmtYn);
		
		// 로그인한 ID가 수정 권한이 있는지 확인 - 권한이 없으면 홈으로 이동
		String loginId = commonService.getLoginInfo().getId();
		BoardDTO dto = boardService.selArticleBySeq(seq, pid);
		if(!loginId.equals(dto.getAuthor()) && !loginId.equals("devvv")) {
			return "redirect:/";
		}
		
		if(seq > 0 && pid > -1 &&  attachfile != 0 && cmtYn != 0) {
			boardService.delArticle(seq, pid, attachfile, cmtYn);
		}
		
		return "redirect:/board/toList";
	}
	
	// 게시글 수정 페이지로 이동
	@GetMapping(value="/editArticle")
	public String toEditPage(int seq, int pid, Model model) {
		String loginId = commonService.getLoginInfo().getId();
		BoardDTO dto = boardService.selArticleBySeq(seq, pid);
		// 로그인한 ID가 수정 권한이 있는지 확인 - 권한이 없으면 홈으로 이동
		if(!loginId.equals(dto.getAuthor())) return "redirect:/";
		
		model.addAttribute("dto", dto);
		
		// 파일 있는 경우
		if(dto.getAttachfile().contentEquals("y")) {
			// "board"와 seq에 해당하는 파일들 가져오기
			String category = (pid > 0) ? "board_reply" : "board";
			List<FileVO> files = commonService.SelFileById(""+seq, category);
			int fileCnt = files.size();
			model.addAttribute("files", files);
			model.addAttribute("fileCnt", fileCnt);
		}
		
		return "/board/editBoard";
	}
	
	// 게시글 수정
	@PostMapping(value="/edit.do")
	public String editArticle(BoardDTO dto, int fileCnt, @RequestParam("upfile") MultipartFile[] multipartFiles
			, @RequestParam(value="prevImg", required=false) String[] prevImgs, Model model, HttpServletRequest request) throws Exception {
		// 로그인한 ID가 수정 권한이 있는지 확인 - 권한이 없으면 홈으로 이동
		String loginId = commonService.getLoginInfo().getId();
		BoardDTO dtoCheck = boardService.selArticleBySeq(dto.getSeq(), dto.getPid());
		if(!loginId.equals(dtoCheck.getAuthor())) {
			return "redirect:/";
		}
		
		log.info("BoardDTO : " + dto.toString());
		log.info("*** 기존 이미지 목록 ***");
		if(prevImgs != null) {
			for(String p : prevImgs) {
				log.info("기존 이미지 seq : " + p);
			}			
		}
		
		log.info("*** 첨부파일 목록 ***");
		if(multipartFiles != null) {
			for(MultipartFile f : multipartFiles) {
				log.info("첨부파일 이름 " + f.getOriginalFilename());
			}
		}
		
		// 게시글 db 수정
		boardService.editArticle(dto);
		
		// 기존 이미지 삭제
		String category = (dto.getPid() > 0) ? "board_reply" : "board";
		if(prevImgs != null) {
			// 기존 이미지에서 삭제할 seq 찾기
			List<String> arr = Arrays.asList(prevImgs);
			List<Integer> delSeq = commonService.getDelSeq(""+dto.getSeq(), arr);
			
			log.info("삭제할 seq는 " + delSeq.toString());
			
			// 만약 삭제할 이미지가 있다면, 기존 이미지 삭제
			for(int seq : delSeq) {
				commonService.delFilePhs(seq);
				commonService.delFileDbBySeq(seq);
			}
		} else {
			// 기존 이미지 전부 삭제
			commonService.delFileByIdCat(""+dto.getSeq(), category);
		}
		
		// 이미지를 수정 했다면
		if(dto.getAttachfile().contains("y")) {
			// 새로운 이미지 업로드
			commonService.saveNewFiles(multipartFiles, commonService.setFileList(multipartFiles, ""+dto.getSeq(), category, this.path));
		}
		
		return "redirect:/board/toList";
	}
	
	
	// 엑셀 다운로드 테스트
	@GetMapping(value="/excel")
	public String excelDownload(HttpServletResponse response) throws UnsupportedEncodingException {
//		String EncodedName = new String("다이얼엑셀".getBytes("UTF-8"), "8859_1");
//		response.setHeader("Content-Disposision", "attachment; filename=" + EncodedName);
		response.setHeader("Content-Disposition", "attachment; filename=dialogue.xls");
		return "/excelDownload";
	}
	
	/* 
	 * 
	 *썸머노트 글 작성
	@PostMapping(value="/writeSummernote.do")
	public String writeAtricle(BoardDTO dto, Model model) throws IOException {
		log.info("========== Board Controller 진입 ==========");
		log.info("BoardDTO : " + dto.toString());
		boardService.writeArticle(dto);
		log.info("글 저장 완료");
		
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
		

		log.info("썸머노트 이미지 저장");
		

		String path = "/" + filePath.substring(filePath.lastIndexOf("resources")) + fileList.get(0).getSysName();
		log.info("ajax로 넘겨줄 url path : " + path);
		
		return path;
	}
	*/
	
	
	
	
}
