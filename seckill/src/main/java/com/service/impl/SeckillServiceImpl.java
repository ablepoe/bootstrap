package com.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.common.Common;
import com.dao.SeckillDao;
import com.dto.ExposerUrl;
import com.entity.Seckill;
import com.exception.SeckillMD5UnmatchException;
import com.exception.SeckillRecordInsertException;
import com.exception.SeckillUpdateException;
import com.service.SeckillService;

/**
 * seckillService模块实现类
 * 
 * @author hanliang
 *
 */
@Service("SeckillService")
public class SeckillServiceImpl implements SeckillService {

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
	@Transactional(propagation = Propagation.REQUIRED)
	public int executeSeckill(long id, long userPhone, String md5)
			throws SeckillRecordInsertException, SeckillUpdateException, SeckillMD5UnmatchException {
		if(!md5.equals(getMD5(id))){
			throw new SeckillMD5UnmatchException("md5 unmatch");
		}
		int updateResult = seckillDao.updateSeckill(id);
		int insertResult = 0;
		if (updateResult > 0) {
			insertResult = seckillDao.insertSeckillRecord(id, userPhone,
					Common.STATE);
			if (insertResult <= 0) {
				throw new SeckillRecordInsertException("insert error");
			}
		} else {
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
		// 秒杀对象
		Seckill seckill = seckillDao.getSeckill(id);
		if(seckill == null){
			exposerUrl = new ExposerUrl(id, Common.KILLOBJNOTEXIST, Common.getInfo(Common.KILLOBJNOTEXIST));
		}
		long endTime = seckill.getEndTime().getTime();
		long startTime = seckill.getStartTime().getTime();
		// time 传入时间
		if (time > endTime) {
			// 秒杀已结束
			exposerUrl = new ExposerUrl(id, Common.TIMEOVER,
					Common.getInfo(Common.TIMEOVER), startTime, endTime, time);

		} else if (time < startTime) {
			// 秒杀未开始
			exposerUrl = new ExposerUrl(id, Common.NOTSTARTED,
					Common.getInfo(Common.NOTSTARTED), startTime, endTime, time);
		} else {
			// 秒杀进行中
			String md5 = getMD5(id);
			exposerUrl = new ExposerUrl(id, Common.KILLING, md5,
					Common.getInfo(Common.KILLING), startTime, endTime, time);
		}
		return exposerUrl;
	}

	private String getMD5(long id) {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append("/");
		sb.append(Common.SALT);
		return DigestUtils.md5DigestAsHex(sb.toString().getBytes());
	}

}
