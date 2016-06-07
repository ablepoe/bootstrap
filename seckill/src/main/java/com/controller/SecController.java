package com.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.Common;
import com.dto.ExposerUrl;
import com.dto.ResponseDto;
import com.entity.Seckill;
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
	
	@RequestMapping(value = "/executeSeckill")
	public void executeSeckill(){
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/getTime", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public ResponseDto<Long> getTime(){
		Date date = new Date();
		ResponseDto<Long> resp = new ResponseDto<Long>(Common.SUCCESS,date.getTime());
		return resp;
	}
	
	@ResponseBody
	@RequestMapping(value = "{id}/getUrl" , method = RequestMethod.POST)
	public ResponseDto<ExposerUrl> getUrl(@PathVariable("id") long id){
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
