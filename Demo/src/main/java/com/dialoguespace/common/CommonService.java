package com.dialoguespace.common;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dialoguespace.member.MemberDTO;
import com.dialoguespace.vo.FileVO;

@Service
public class CommonService {
	
	private final CommonDAO commonDAO;
	private final HttpSession session;
	
	@Autowired
	public CommonService(CommonDAO commonDAO, HttpSession session) {
		this.commonDAO = commonDAO;
		this.session = session;
	}

	// 검색 조건 Map에 담기
	public Map<String, Object> makeSrchInfo(String searchType, String searchKeyword, String status) throws Exception {
		Map<String, Object> srchInfo = new HashMap<>();
		srchInfo.put("searchType", searchType);
		srchInfo.put("searchKeyword", searchKeyword);
		srchInfo.put("status", status);
		
		return srchInfo;
	}
	
	// 다중 MultipartFile에서 VO 설정 및 업로드 파일 처리 후 List 리턴
	public List<FileVO> setFileList(MultipartFile[] files, String fileParent, String category, String path) throws IOException {
		List<FileVO> fileList = new ArrayList<>();
		for(int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			FileVO fVO = this.setFileVOByMultipart(file, fileParent, category, path);
			if (fVO != null) {
				fileList.add(fVO);
			}
		}
		
		return fileList;
	}
	
	// 개별 fileVO 설정
	public FileVO setFileVOByMultipart(MultipartFile file, String fileParent, String category, String path) throws IOException {
		if(!file.isEmpty()) {
			String orgName = file.getOriginalFilename();
			String extension = orgName.substring(orgName.lastIndexOf("."));
			String sysName = UUID.randomUUID().toString() + extension;
			FileVO vo = new FileVO();
			
			vo.setFileParent(fileParent);
			vo.setCategory(category);
			vo.setOrgName(orgName);
			vo.setSysName(sysName);
			vo.setFileSize(file.getSize());
			vo.setFancySize(fansySize(file.getSize()));
			vo.setContentType(file.getContentType());
			vo.setFilePath(path);
			
			return vo;
		} else {
			return null;
		}
	}
	
	private DecimalFormat df = new DecimalFormat("#,###.0");
	
	private String fansySize(long size) {
		if(size < 1024) {	// 1K 미만
			return size + " Bytes";
		} else if(size < (1024 * 1024)) {	// 1M 미만
			return df.format(size / 1024.0) + " KB";
		} else if(size < (1024 * 1024 * 1024)) {	// 1G 미만
			return df.format(size / (1024.0 * 1024.0)) + " MB";
		} else {
			return df.format(size / (1024.0 * 1024.0 * 1024.0)) + " GB";
		}
	}

	// 다수 파일 저장
	public List<Integer> saveNewFiles(MultipartFile[] multipartFiles, List<FileVO> fileList) {
		// 임시 폴더 경로
		String tempPath = "D:\\demoTemp\\";
		createTempFolder(tempPath);
		List<Integer> filesPK = new ArrayList<>();
		
			try {
				// 파일 하나씩 저장		// for문 하나에서 인덱스 지정하여 물리파일 저장-DB저장-resource로 파일 이동에서 for문 3개로 나눔
				for(int i = 0; i < multipartFiles.length; i++) {
					// 물리 파일 저장
					FileUtils.copyInputStreamToFile(multipartFiles[i].getInputStream(), new File(tempPath, fileList.get(i).getSysName()));
					System.out.println(i + "번 째 물리 파일 저장"); 
				}
				
				// 파일 내용 DB에 저장
				for(FileVO vo : fileList) {
					commonDAO.saveNewFile(vo);
					filesPK.add(commonDAO.selectFilePK(vo));
				}
				
				// 물리파일, DB 저장 끝난 후 템프 폴더에 있는 파일을 resource로 옮기기
				for(int i = 0; i < fileList.size(); i++) {
					copyFile(multipartFiles, fileList);
				}
				
			} catch(Exception e) {
				System.out.println("파일 저장 실패");
				e.printStackTrace();
				// DB 파일 삭제
				for(FileVO vo : fileList) {
					try {
						commonDAO.delFile(vo);					
					} catch(Exception ex) {
						ex.printStackTrace();
					}
				}
				
				
			} finally {
				// 템프 폴더 지우기
				File folder = new File(tempPath);
				delFilesUnderFolder(folder);	// 폴더 하위에 파일이 있으면 폴더가 삭제되지 않으므로 파일들을 우선 삭제한다.
				folder.delete();			
			}
			
			return filesPK;
			
		
		
	}
	
