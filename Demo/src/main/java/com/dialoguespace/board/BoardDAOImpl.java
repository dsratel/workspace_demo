package com.dialoguespace.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	SqlSession sqlsession;
	
	// 글 작성 프로세스
	@Override
	public int writeArticle(BoardDTO boardDto) {
		System.out.println("========== BoardDAOImpl 진입 ==========");
		return sqlsession.insert("boardMapper.writeArticle", boardDto);
	}
	
	// 글 전체 불러오기
	@Override
	public List<BoardDTO> selectArticle(Map srchInfo) {
		System.out.println("========== BoardDAOImpl 진입 ==========");
		List<BoardDTO> list = sqlsession.selectList("boardMapper.selectArticle", srchInfo); 
		return list;
	}
	
	// seq로 게시글 정보 불러오기
	@Override
	public BoardDTO selArticleBySeq(int seq) {
		System.out.println("========== BoardDAOImpl 진입 ==========");
		return sqlsession.selectOne("boardMapper.selArticleBySeq", seq);
	}
	
	// 가장 최신 글 seq 불러오기
	@Override
	public int getLatestSeq(String author) {
		System.out.println("========== BoardDAOImpl 진입 ==========");
		return sqlsession.selectOne("boardMapper.getLatestSeq", author);
	}
	
	// 게시글 삭제
	@Override
	public int delArticle(int seq) {
		System.out.println("========== BoardDAOImpl 진입 ==========");
		return sqlsession.delete("boardMapper.delArticle", seq);
	}
	
	// 게시글 수정
	public int editArticle(BoardDTO boardDto) {
		return sqlsession.update("boardMapper.editArticle", boardDto);
	}
	
	// 조건에 맞는 게시글 개수
	public int countList(Map map) {
		return sqlsession.selectOne("boardMapper.countList", map);
	}
}
