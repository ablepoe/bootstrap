package com.exception;

/**
 * ����seckill��ʧ���쳣
 * @author hanliang
 *
 */
public class SeckillUpdateException extends SeckillException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1294907578215554800L;

	public SeckillUpdateException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeckillUpdateException(String message) {
		super(message);
	}
	
}
