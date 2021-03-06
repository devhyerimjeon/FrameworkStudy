package com.test.mybatis;

import java.util.ArrayList;

public interface IGradeDAO
{
	// 성적 데이터가 입력된 인원 수 확인
	public int count();
	
	// 성적 데이터 리스트 확인
	public ArrayList<GradeDTO> list();
	
	// 성적 데이터 입력(추가)
	public int add(GradeDTO g);
}
