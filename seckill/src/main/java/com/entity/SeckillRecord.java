package com.entity;

import java.util.Date;

/**
 * ��ɱ��¼���Ӧʵ����
 * @author hanliang
 *
 */
public class SeckillRecord {

	//��ɱ��Ʒid
	private long id;
	//�û��ֻ�
	private long userPhone;
	//��ɱ״̬
	private int state;
	//���ݴ���ʱ��
	private Date createTime;
	//��ɱ����ʵ��
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
