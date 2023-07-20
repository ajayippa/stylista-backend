package com.stoned.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stoned.app.model.Draft;
import com.stoned.app.model.Post;
import com.stoned.app.service.PostService;

@RestController
@CrossOrigin(origins = "${frontend.url}")
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostService service;
	
	@PostMapping("/createPost/{userEmail}")
	public ResponseEntity<?> CreatePost(@RequestPart("image")MultipartFile file ,@RequestPart("caption") String caption,@PathVariable String userEmail) throws IOException{
		
		String data=service.uploadPost(file,caption,userEmail);
		return ResponseEntity.status(HttpStatus.OK)
				.body(data);
	}
	
	@GetMapping("/getAllPosts")
	public ResponseEntity<List<Post>> getAllUserCaptions()
	{
		List<Post> posts=service.getAllUserPosts();
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@PostMapping("/createDraft/{userEmail}")
	public ResponseEntity<?> CreateDraft(@RequestPart("image")MultipartFile file ,@RequestPart("caption") String caption,@PathVariable String userEmail) throws IOException{
		
		String data=service.uploadDraft(file,caption,userEmail);
		return ResponseEntity.status(HttpStatus.OK)
				.body(data);
	}
	
	@GetMapping("/getAllDrafts")
	public ResponseEntity<List<Draft>> getAllUserDrafts(@RequestBody String email)
	{
		List<Draft> drafts=service.getAllUserDrafts(email);
		return new ResponseEntity<List<Draft>>(drafts,HttpStatus.OK);
	}
	
	
}
