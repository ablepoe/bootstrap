package com.service;

import java.util.Date;
import java.util.List;

import com.dto.ExposerUrl;
import com.entity.Seckill;
import com.exception.SeckillRecordInsertException;
import com.exception.SeckillUpdateException;

/**
 * service�ӿ�ģ��
 * @author hanliang
 *
 */
public interface SeckillService {

	//��ȡȫ����ɱ����
	public List<Seckill> getAllSeckill();
	
	//��ȡָ����ɱ����
	public Seckill getSeckill(long id);
	
	//ִ����ɱ����
	public int executeSeckill(long id, long userPhone) throws SeckillRecordInsertException,SeckillUpdateException;
	
	//��ȡ������ʱ��
	public Date getTime();
	
	//��¶��ɱ�ӿ�
	public ExposerUrl getUrl(long id, long time);
	
}
