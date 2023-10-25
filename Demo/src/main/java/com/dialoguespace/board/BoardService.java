package com.dialoguespace.board;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dialoguespace.common.CommonService;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO boardDAO;
	
	@Autowired
	CommonService commonService;
	
	// 글 작성 프로세스
	public int writeArticle(BoardDTO dto) throws IOException {
		System.out.println("========== BoardService 진입 ==========");
		
		// 첨부파일이 있다면 attathyn = 'y'
		if(dto.getContent().contains("<img")) {
			dto.setAttachfile("y");
			
			// img가 몇 개인지 파악
		} else {
			dto.setAttachfile("n");
		};

		System.out.println("BoardService - writeArticle() - BoardDTO" + dto.toString());
		
		return boardDAO.writeArticle(dto);
	}
	
	// 글 전체 불러오기
	public List<BoardDTO> selectAll() {
		System.out.println("========== BoardService 진입 ==========");
		return boardDAO.selectAll();
	}
	
	// seq로 게시글 정보 불러오기
	public BoardDTO selArticleBySeq(int seq) {
		System.out.println("========== BoardService 진입 ==========");
		return boardDAO.selArticleBySeq(seq);
	}
	
	// 가장 최신 글 seq 불러오기
	public int getLatestSeq(String author) {
		System.out.println("========== BoardService 진입 ==========");
		return boardDAO.getLatestSeq(author);
	}
	
	// 게시글 삭제
	public int delArticle(int seq, char attachfile) {
		System.out.println("========== BoardService - delArticle 진입 ==========");
		// 첨부파일이 있는 경우 filemeta의 db 삭제
		if(attachfile == 'y') {
			String id = "" + seq;
			commonService.delFileByIdCat(id, "board");
		}
		
		// 게시글 삭제
		return boardDAO.delArticle(seq); 
	}

}