	// 임시 폴더 생성
	public void createTempFolder(String tempPath) {
		
		File folder = new File(tempPath);
		try {
			if(folder.exists()) {
				System.out.println("demo 임시 폴더가 존재합니다. 하위 파일을 삭제합니다.");
				delFilesUnderFolder(folder);
			} else {
				folder.mkdir();
				System.out.println("demo 임시 폴더 생성");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 파일 옮기기 (임시 폴더 -> resource 폴더)
	public void copyFile(MultipartFile[] multipartFiles, List<FileVO> fileList) throws Exception {
		// 파일 하나씩 저장
		for(int i = 0; i < multipartFiles.length; i++) {
			// 물리 파일 저장
			FileUtils.copyInputStreamToFile(multipartFiles[i].getInputStream(), new File(fileList.get(i).getFilePath(), fileList.get(i).getSysName()));
			System.out.println(i + "번 째 물리 파일 resource하위 폴더에 저장"); 
		}
	}
	
	// 폴더 하위 파일 삭제
	public void delFilesUnderFolder(File folder) {
		File[] folder_list = folder.listFiles();
		for(File file : folder_list) {
			file.delete();
			System.out.println(file + " 삭제 완료");
		}
	}
	
	// 파일 경로 검색
	public String getPath(int seq) {
		return commonDAO.getPath(seq);
	}
	
	// seq로 특정 파일 삭제(물리적)
	public void delFilePhs(int seq) throws Exception {
		String filePath = commonDAO.getPath(seq);
		File f = new File(filePath);
		f.delete();
		System.out.println(filePath + " 삭제 완료");
	}
	
	// seq로 특정 파일 DB정보 삭제
	public int delFileDbBySeq(int seq) throws Exception {
		return commonDAO.delFileDbBySeq(seq);
	}
	
	// seq로 특정 파일 DB정보 업데이트
	public int updateFileDB(FileVO file) throws Exception {
		return commonDAO.updateFileDB(file);
	}
	
	// 특정 파일 물리적으로 저장
	public void saveFileOnly(MultipartFile multipartFile, String sysname, String tempPath, String sysPath) {
		// 물리 파일 저장
		try {
			FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(tempPath, sysname));			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			File folder = new File(tempPath);
			delFilesUnderFolder(folder);
			folder.delete();
			try {
				FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(sysPath, sysname));				
			} catch(IOException e) {
				e.printStackTrace();
				}
		}
		
		System.out.println(sysPath + sysname + "물리 파일 저장 완료"); 
	}
	
	// 파일 PK 찾기
	public int selectFilePK(String category, String fileParent, String orgname) throws Exception {
		FileVO vo = new FileVO();
		vo.setCategory(category);
		vo.setFileParent(fileParent);
		vo.setOrgName(orgname);
		return commonDAO.selectFilePK(vo); 
	}
	
	// id와 category로 파일 삭제
	public int delFileByIdCat(String id, String category) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("category", category);
		
		int delFile = 0;
		
		// 물리파일 삭제
		//// 경로 가져오기
		List<String> paths = commonDAO.selFilePathByIdCat(map);
		//// 경로에 해당하는 파일 삭제
		for(String filePath : paths) {
			File file = new File(filePath);
			file.delete();
			delFile++;
		}
		
		// filemeta DB 삭제
		int delFileDb = commonDAO.delFileDbByIdCat(map);
		
		System.out.println("물리파일 삭제 개수 : " + delFile + " / 파일 DB 삭제 개수 : " + delFileDb);
		return delFileDb;
	}
	
	// id와 category로 fileparent update
	public int modifyFileparent(String id, String category, int seq) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("category", category);
		map.put("seq", seq);
		
		return commonDAO.modifyFileparent(map);
	}
	
	// seq로 File db 찾기
	public List<FileVO> SelFileById(String seq, String category) {
		Map<String, String> map = new HashMap<>();
		map.put("category", category);
		map.put("seq", seq);
		
		return commonDAO.SelFileById(map);
	}	
	
	// fileparent로 file path 찾기
	public List<String> SelFilePathById(String id, int pid) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("pid", pid);
		return commonDAO.SelFilePathById(map);
	}
		
	// loginInfo 불러오기
	public MemberDTO getLoginInfo() {
		return (MemberDTO)session.getAttribute("loginSession");
	}
	

	
	// 삭제할 seq 찾기
	public List<Integer> getDelSeq(String seq, List<String> arr) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", seq);
		map.put("arr", arr);
		return commonDAO.getDelSeq(map);
	}
	
	// seq로 sysname 찾기
	public String getSysNameBySeq(int seq, String category) {
		Map<String, Object> map = new HashMap<>();
		map.put("seq", seq);
		map.put("category", category);
		return commonDAO.getSysNameBySeq(map);
	}
}
