package com.dialoguespace.member;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dialoguespace.common.CommonService;
import com.dialoguespace.password.AuthService;
import com.dialoguespace.password.CustomMessageService;
import com.dialoguespace.utils.EncryptionUtils;
import com.dialoguespace.vo.FileVO;
import com.dialoguespace.vo.PaginationVO;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping(value="/member")
@Slf4j
public class MemberController {
	
	private final MemberService memberService;
	private final CommonService commonService;
	private final EncryptionUtils encryptionUtils;
	private final AuthService authService;
	private final CustomMessageService customMessageService;
	private final HttpSession session;
	
	@Value("${repo_dir}")
	private String repo_path;
	
	@Value("${member_dir}")
	private String member_path;
	
	@Autowired
	public MemberController(MemberService memberService, CommonService commonService, EncryptionUtils encryptionUtils, HttpSession session, AuthService authService
							, CustomMessageService customMessageService) {
		this.memberService = memberService;
		this.commonService = commonService;
		this.encryptionUtils = encryptionUtils;
		this.authService = authService;
		this.customMessageService = customMessageService;
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
	
	// 회원 등록
	@PostMapping(value="/insertMember.do")
	public String insertMember(MemberDTO memberDto, @RequestParam("upfile") MultipartFile[] files, HttpServletRequest request)
			throws NullPointerException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException
					, NoSuchPaddingException, NoSuchAlgorithmException, IOException {
		// 회원 등록
		log.info("MemberController memberDto : " + memberDto); 
		
		// RSA PrivateKey
		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		if(!memberDto.getPw().equals("")) {
			String strPw = encryptionUtils.decryptRsa(privateKey, memberDto.getPw());
			strPw = encryptionUtils.getSHA512(strPw);
			memberDto.setPw(strPw);
		}
		
		if(memberService.insertMember(memberDto) < 0) {
			return "errorPage";
		};
		log.info("회원정보 등록 완료");
		
		// 프로필 사진 등록
		if(files[0].getSize() > 0) {
		
		//// 파일VO에 리스트로 정리
		List<FileVO> fileList = commonService.setFileList(files, memberDto.getId(), "member", this.member_path);
		
		
		List<Integer> pk = commonService.saveNewFiles(files, fileList);			
			
		// 회원 프로필 사진 PK 등록
		memberService.addFileNo(memberDto.getId(), pk.get(0));
		}
		
		return "redirect:/member/list";
	}
	
	// 리스트 페이지 이동
	@GetMapping(value="/list")
	public String toMemberList(@RequestParam(defaultValue = "0") String searchType, @RequestParam(defaultValue = "")String searchKeyword,
			 @RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "10") int pageSize,
			 @RequestParam(defaultValue = "1") String status, Model model) throws Exception {
		// 검색 조건 - 조건으로 검색 - 전체 페이지 출력 - 조건 및 페이지에 맞는 데이터만 추출 - 화면에 출력
		// 검색 및 페이징 Map에 담기
		// 검색조건
		Map<String, Object> srchInfo = commonService.makeSrchInfo(searchType, searchKeyword, status);
		
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
		
		if(Integer.parseInt(status) == 2) model.addAttribute("delList", "y");
		
		return "/member/list";
	}
	
	// 회원 한명 삭제
	@ResponseBody
	@PostMapping(value="/delMember.do")
	public String delMember(@RequestParam String id, String self) throws Exception {
		//if(!id.equals("devvv")) memberService.delMember(id);
		log.info("id : " + id + " / self : " + self);
		
		return (self.equals("y") ?  "redirect:/" : "redirect:/member/list");
	}
	
	// 선택한 회원 삭제
	@ResponseBody
	@PostMapping(value="/selDelMember.do")
	public void selDelMember(@RequestBody String[] selectedMember) throws Exception {
		for(int i=0; i<selectedMember.length; i++) {
			log.info(selectedMember[i]);
		}
		log.info("삭제할 회원 목록 : " + selectedMember);
		memberService.selDelMember(selectedMember);
		log.info("선택한 회원 삭제 완료");
	}
	
