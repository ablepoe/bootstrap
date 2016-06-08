package com.service;

import java.util.Date;
import java.util.List;

import com.dto.ExposerUrl;
import com.entity.Seckill;
import com.exception.SeckillMD5UnmatchException;
import com.exception.SeckillRecordInsertException;
import com.exception.SeckillUpdateException;

/**
 * service接口模块
 * @author hanliang
 *
 */
public interface SeckillService {

	//获取全部秒杀对象
	public List<Seckill> getAllSeckill();
	
	//获取指定秒杀对象
	public Seckill getSeckill(long id);
	
	//执行秒杀数量
	public int executeSeckill(long id, long userPhone, String md5) throws SeckillRecordInsertException, SeckillUpdateException, SeckillMD5UnmatchException;
	
	//获取服务器时间
	public Date getTime();
	
	//暴露秒杀接口
	public ExposerUrl getUrl(long id, long time);
	
}
