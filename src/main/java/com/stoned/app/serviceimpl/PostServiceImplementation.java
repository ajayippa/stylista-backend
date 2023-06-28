package com.stoned.app.serviceimpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stoned.app.model.CustomerSignUp;
import com.stoned.app.model.Post;
import com.stoned.app.repository.CustomerSignupRepo;
import com.stoned.app.repository.PostRepository;
import com.stoned.app.service.PostService;
import com.stoned.app.util.ImageUtils;

@Service
public class PostServiceImplementation implements PostService{
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CustomerSignupRepo customerRepo;
	
	public String uploadPost(MultipartFile file,String caption,String userEmail) throws IOException{
		LocalDateTime currentDateTime = LocalDateTime.now();
		CustomerSignUp customerData=customerRepo.findByEmail(userEmail);
		
		Post data=postRepository.save(Post.builder()
				.type(file.getContentType())
				.userName(customerData.getUsername())
				.name(customerData.getFirstname())
				.userEmail(userEmail)
				.userId(customerData.getId())
				.captions(caption)
				.timestamp(currentDateTime.toString())
				.image(ImageUtils.compressImage(file.getBytes())).build());
		if(data!=null) {
			return "file uploaded successfully";
		}
		
		return null;
	}
	
	
	public List<Post> getAllUserPosts() {
		List<Post> allPost =postRepository.findAll();
		
		for (Post post : allPost) {
            byte[] compressedImage = post.getImage();
            byte[] decompressedImage = ImageUtils.decompressImage(compressedImage);
            post.setImage(decompressedImage);
        }
		
		return allPost;
	}

}
