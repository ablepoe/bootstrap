package com.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Common;
import com.dto.ExposerUrl;
import com.dto.ResponseDto;
import com.entity.Seckill;
import com.exception.SeckillMD5UnmatchException;
import com.exception.SeckillRecordInsertException;
import com.exception.SeckillUpdateException;
import com.service.SeckillService;

/**
 * 控制层，与页面交互
 * @author hanliang
 *
 */
@Controller
public class SecController {
	
	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "SeckillService")
	private SeckillService seckillService;
	
	@RequestMapping(value = "/getAllList", method = RequestMethod.GET)
	public String getAllList(Model model){
		List<Seckill> li = seckillService.getAllSeckill();
		model.addAttribute("list", li);
		return "list";
	}
	
	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String getSeckill(@PathVariable("id") long id, Model model){
		Seckill seckill = seckillService.getSeckill(id);
		model.addAttribute("data", seckill);
		return "detail";
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}/{md5}/executeSeckill", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseDto<Object> executeSeckill(@PathVariable("id") Long id, @PathVariable("md5") String md5,@CookieValue("phone") Long userPhone){
		ResponseDto<Object> responseDto;
		try{
			seckillService.executeSeckill(id, userPhone, md5);
			responseDto = new ResponseDto<Object>(true, Common.KILLSUCCESS);
		}catch(SeckillRecordInsertException e){
			responseDto = new ResponseDto<Object>(false, e.getMessage());
		}catch(SeckillUpdateException e1){
			responseDto = new ResponseDto<Object>(false, e1.getMessage());
		}catch(SeckillMD5UnmatchException e2){
			responseDto = new ResponseDto<Object>(false, e2.getMessage());
		}catch(Exception e3){
			responseDto = new ResponseDto<Object>(false, e3.getMessage());
		}
		return responseDto;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getTime", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public ResponseDto<Long> getTime(){
		Date date = new Date();
		ResponseDto<Long> resp = new ResponseDto<Long>(Common.SUCCESS,date.getTime());
		return resp;
	}
	
	@ResponseBody
	@RequestMapping(value = "{id}/getUrl" , method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public ResponseDto<ExposerUrl> getUrl(@PathVariable("id") Long id){
		try{
			Date date = new Date();
			ExposerUrl exposerurl =  seckillService.getUrl(id, date.getTime());
			return new ResponseDto<ExposerUrl>(true, exposerurl, exposerurl.getStartTime(), exposerurl.getEndTime());	
		}catch(Exception e){
			log.error(e.getMessage(), e);
			return new ResponseDto<ExposerUrl>(false, e.getMessage());
		}
	}
	
}
