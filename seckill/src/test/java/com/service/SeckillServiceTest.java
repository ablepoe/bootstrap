package com.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dto.ExposerUrl;
import com.entity.Seckill;
import com.exception.SeckillRecordInsertException;
import com.exception.SeckillUpdateException;

/**
 * seckillService µƒjunit ≤‚ ‘¿‡
 * @author hanliang
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-service.xml","classpath:spring/spring-dao.xml"})
public class SeckillServiceTest {
	
	private Logger log = Logger.getLogger(SeckillServiceTest.class);

	@Resource(name = "SeckillService")
	private SeckillService seckillService;
	
	@Test
	@Ignore
	public void testGetAllSeckill() {
		List<Seckill> li = seckillService.getAllSeckill();
		for(Seckill seckill : li){
			System.out.println(seckill.toString());
		}
	}

	@Test
	@Ignore
	public void testGetSeckill() {
		long id = 1000l;
		Seckill seckill = seckillService.getSeckill(id);
		System.out.println(seckill.toString());
	}

	@Test
	@Ignore
	public void testExecuteSeckill() {
		long id = 1000l;
		long userPhone = 13916012313l;
		try{
			int result = seckillService.executeSeckill(id, userPhone);
			System.out.println(result);
		}catch(SeckillRecordInsertException e){
			e.printStackTrace();
		}catch(SeckillUpdateException e1){
			e1.printStackTrace();
		}catch(Exception e2){
			e2.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testGetTime() {
		Date date = seckillService.getTime();
		System.out.println(date.toString());
	}

	@Test
	@Ignore
	public void testGetUrl() {
		long id = 1001l;
		long time = new Date().getTime();
		ExposerUrl exposerUrl = seckillService.getUrl(id, time);
		System.out.println(exposerUrl.toString());
	}

}
