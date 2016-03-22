package com.entity;

import java.util.List;

public class Command {
	
	private int id;
	private String command;
	private String description;
	private List<CommandContent> contents;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<CommandContent> getContents() {
		return contents;
	}
	public void setContent(List<CommandContent> contents) {
		this.contents = contents;
	}
}
