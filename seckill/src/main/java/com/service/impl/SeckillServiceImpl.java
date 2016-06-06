package com.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.common.Common;
import com.dao.SeckillDao;
import com.dto.ExposerUrl;
import com.entity.Seckill;
import com.exception.SeckillRecordInsertException;
import com.exception.SeckillUpdateException;
import com.service.SeckillService;

/**
 * seckillServiceģ��ʵ����
 * @author hanliang
 *
 */
@Service("SeckillService")
public class SeckillServiceImpl implements SeckillService{
	
	@Resource
	private SeckillDao seckillDao;

	@Override
	public List<Seckill> getAllSeckill() {
		return seckillDao.getAllSeckill();
	}

	@Override
	public Seckill getSeckill(long id) {
		return seckillDao.getSeckill(id);
	}

	@Override
	public int executeSeckill(long id, long userPhone) throws SeckillRecordInsertException,SeckillUpdateException{
		int updateResult = seckillDao.updateSeckill(id);
		int insertResult = 0;
		if(updateResult > 0){
			insertResult = seckillDao.insertSeckillRecord(id, userPhone, Common.STATE);
			if(insertResult <= 0){
				throw new SeckillRecordInsertException("insert error");
			}
		}else{
			throw new SeckillUpdateException("update error");
		}
		return insertResult;
	}

	@Override
	public Date getTime() {
		return new Date();
	}

	@Override
	public ExposerUrl getUrl(long id, long time) {
		ExposerUrl exposerUrl = null;
		boolean flag = false;
		//��ɱ����
		Seckill seckill = seckillDao.getSeckill(id);
		//time ����ʱ��
		if(time > seckill.getEndTime().getTime()){
			//��ɱ�ѽ���
			exposerUrl = new ExposerUrl(id,Common.TIMEOVER,Common.getInfo(Common.TIMEOVER));
			
		}else if(time < seckill.getStartTime().getTime()){
			//��ɱδ��ʼ
			exposerUrl = new ExposerUrl(id,Common.NOTSTARTED,Common.getInfo(Common.NOTSTARTED));
		}else{
			//��ɱ������
			String md5 = getMD5(id);
			exposerUrl = new ExposerUrl(id,Common.KILLING,md5,Common.getInfo(Common.KILLING));
		}
		return exposerUrl;
	}

	private String getMD5(long id){
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append("/");
		sb.append(Common.SALT);
		return DigestUtils.md5DigestAsHex(sb.toString().getBytes());
	}
	
}
