package com.entity;

import java.util.Date;

/**
 * 秒杀记录表对应实体类
 * @author hanliang
 *
 */
public class SeckillRecord {

	//秒杀商品id
	private long id;
	//用户手机
	private long userPhone;
	//秒杀状态
	private int state;
	//数据创建时间
	private Date createTime;
	//秒杀对象实体
	private Seckill seckill;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Seckill getSeckill() {
		return seckill;
	}
	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}
	@Override
	public String toString() {
		return "SeckillRecord [id=" + id + ", userPhone=" + userPhone
				+ ", state=" + state + ", createTime=" + createTime
				+ ", seckill=" + seckill + "]";
	}

	
	
}
