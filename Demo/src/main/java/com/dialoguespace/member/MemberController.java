package com.dialoguespace.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dialoguespace.common.CommonService;
import com.dialoguespace.vo.FileVO;
import com.dialoguespace.vo.PaginationVO;


@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private HttpSession session;
	
	// 회원 등록
	@PostMapping(value="/insertMember.do")
	public String insertMember(MemberDTO memberDto, @RequestParam("upfile") MultipartFile[] files, HttpServletRequest request) throws Exception {
		// 회원 등록
		System.out.println("MemberController memberDto : " + memberDto); 
		if(memberService.insertMember(memberDto) < 0) {
			return "errorPage";
		};
		System.out.println("회원정보 등록 완료");
		
		// 프로필 사진 등록
		if(files[0].getSize() > 0) {
		//// 파일 디렉토리 변수에 담기
		String contextRoot	= new HttpServletRequestWrapper(request).getRealPath("/");
		String path			= contextRoot + "resources/testFolder/";
		
		//// 파일VO에 리스트로 정리
		List<FileVO> fileList = commonService.setFileList(files, memberDto.getId(), "member", path);
		
		
		List<Integer> pk = commonService.saveNewFiles(files, fileList);			
			
		// 회원 프로필 사진 PK 등록
		memberService.addFileNo(memberDto.getId(), pk.get(0));
		}
		
		
		System.out.println("프로필 파일 저장 완료");

		return "redirect:/member/list";
	}
	
	// 리스트 페이지 이동
	@GetMapping(value="/list")
	public String toMemberList(@RequestParam(defaultValue = "0") String searchType, @RequestParam(defaultValue = "")String searchKeyword,
			 @RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "10") int pageSize, Model model) throws Exception {
		// 검색 조건 - 조건으로 검색 - 전체 페이지 출력 - 조건 및 페이지에 맞는 데이터만 추출 - 화면에 출력
		// 검색 및 페이징 Map에 담기
		// 검색조건
		Map srchInfo = commonService.makeSrchInfo(searchType, searchKeyword);
		
		// 조건에 맞는 데이터 개수
		int listCnt = memberService.countList(srchInfo);
		
		// 조건에 맞는 데이터 추출
		if(listCnt > 0) {	// 데이터가 1개 이상 있는 경우
			// paginationVO 생성
			PaginationVO paginationVO = new PaginationVO(listCnt, curPage, pageSize);
			
			// srchInfo map에 데이터 추가
			srchInfo.put("pagination", paginationVO);
			
			// 조건에 맞는 데이터 검색
			model.addAttribute("list", memberService.toMemberList(srchInfo));
		} else {
			model.addAttribute("list", null);
		}
		
		model.addAttribute("srchInfo", srchInfo);
		System.out.println("회원 목록 조회 완료");		
		return "list";
	}
	
	// 회원 한명 삭제
	@ResponseBody
	@PostMapping(value="/delMember.do")
	public void delMember(@RequestParam String id) throws Exception {
		System.out.println("삭제할 id 값 : " + id);
		memberService.delMember(id);
		System.out.println("회원 삭제 완료");
	}
	
	// 선택한 회원 삭제
	@ResponseBody
	@PostMapping(value="/selDelMember.do")
	public void selDelMember(@RequestBody String[] selectedMember) throws Exception {
		for(int i=0; i<selectedMember.length; i++) {
			System.out.println(selectedMember[i]);
		}
		System.out.println("삭제할 회원 목록 : " + selectedMember);
		memberService.selDelMember(selectedMember);
		System.out.println("선택한 회원 삭제 완료");
	}
	
	// 회원 수정 페이지로 이동
	@GetMapping(value="/editMember")
	public String toEditMember(String id, Model model) throws Exception {
		System.out.println("수정할 회원 ID : " + id);
		if(id != null) {
			// 멤버 정보 Model에 담기
			MemberDTO dto = memberService.toEditMember(id);
			model.addAttribute("dto", dto);
			
			if(dto.getFileno() > 0) {
				String path = commonService.getPath(dto.getFileno());
				String filePath = path.substring(path.lastIndexOf("resources")-1);
				model.addAttribute("filePath", filePath);				
			}
			// 사진 경로
			return "memberView";
		} else {
			return "errorPage";
		}
	}
	
	// 회원 정보 수정
	@PostMapping(value="/editMember.do")
	public String editMember(MemberDTO memberDto, @RequestParam("profilePhoto") MultipartFile[] files, Model model, HttpServletRequest request) throws Exception {
		
		// 해당 회원의 프로필 사진 PK
		int seq = memberService.selFileNo(memberDto.getId());			
		
		// 새로운 사진 유무
		if(files[0].getSize() > 0) {	
			/* 파일 디렉토리 변수에 담기 */
			String contextRoot		= new HttpServletRequestWrapper(request).getRealPath("/");
			String sysPath			= contextRoot + "resources/testFolder/";
			String tempPath			= "D:\\demoTemp\\";
			List<FileVO> fileList	= commonService.setFileList(files, memberDto.getId(), "member", sysPath);	// FileVO 정보 생성
			
			if(seq > 0) {
				// 새로운 사진 O & 기존에 프로필 사진 O
				//// 기존 프로필 사진 물리파일 삭제
				commonService.delFilePhs(seq);
				System.out.println("기존 프로필 사진 물리파일 삭제");
				
				//// 새로운 프로필 사진 저장				
				commonService.saveFileOnly(files[0], fileList.get(0).getSysName(), tempPath, sysPath);
				
				//// 새로운 프로필 사진 정보 DB에 업데이트
				fileList.get(0).setSeq(seq);
				commonService.updateFileDB(fileList.get(0));
				
				// 기존 사진 번호 dto에 담기
				memberDto.setFileno(seq);
			} else {
				// 새로운 사진 O & 기존에 프로필 사진 X
				// 새로운 프로필 사진 등록
				commonService.saveNewFiles(files, fileList);
				
				// 새로 등록한 fileNo MemberDTO에도 등록
				memberDto.setFileno(commonService.selectFilePK("member", memberDto.getId()));
			}
		} else {
			if(seq > 0) {
				// 새로운 사진 X & 기존 프로필 사진 O
				memberDto.setFileno(seq);
			}
			// 새로운 사진이 없고 기존 프로필도 없으면 회원 정보만 업데이트.
		}
		
		// 회원의 DB 정보 수정
		System.out.println("수정할 회원 정보 : " + memberDto.toString());
		if(memberService.editMember(memberDto) < 0) {
			return "errorPage";
		}
		System.out.println(memberDto.getId() + "님 회원정보 수정 완료");
		
		return toEditMember(memberDto.getId(), model);
	}
	
	// ID 중복체크
	@ResponseBody	// ajax를 쓰려면 @ResponseBody 어노테이션을 붙여야 한다.
	@GetMapping(value="/checkId")	// 브라우저에서 보낼 때 contentType: application/x-www-form-urlencoded; charset=utf-8; 을 설정해도 보낼 떄 text형 그리고 charset을 다시 지정해야 한다.
	public int checkId(String id) throws Exception {
		return memberService.checkId(id);
	}
	
	// 로그인
	@PostMapping(value="/login.do")
	public String loginProcess(MemberDTO memberDto, Model model) throws Exception {
		// 로그인 정보와 맞고 현재 활동 중인 회원이라면 session에 저장
		MemberDTO dto = memberService.selMemberByIdPw(memberDto);
		if(dto != null && dto.getStatus() == 1) {
			session.setAttribute("loginSession", dto);
			model.addAttribute("dto", dto);
			return "redirect:/board/toList";
		} else {
			return "redirect:/";
		}
	}
	
	// 회원가입 페이지로 이동
	@GetMapping(value="/signUp")
	public String toSignUp() {
		return "signUp";
	}
	
	
}