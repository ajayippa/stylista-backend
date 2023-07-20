package com.stoned.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stories")
@Builder
public class Story {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/*@NotNull
    @Embedded
	@AttributeOverrides({
		@AttributeOverride(name="id",column = @Column(name = "user_id")),
		@AttributeOverride(name ="email",column = @Column(name="userEmail")),
	})
	private UserDto userDto;*/
	
	private String userName;
	private String name;
	@Column(columnDefinition = "longblob")
	private byte[] userImage;
	private String userEmail;
	private Integer userId;
	
	
	@Column(columnDefinition = "longblob")
	private byte[] image;
	private String type;
	private String captions;
	
	private String timestamp;
}
