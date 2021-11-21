package net.auriema.app.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import net.auriema.app.entity.CustomImage;
import net.auriema.app.entity.Thumb;
import net.auriema.app.enums.ImageSize;
import net.auriema.app.enums.ImageType;
import net.auriema.app.repository.ImageRepository;
import net.auriema.app.repository.ThumbRepository;

@Service
public class ImageService {
	
	final Integer MAX_WIDTH = 200;

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	ThumbRepository thumbRepository;
	
	public byte[] get (ImageSize imageSize, ImageType imageType, UUID id) {
		
		CustomImage image = null;
		
		switch (imageSize) {
		case RAW:
	 		image = imageRepository.findById(id).orElse(null);
	 		break;
		case THUMB:
	 		image = thumbRepository.findById(id).orElse(null);
	 		break;	 
		default:
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		if ((image == null) || (!imageType.getType().equals(image.getType())))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);		
		
		return Base64.getDecoder().decode(image.getData());
	}
	
	public BufferedImage generateThumb (BufferedImage source) {
		
		var width = Math.min(source.getWidth(), MAX_WIDTH);
		var height = source.getHeight() * MAX_WIDTH / source.getWidth();
				
	    Image image = source.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	    BufferedImage thumb = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    thumb.getGraphics().drawImage(image, 0, 0, null);
	    return thumb;
	   }
	
	public Thumb generateThumb (CustomImage source) throws IOException {
		
		InputStream input = new ByteArrayInputStream(Base64.getDecoder().decode(source.getData()));
		BufferedImage bi = generateThumb(ImageIO.read(input));
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(bi, source.getType(), out);
		
		Thumb thumb = new Thumb();
		thumb.setId(source.getId());
		thumb.setType(source.getType());		
		thumb.setData(Base64.getEncoder().encodeToString(out.toByteArray()));
		
		return thumb;
		
	}

	public void generateThumbAndSave(CustomImage source) throws IOException {
		Thumb thumb = generateThumb(source);
		thumbRepository.save(thumb);		
	}	

}
