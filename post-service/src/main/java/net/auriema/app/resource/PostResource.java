package net.auriema.app.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.auriema.app.client.ImageClient;
import net.auriema.app.client.request.AddImageRequest;
import net.auriema.app.client.response.AddImageResponse;
import net.auriema.app.entity.Post;
import net.auriema.app.repository.PostRepository;
import net.auriema.app.request.AddPostRequest;

@RestController
public class PostResource {
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	ImageClient imageClient;
	
	@PostMapping ("/")
	public Post add (@RequestBody AddPostRequest request) throws Exception {

		Post post = new Post();
		post.setTitle(request.getTitle());
		post.setDescription(request.getDescription());
		
		AddImageRequest imageRequest = new AddImageRequest();
		imageRequest.setType(request.getType());
		imageRequest.setData(request.getData());
		AddImageResponse imageResponse = imageClient.AddImage(imageRequest);
		
		post.setName(imageResponse.getId());
		post.setType(request.getType());
		
		postRepository.save(post);
		return post;
	}	
	
	@GetMapping ("/")
	public List<Post> getAll (){
		return postRepository.findAll();
	}
	
	@GetMapping ("/{id}")
	public Post get (@PathVariable(value="id") Long id){
		return postRepository.findById(id).get();
	}

}
