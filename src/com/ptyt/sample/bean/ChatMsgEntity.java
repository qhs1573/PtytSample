package com.ptyt.sample.bean;

public class ChatMsgEntity extends BaseBean {
	private static final String TAG = ChatMsgEntity.class.getSimpleName();
    //����
    private String name;
    //����
    private String date;
    //��������
    private String text;
    //�Ƿ�Ϊ�Է���������Ϣ
    private boolean isComMeg = true;
    
	public ChatMsgEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public ChatMsgEntity(String name, String date, String text, boolean isComMeg) {
		this.name = name;
		this.date = date;
		this.text = text;
		this.isComMeg = isComMeg;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isComMeg() {
		return isComMeg;
	}
	public void setComMeg(boolean isComMeg) {
		this.isComMeg = isComMeg;
	}
}
