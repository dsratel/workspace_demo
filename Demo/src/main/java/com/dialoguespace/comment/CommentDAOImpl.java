package com.dialoguespace.comment;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAOImpl implements CommentDAO {
	
	@Autowired
	SqlSession sqlsession;
	
	// 댓글 작성
	@Override
	public int writeComment(CommentDTO commentDto) {
		return sqlsession.insert("commentMapper.writeComment", commentDto);
	}
	
	// 댓글 목록
	@Override
	public List<CommentDTO> cmtListByBoardseq(int boardseq) {
		List<CommentDTO> list = sqlsession.selectList("commentMapper.cmtListByBoardseq", boardseq);
		return list;
	}

}