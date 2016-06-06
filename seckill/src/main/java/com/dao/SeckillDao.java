package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Seckill;

/**
 * dao模块接口
 * @author hanliang
 *
 */
public interface SeckillDao {

	//获取全部秒杀对象
	public List<Seckill> getAllSeckill();
	
	//获取指定秒杀对象
	public Seckill getSeckill(long id);
	
	//更新秒杀数量
	public int updateSeckill(long id);
	
	//记录秒杀结果
	public int insertSeckillRecord(@Param("id") long id, @Param("userPhone") long userPhone, @Param("state") int state);

}
