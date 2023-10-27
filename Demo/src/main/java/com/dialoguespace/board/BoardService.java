package com.dialoguespace.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialoguespace.common.CommonService;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO boardDAO;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	HttpSession session;
	
	// 글 작성 프로세스
	public int writeArticle(BoardDTO dto) throws IOException {
		System.out.println("========== BoardService - writeArticle 진입 ==========");

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
		
		return boardDAO.delArticle(seq); 
	}
	
	// 게시글 수정
	public int editArticle(BoardDTO boardDto) {
		return boardDAO.editArticle(boardDto);
	}

}
