package com.dialoguespace.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private SqlSession sqlsession;
	
	@Autowired
	public BoardDAOImpl(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	// 글 작성 프로세스
	@Override
	public int writeArticle(BoardDTO boardDto) {
		return sqlsession.insert("boardMapper.writeArticle", boardDto);
	}
	
	// 글 전체 불러오기
	@Override
	public List<BoardDTO> selectArticle(Map<String, Object> srchInfo) {
		List<BoardDTO> list = sqlsession.selectList("boardMapper.selectArticle", srchInfo); 
		return list;
	}
	
	// seq로 게시글 정보 불러오기
	@Override
	public BoardDTO selArticleBySeq(Map<String, Integer> map) {
		return sqlsession.selectOne("boardMapper.selArticleBySeq", map);
	}
	
	// 가장 최신 글 seq 불러오기
	@Override
	public int getLatestSeq(Map<String, Object> map) {
		return sqlsession.selectOne("boardMapper.getLatestSeq", map);
	}
	
	// 게시글 삭제
	@Override
	public int delArticle(Map<String, Integer> map) {
		return sqlsession.delete("boardMapper.delArticle", map);
	}
	
	// 게시글 수정
	@Override
	public int editArticle(BoardDTO boardDto) {
		return sqlsession.update("boardMapper.editArticle", boardDto);
	}
	
	// 조건에 맞는 게시글 개수
	@Override
	public int countList(Map<String, Object>  map) {
		return sqlsession.selectOne("boardMapper.countList", map);
	}
	
	// 게시글 댓글 개수 증가
	@Override
	public int addCommentCnt(Map<String, Object> map) {
		return sqlsession.update("boardMapper.addCommentCnt", map);
	}
	
	// 게시글 댓글 개수 감소
	@Override
	public int reduceCommentCnt(Map<String, Object> map) {
		return sqlsession.update("boardMapper.reduceCommentCnt", map);
	}
	
	// 게시글 조회 수 증가
	@Override
	public int addViewCnt(int seq) {
		return sqlsession.update("boardMapper.addViewCnt", seq);
	}
	
	// 게시글 시퀀스 리스트
	@Override
	public List<Integer> getSeqList(Map<String, Object> srchInfo) {
		return sqlsession.selectList("boardMapper.getSeqList", srchInfo);
	}
}
