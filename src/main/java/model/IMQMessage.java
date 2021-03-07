package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IMQMessage {
	private int id;
	private String data;
	private String createdDate;
	private String expiredDate;
	private String topic;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTopic() {
		return topic;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setCreatedDate() {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		this.createdDate = LocalDateTime.now().format(myFormatObj);
	}

	public String getCreatedDate() {
		return createdDate;
	} 

	public void setExpiredDate() {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		this.expiredDate = LocalDateTime.now().plusDays(2).format(myFormatObj);
	}

	public String getExpiredDate() {
		return expiredDate;
	}

}
