package com.dialoguespace.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

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

import com.dialoguespace.dto.MemberDTO;
import com.dialoguespace.service.CommonService;
import com.dialoguespace.service.MemberService;
import com.dialoguespace.vo.FileVO;
import com.dialoguespace.vo.PaginationVO;


@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CommonService commonService;
	
	// 회원 등록
	@PostMapping(value="/insertMember.do")
	public String insertMember(MemberDTO memberDto, @RequestParam("upfile") MultipartFile[] files, HttpServletRequest request) throws Exception {
		// 회원 등록
		System.out.println("MemberController memberDto : " + memberDto);
		memberService.insertMember(memberDto);
		System.out.println("insert success");
		
		// 프로필 사진 등록
		//// 파일 디렉토리 변수에 담기
		String contextRoot	= new HttpServletRequestWrapper(request).getRealPath("/");
		String path			= contextRoot + "resources/testFoler/";
		
		//// 파일VO에 리스트로 정리
		List<FileVO> fileList = commonService.getFileList(files, memberDto.getM_id(), "member", path);
		
		//// 파일 저장
		commonService.saveFiles(files, fileList);
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
	public void delMember(@RequestParam String m_id) throws Exception {
		System.out.println("삭제할 id 값 : " + m_id);
		memberService.delMember(m_id);
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
	public String toEditMember(String m_id, Model model) throws Exception {
		System.out.println("수정할 회원 ID : " + m_id);
		if(m_id != null) {
			model.addAttribute("map", memberService.toEditMember(m_id));			
			return "memberView";
		} else {
			return "errorPage";
		}
	}
	
	// 회원 정보 수정
	@PostMapping(value="/editMember.do")
	public String editMember(MemberDTO memberDto, Model model) throws Exception {
		System.out.println("수정할 회원 정보 : " + memberDto.toString());
		memberService.editMember(memberDto);
		System.out.println(memberDto.getM_id() + "님 회원정보 수정 완료");
		return toEditMember(memberDto.getM_id(), model);
	}
	
}
