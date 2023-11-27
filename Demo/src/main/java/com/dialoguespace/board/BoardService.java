package com.dialoguespace.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialoguespace.comment.CommentService;
import com.dialoguespace.common.CommonService;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO boardDAO;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	HttpSession session;
	
	// 글 작성 프로세스
	public int writeArticle(BoardDTO dto) throws IOException {
		System.out.println("========== BoardService - writeArticle 진입 ==========");

		System.out.println("BoardService - writeArticle() - BoardDTO" + dto.toString());
		
		return boardDAO.writeArticle(dto);
	}
	
	// 글 전체 불러오기
	public List<BoardDTO> selectArticle(Map srchInfo) {
		System.out.println("========== BoardService 진입 ==========");
		return boardDAO.selectArticle(srchInfo);
	}
	
	// seq로 게시글 정보 불러오기
	public BoardDTO selArticleBySeq(int seq, int pid) {
		System.out.println("========== BoardService 진입 ==========");
		Map map = new HashMap();
		map.put("seq", seq);
		map.put("pid", pid);
		return boardDAO.selArticleBySeq(map);
	}
	
	// 가장 최신 글 seq 불러오기
	public int getLatestSeq(String author, int pid) {
		System.out.println("========== BoardService 진입 ==========");
		// 답글 구분을 위한 pid 추가
		Map map = new HashMap();
		map.put("author", author);
		map.put("pid", pid);
		return boardDAO.getLatestSeq(map);
	}
	
	// 게시글 삭제
	public int delArticle(int seq, int pid, char attachfile, char cmtYn) {
		System.out.println("========== BoardService - delArticle 진입 ==========");
		// 답글일 경우 category를 board_reply로 변경
		String category = "board";
		if(pid > 0) category = "board_reply";
		
		// 첨부파일이 있는 경우 filemeta의 db 삭제
		if(attachfile == 'y') {
			String id = "" + seq;			
			commonService.delFileByIdCat(id, category);
		}
		
		// 답글 구분을 위하여 pid 담기
		Map map = new HashMap();
		map.put("seq", seq);
		map.put("pid", pid);
		
		int rs = boardDAO.delArticle(map);
		
		// 댓글 삭제
		if(cmtYn == 'Y') {
			commentService.deleteCmtByBoardseq(map);
		}
				
		return  rs;
	}
	
	// 게시글 수정
	public int editArticle(BoardDTO boardDto) {
		return boardDAO.editArticle(boardDto);
	}
	
	// 조건에 맞는 게시글 개수
	public int countList(Map srchInfo) {
		return boardDAO.countList(srchInfo);
	}
	
	public Map makeSrchInfo(String category,String searchType,String searchKeyword) {
		Map map = new HashMap();
		map.put("category", category);
		map.put("searchType", searchType);
		if(searchKeyword == null) {
			map.put("searchKeyword", "");			
		} else {
			map.put("searchKeyword", searchKeyword);
		}
		
		return map;
	}
	
	// 게시글 댓글 개수 증가
	public int addCommentCnt(int boardseq, char replyYn) {
		// 답글 여부
		Map map = new HashMap();
		map.put("boardseq", boardseq);
		map.put("replyYn", replyYn);
				
		return boardDAO.addCommentCnt(map);
	}
	
	// 게시글 댓글 개수 감소
	public int reduceCommentCnt(int seq, int pid, int boardseq, char replyyn) {
		Map map = new HashMap();
		map.put("seq", seq);
		map.put("pid", pid);
		map.put("boardseq", boardseq);
		map.put("replyyn", replyyn);
		
		return boardDAO.reduceCommentCnt(map);
	}
	
	// 게시글 조회 수 증가
	public int addViewCnt(int seq) {
		return boardDAO.addViewCnt(seq);
	}
	
	// 게시글 시퀀스 리스트
	public List<Integer> getSeqList(Map srchInfo) {
		return boardDAO.getSeqList(srchInfo);
	}

}
