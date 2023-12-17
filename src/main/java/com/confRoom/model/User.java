package com.confRoom.model;

import java.util.ArrayList;
import java.util.List;

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
	private String userName;
	private String password;
	private ArrayList<String> roles;
	
	

	
}
