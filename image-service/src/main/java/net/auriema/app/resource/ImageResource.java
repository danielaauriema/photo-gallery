package net.auriema.app.resource;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.auriema.app.enums.ImageSize;
import net.auriema.app.enums.ImageType;
import net.auriema.app.service.ImageService;

@RestController
public class ImageResource {
	
	@Autowired
	ImageService imageService;	
	
	@GetMapping (value = "/jpg/{size}/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getJpgImage (@PathVariable(value="size") String size, @PathVariable(value="fileName") String fileName) {	
		return getImage(ImageType.JPG, size, fileName); 
	}
	
	@GetMapping (value = "/png/{size}/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] getPngImage (@PathVariable(value="size") String size, @PathVariable(value="fileName") String fileName) {	
		return getImage(ImageType.PNG, size, fileName); 
	}
	
	private byte[] getImage (ImageType imageType, String sizeParam, String fileName) {

		if ((sizeParam == null) || (fileName == null))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);		
		
		String[] fileNames = fileName.split("\\.");
		
		if (fileNames.length != 2)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		var imageSize = ImageSize.fromString(sizeParam);

		if ((imageSize == null) || (!imageType.getType().equals (fileNames[1])))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		return imageService.get(imageSize, imageType, UUID.fromString(fileNames[0]));
		
	}
	
}
