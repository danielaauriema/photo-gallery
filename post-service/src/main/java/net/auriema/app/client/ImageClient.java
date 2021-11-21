package net.auriema.app.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.auriema.app.client.request.AddImageRequest;
import net.auriema.app.client.response.AddImageResponse;

@Service
public class ImageClient {

	RestTemplate restTemplate = new RestTemplate();
	
	@Value("${image-service.url}")
	private String imageServiceUrl;

	public AddImageResponse AddImage (AddImageRequest request) throws Exception {
		return  restTemplate.postForObject(imageServiceUrl + "data/", request, AddImageResponse.class);
	}	

}
