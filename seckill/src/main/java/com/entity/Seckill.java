package com.entity;

import java.util.Date;

/**
 * ���ݿ�seckillʵ����
 * @author hanliang
 *
 */
public class Seckill {

	//��ɱ��Ʒid
	private long id;
	//��ɱ��Ʒname
	private String name;
	//��ɱ��Ʒ����
	private long num;
	//��ɱ��ʼʱ��
	private Date startTime;
	//��ɱ����ʱ��
	private Date endTime;
	//���ݴ���ʱ��
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
