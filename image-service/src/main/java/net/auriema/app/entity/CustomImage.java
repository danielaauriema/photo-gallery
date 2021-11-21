package net.auriema.app.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.Data; 

@Data
public class CustomImage {

	  @Id
	  public UUID id;

	  public String type;
	  public String data;

}
