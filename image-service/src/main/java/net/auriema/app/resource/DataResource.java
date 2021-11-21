package net.auriema.app.resource;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.auriema.app.entity.Image;
import net.auriema.app.enums.ImageType;
import net.auriema.app.exception.BusinessException;
import net.auriema.app.repository.ImageRepository;
import net.auriema.app.request.AddImageRequest;
import net.auriema.app.response.AddImageResponse;
import net.auriema.app.service.ImageService;

@RestController
@RequestMapping("data")
public class DataResource {
	
	@Autowired
	ImageRepository imageRepository;

	@Autowired
	ImageService imageService;
	
	private void AddImageValidator (AddImageRequest request) {
		
		final int MEGABYTE = (int) 1e6;
		final int MAX_SIZE = 4 * MEGABYTE; 
		
		if (request.getType() == null)
			throw new BusinessException ("Image type is required.");
		if (ImageType.fromString(request.getType()) == null)
			throw new BusinessException ("Invalid image type.");

		if (request.getData() == null)
			throw new BusinessException ("Image data is required.");
		if (request.getData().length() > MAX_SIZE)
			throw new BusinessException ("The maximum image size allowed is 4MB");
		
	}

	@PostMapping ("/")
	public AddImageResponse add (@RequestBody AddImageRequest request) throws IOException {
		
		AddImageValidator (request);

		Image image = new Image();
		image.setId(UUID.randomUUID());
		image.setType(request.getType());
		image.setData(request.getData());
		
		imageRepository.save(image);
		
		imageService.generateThumbAndSave (image);
		
		return new AddImageResponse(image.getId().toString());
		
	}
	
	@GetMapping ("/{id}")
	public Image get (@PathVariable(value="id") UUID id){
		return imageRepository.findById(id).get();
	}
	
}
