package com.dto;

/**
 * controller层返回前台页面js的对象
 * @author hanliang
 *
 */
public class ResponseDto<T> {

	private boolean status;
	
	private T data;
	
	private long startTime;
	
	private long endTime;
	
	private String message;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponseDto [status=" + status + ", data=" + data
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

	public ResponseDto(boolean status, T data) {
		super();
		this.status = status;
		this.data = data;
	}

	public ResponseDto(boolean status, T data, long startTime, long endTime) {
		super();
		this.status = status;
		this.data = data;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public ResponseDto(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	
}
