package com.exception;

/**
 * seckillRecord表 新增数据异常
 * @author hanliang
 *
 */
public class SeckillRecordInsertException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1481473481118895191L;

	public SeckillRecordInsertException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SeckillRecordInsertException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
