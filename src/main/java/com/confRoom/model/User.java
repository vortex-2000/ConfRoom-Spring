package com.confRoom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User {
	
	@Id
	@Column(name = "userId")
	@GeneratedValue
	private int userId;
	private String name;
	
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public int getUserId() {
		return this.userId;
	}
	
}
