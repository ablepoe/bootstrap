package com.dao;

import java.util.List;

import com.entity.CommandContent;

public interface ICommandContent {
	public List<CommandContent> getAllCommandContent();
	
	public void insertCommandContent(List<CommandContent> commandContetns);
}
