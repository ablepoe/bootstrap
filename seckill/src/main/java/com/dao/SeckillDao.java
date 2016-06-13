package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.entity.Seckill;

/**
 * daoģ��ӿ�
 * @author hanliang
 *
 */
public interface SeckillDao {

	//��ȡȫ����ɱ����
	public List<Seckill> getAllSeckill();
	
	//��ȡָ����ɱ����
	public Seckill getSeckill(long id);
	
	//������ɱ����
	public int updateSeckill(long id);
	
	//��¼��ɱ���
	public int insertSeckillRecord(@Param("id") long id, @Param("userPhone") long userPhone, @Param("state") int state);

	//����mysql�洢����ִ����ɱ
	public void executeSeckillByProcedure(Map<String,Object> map);
}
