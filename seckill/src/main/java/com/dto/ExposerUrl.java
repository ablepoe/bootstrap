package com.dto;
/**
 * controler 与 service 的交互对象
 * @author hanliang
 *
 */
public class ExposerUrl {

	private long id;
	
	private int status;
	
	private String md5;
	
	private String stateInfo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public ExposerUrl(long id, int status, String stateInfo) {
		super();
		this.id = id;
		this.status = status;
		this.stateInfo = stateInfo;
	}

	public ExposerUrl(long id, int status, String md5, String stateInfo) {
		super();
		this.id = id;
		this.status = status;
		this.md5 = md5;
		this.stateInfo = stateInfo;
	}

	@Override
	public String toString() {
		return "ExposerUrl [id=" + id + ", status=" + status + ", md5=" + md5
				+ ", stateInfo=" + stateInfo + "]";
	}
	
	
	
}