	// 회원 상세보기 페이지로 이동
	@GetMapping(value="/toViewMember")
	public String toViewMember(@RequestParam(defaultValue = "")String id, Model model) throws Exception {
		log.info("상세 보기 회원 ID : " + id);
		
//		MemberDTO loginInfo = (MemberDTO)session.getAttribute("loginSession");
		
		if(id.equals("")) id = commonService.getLoginInfo().getId();
		
		// 멤버 정보 Model에 담기
		MemberDTO dto = memberService.toViewMember(id);
		model.addAttribute("dto", dto);
		
		String filePath = this.repo_path.replace("D:/demo_", "/") + "userIcon.png";
		
		if(dto.getFileno() > 0) {
			filePath = this.member_path.replace("D:/demo_", "/") + commonService.getSysNameBySeq(dto.getFileno(), "member"); 
//				filePath = filePath.length() > 19 ? filePath : (this.repo_path.replace("D:/demo_", "/") + "userIcon.png");  
			
		}
		// 사진 경로
//		model.addAttribute("masteryn", loginInfo.getMasteryn());
//		model.addAttribute("loginId", loginInfo.getId());
		model.addAttribute("filePath", filePath);
		return "/member/memberView";
		
	}
	
	// 회원 정보 수정
	@PostMapping(value="/editMember.do")
	public String editMember(MemberDTO memberDto, @RequestParam("profilePhoto") MultipartFile[] files, Model model) throws Exception {
		
		// 해당 회원의 프로필 사진 PK
		int seq = memberService.selFileNo(memberDto.getId());
		
		// 새로운 사진 유무
		if(files[0].getSize() > 0) {
			/* 파일 디렉토리 변수에 담기 */
			String sysPath			= this.member_path;
			String tempPath			= "D:\\demoTemp\\" + memberDto.getId();
			List<FileVO> fileList	= commonService.setFileList(files, memberDto.getId(), "member", sysPath);	// FileVO 정보 생성
			
			if(seq > 0) {
				// 새로운 사진 O & 기존에 프로필 사진 O
				//// 기존 프로필 사진 물리파일 삭제
				commonService.delFilePhs(seq);
				log.info("기존 프로필 사진 물리파일 삭제");
				
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
				memberDto.setFileno(commonService.selectFilePK("member", memberDto.getId(), fileList.get(0).getOrgName()));
			}
		} else {
			if(seq > 0) {
				// 새로운 사진 X & 기존 프로필 사진 O
				memberDto.setFileno(seq);
			}
			// 새로운 사진이 없고 기존 프로필도 없으면 회원 정보만 업데이트.
		}
		
		// 회원의 DB 정보 수정
		log.info("수정할 회원 정보 : " + memberDto.toString());
		if(memberService.editMember(memberDto) < 0) {
			return "errorPage";
		}
		log.info(memberDto.getId() + "님 회원정보 수정 완료");
		
		return toViewMember(memberDto.getId(), model);
	}
	
	// ID 중복체크
	@ResponseBody	// ajax를 쓰려면 @ResponseBody 어노테이션을 붙여야 한다.
	@GetMapping(value="/checkId")	// 브라우저에서 보낼 때 contentType: application/x-www-form-urlencoded; charset=utf-8; 을 설정해도 보낼 떄 text형 그리고 charset을 다시 지정해야 한다.
	public int checkId(String id) throws Exception {
		return memberService.checkId(id);
	}
	
	// EMAIL 중복체크
	@ResponseBody	// ajax를 쓰려면 @ResponseBody 어노테이션을 붙여야 한다.
	@GetMapping(value="/checkEmail")	// 브라우저에서 보낼 때 contentType: application/x-www-form-urlencoded; charset=utf-8; 을 설정해도 보낼 떄 text형 그리고 charset을 다시 지정해야 한다.
	public int checkEmail(String email) throws Exception {
		List<MemberDTO> list = memberService.checkEmail(email);
		return list.size();
	}
	
