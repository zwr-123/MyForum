package com.example.demo.bean;

import java.util.Arrays;
import java.util.List;


/**
 * 测试 用枚举类标识问题的标签
 */
public enum tag {
	编程语言("编程语言",Arrays.asList("JAVA","RUST","C++")),
	数据库("数据库",Arrays.asList("mysql","mongdb"));
	
	private final String kind;
	private final List<String> signList;
	
	private tag(String kind,List<String> signList){
		this.kind=kind;
		this.signList=signList;
	}

	public String getKind() {
		return kind;
	}

	public List<String> getSignList() {
		return signList;
	}
}
