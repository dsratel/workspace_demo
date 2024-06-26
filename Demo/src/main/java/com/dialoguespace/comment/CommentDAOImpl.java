package com.dialoguespace.comment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAOImpl implements CommentDAO {
	
	private SqlSession sqlsession;
	
	@Autowired
	public CommentDAOImpl(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	// 댓글 작성
	@Override
	public int writeComment(CommentDTO commentDto) {
		return sqlsession.insert("commentMapper.writeComment", commentDto);
	}
	
	// 댓글 목록 - boardseq 별
	@Override
	public List<CommentDTO> cmtListByBoardseq(Map<String, Object> map) {
		List<CommentDTO> list = sqlsession.selectList("commentMapper.cmtListByBoardseq", map);
		return list;
	}
	
	// 댓글 목록 - search condition 별
	@Override
	public List<CommentDTO> cmtListBySearchcondition() {
		List<CommentDTO> list = sqlsession.selectList("commentMapper.cmtListBySearchcondition");
		return list;
	}
	
	// 댓글 삭제
	@Override
	public int deleteCmt(Map<String, Integer> map) {
		return sqlsession.delete("commentMapper.deleteCmt", map);
	}
	
	// 댓글 비밀번호 확인
	@Override
	public int passwordCheck(Map<String, Object> map) {
		return sqlsession.selectOne("commentMapper.passwordCheck", map);
	}
	
	// 댓글 수정
	@Override
	public int editCmt(Map<String, Object> map) {
		return sqlsession.update("commentMapper.editCmt", map);
	}
	
	// depth 0 댓글 rootseq 수정
	@Override
	public int editRootSeq() {
		return sqlsession.update("commentMapper.editRootSeq");
	}
	
	// 동일 rootseq, depth의 마지막 re_order 가져오기
	@Override
	public int getNextReorder(Map map) {
		return sqlsession.selectOne("commentMapper.getNextReorder", map);
	}
	
	// 댓글 정렬
	@Override
	public int sortComment(Map map) {
		return sqlsession.update("commentMapper.sortComment", map);
	}
	
	// 게시글 시퀀스로 댓글 삭제
	@Override
	public int deleteCmtByBoardseq(Map map) {
		return sqlsession.delete("commentMapper.deleteCmtByBoardseq", map);
	}
	
	// 대댓글 작성
	@Override
	public int writeReplyComment(CommentDTO commentDto) {
		return sqlsession.insert("commentMapper.writeReplyComment", commentDto);
	}
	
	// 대댓글 목록
	@Override
	public List<CommentDTO> cmtListByPid(int pid) {
		return sqlsession.selectList("commentMapper.cmtListByPid", pid);
	}
	
	// 대댓글 삭제
	@Override
	public int deleteReplyCmt(int seq) {
		return sqlsession.delete("commentMapper.deleteReplyCmt", seq);
	}

	// 댓글 목록(댓글+대댓글)
	@Override
	public List<CommentDTO> cmtList(String id) {
		return sqlsession.selectList("commentMapper.cmtList", id);
	};
	
	// 조건에 맞는 댓글 개수
	@Override
	public int countList(Map<String, Object> srchInfo) {
		return sqlsession.selectOne("commentMapper.countList", srchInfo);
	}
	
	// 조건에 맞는 댓글 목록
	@Override
	public List<CommentDTO> commentList(Map<String, Object> srchInfo) {
		return sqlsession.selectList("commentMapper.commentList", srchInfo);
	};
}