	// 로그인
	@PostMapping(value="/login.do")
	public String loginProcess(MemberDTO memberDto, String requestURI, String[] rememberId, HttpServletResponse response, Model model)
		throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException
				, UnsupportedEncodingException, InvalidKeyException {
		// 개인키 가져오기
		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		
		// 로그인 정보와 맞고 현재 활동 중인 회원이라면 session에 저장
		if(memberDto != null && memberDto.getPw() != null && !memberDto.getPw().equals("") && privateKey != null) {
			// RSA 복호화
			String strPw = encryptionUtils.decryptRsa(privateKey, memberDto.getPw());
			log.info("로그인 시 입력한 비밀번호 : " + strPw);
			
			if(!strPw.equals("aaa")) {
				// SHA-512 암호화
				strPw = encryptionUtils.getSHA512(strPw);
				log.info("로그인 시 입력한 암호화 SHA512 : " + strPw);				
			}
			memberDto.setPw(strPw);
			MemberDTO dto = memberService.selMemberByIdPw(memberDto);
			
			
			if(dto != null && dto.getStatus() == 1) {
				session.setAttribute("loginSession", dto);
				model.addAttribute("dto", dto);
				
				// redirect할 URI가 없다면 글목록을 요청
				requestURI = requestURI.equals("") ? "/board/toList" : requestURI;
				
				if(dto.getMasteryn() == 'y') {
					requestURI = "/master/home";
				}
				
				// ID 기억 요청한 경우
				if(rememberId != null) {
					Cookie cookie = new Cookie("rememberId", memberDto.getId());
					cookie.setDomain("demo.com");
					cookie.setPath("/");
					cookie.setMaxAge(24*60*60);
					cookie.setSecure(false);
					
					response.addCookie(cookie);
				}
				return "redirect:" + requestURI + "?login=y";
			} else {
				return "redirect:/";
			}
				
		} else {
			return "redirect:/"; 
		}
	}
	
	// 회원가입 페이지로 이동
	@GetMapping(value="/signUp")
	public String toSignUp(Model model)  throws NoSuchAlgorithmException, InvalidKeySpecException {
		// KeyPariGenerator 인스턴스 생성(RSA 알고리즘)
		encryptionUtils.genRsaInstance(model);
		
		return "/member/signUp";
	}
	
	// 프로필 사진 삭제
	@Transactional
	@GetMapping(value="/delPfPhoto")
	public int delPfPhoto(String id) {
		log.info("========== MemberController - delPfPhoto ==========");
		log.info("프로필 파일을 삭제할 유저 id : " + id);
		// t_filemeta DB, 물리파일 삭제
		commonService.delFileByIdCat(id, "member");
		
		// t_member DB에서 fileno 0으로 만들기
		return memberService.delPfPhoto(id);
	}	
	
	// 비밀번호 변경 팝업
	@GetMapping(value="/changePassword")
	public String toChangePasswordPop(String id, Model model) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// KeyPariGenerator 인스턴스 생성(RSA 알고리즘)
		encryptionUtils.genRsaInstance(model);
		model.addAttribute("id", id);
		
		return "/member/changePasswordPop";
	}
	
	// 비밀번호 변경
	@PostMapping(value="/changePassword.do")
	public String changePassword(String id, String pw, String curPw, Model model) throws NoSuchPaddingException, NoSuchAlgorithmException
				, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException{
		int rs = 0;
		log.info("현재 str : " + curPw);
		log.info("수정 str : " + pw);
		// RSA PrivateKey
		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		if(!pw.equals("")) {
			String strCurPw = encryptionUtils.decryptRsa(privateKey, curPw);
			String strPw = encryptionUtils.decryptRsa(privateKey, pw);
			log.info("현재 비밀번호 : " + strCurPw);
			log.info("수정 비밀번호 : " + strPw);
			strCurPw = encryptionUtils.getSHA512(strCurPw);
			strPw = encryptionUtils.getSHA512(strPw);
			log.info("현재 비밀번호 암호화 : " + strCurPw);
			log.info("수정 비밀번호 암호화 : " + strPw);
			rs = memberService.changePassword(id, strPw, strCurPw);
		}
	
		model.addAttribute("rs", ( rs > 0 ? "success" : "fail"));
		
		return "result";
	}
	
	// 비밀번호 변경 페이지 - 비밀번호 잊었을 경우
	@GetMapping(value="/forgotPassword")
	public String toPasswordEmail() {
		return "/member/passwordEmail";
	}
	
	// ID와 email로 회원정보 확인
	@ResponseBody
	@PostMapping(value="/userByIdEmail")
	public int userByIdEmail(MemberDTO dto) {
		if(dto.getId() != null && dto.getEmail() != null) {
			return memberService.userByIdemail(dto);
		} else {
			return -1;
		}
	}
	
}
