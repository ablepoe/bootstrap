package com.dao;


import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.entity.Seckill;

/**
 * daoÄ£¿é µ¥Ôª²âÊÔ
 * @author hanliang
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring-dao.xml")
public class SeckillDaoTest {
	
	@Autowired                          
	private SeckillDao seckill;
	

	@Test
	@Ignore
	public void testGetAllSeckill() {
		List<Seckill> li = seckill.getAllSeckill();
		for (Seckill seckill : li) {
			System.out.println(seckill.toString());
		}
	}

	@Test
	@Ignore
	public void testGetSeckill() {
		long id = 1000l;
		Seckill sk = seckill.getSeckill(id);
		System.out.println(sk.toString());
	}
	
	@Test
	@Ignore
	public void testUpdateSeckill() {
		long id = 1000l;
		int result = seckill.updateSeckill(id);
		System.out.println(result);
	}

	@Test
//	@Ignore
	public void testInsertSeckillRecord() {
		long id = 1000l;
		long userPhone = 13916012312l;
		int state = 1;
		int result = seckill.insertSeckillRecord(id, userPhone, state);
		System.out.println(result);
	}

}
