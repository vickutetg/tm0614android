package com.gtotek.football.dao;

import java.util.List;

public interface QuestionDao {
	
	 
	
	public int getSize( );
	
	public QuestionEntity getQuestionByPosition(int index );
	
	public List<QuestionEntity> getAllQuestionByType( );

}
