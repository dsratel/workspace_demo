package com.dialoguespace.service;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dialoguespace.dao.CommonDAO;
import com.dialoguespace.vo.FileVO;

@Service
public class CommonService {
	
	@Autowired
	CommonDAO commonDAO;

	// 검색 조건 Map에 담기
	public Map makeSrchInfo(String searchType, String searchKeyword) throws Exception {
		Map srchInfo = new HashMap();
		srchInfo.put("searchType", searchType);
		srchInfo.put("searchKeyword", searchKeyword);
		
		return srchInfo;
	}
	
	// 다중 MultipartFile에서 VO 설정 및 업로드 파일 처리 후 List 리턴
	public List<FileVO> getFileList(MultipartFile[] files, String fileParent, String category, String path) throws IOException {
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
			String sysName = UUID.randomUUID().toString();
			FileVO vo = new FileVO();
			
			vo.setFileParent(fileParent);
			vo.setCategory(category);
			vo.setOrgName(file.getOriginalFilename());
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

	// 파일 저장
	public int saveFiles(MultipartFile[] multipartFiles, List<FileVO> fileList) {
		// 임시 폴더 경로
		String tempPath = "D:\\demoTemp\\";
		createTempFolder(tempPath);
		
		try {
			
			// 파일 하나씩 저장		// for문 하나에서 인덱스 지정하여 물리파일 저장-DB저장-resource로 파일 이동에서 for문 3개로 나눔
			for(int i = 0; i < multipartFiles.length; i++) {
				// 물리 파일 저장
				FileUtils.copyInputStreamToFile(multipartFiles[i].getInputStream(), new File(tempPath, fileList.get(i).getSysName()));
				System.out.println(i + "번 째 물리 파일 저장"); 
			}
			
			// 파일 내용 DB에 저장
			for(FileVO vo : fileList) {
				commonDAO.saveFile(vo);
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
			delFiles(folder);
			folder.delete();			
		}
			
		return 0;
		
	}
	
	// 임시 폴더 생성
	public void createTempFolder(String tempPath) {
		
		File folder = new File(tempPath);
		try {
			if(folder.exists()) {
				System.out.println("demo 임시 폴더가 존재합니다. 하위 파일을 삭제합니다.");
				delFiles(folder);
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
//		File orgFile = new File(inFilePath);
//		System.out.println(orgFile);
//		File outFile = new File(outFilePath);
//		System.out.println(outFile);
//		
//		try {
//			FileUtils.copyFile(orgFile, outFile);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// 파일 하나씩 저장
		for(int i = 0; i < multipartFiles.length; i++) {
			// 물리 파일 저장
			FileUtils.copyInputStreamToFile(multipartFiles[i].getInputStream(), new File(fileList.get(i).getFilePath(), fileList.get(i).getSysName()));
			System.out.println(i + "번 째 물리 파일 resource하위 폴더에 저장"); 
		}
	}
	
	// 하위 파일 삭제
	public void delFiles(File folder) {
		File[] folder_list = folder.listFiles();
		for(File file : folder_list) {
			file.delete();
			System.out.println(file + " 삭제 완료");
		}
	}
	
}