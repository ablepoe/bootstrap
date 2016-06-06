package com.entity;

import java.util.Date;

/**
 * 数据库seckill实体类
 * @author hanliang
 *
 */
public class Seckill {

	//秒杀商品id
	private long id;
	//秒杀商品name
	private String name;
	//秒杀商品数量
	private long num;
	//秒杀起始时间
	private Date startTime;
	//秒杀结束时间
	private Date endTime;
	//数据创建时间
	private Date createTime;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Seckill [id=" + id + ", name=" + name + ", startTime="
				+ startTime + ", endTime=" + endTime + ", createTime="
				+ createTime + "]";
	}
	
	
}
