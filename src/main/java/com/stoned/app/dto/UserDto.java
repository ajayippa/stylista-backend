package com.stoned.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private String username;
	private String name;
	//private String userImage;
	private String email;
	private Integer id;
}
