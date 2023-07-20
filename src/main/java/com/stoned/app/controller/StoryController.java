package com.stoned.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stoned.app.exception.StoryAlreadyExistException;
import com.stoned.app.model.Story;
import com.stoned.app.service.StoryService;


@RestController
@CrossOrigin(origins = "${frontend.url}")
@RequestMapping("/api/stories")
public class StoryController {
	
	@Autowired
	private StoryService service;
	
	@PostMapping("/createImageStory/{userEmail}")
	public ResponseEntity<?> CreateStory(@RequestParam(value="image", required = false)MultipartFile file ,@PathVariable String userEmail) throws IOException, StoryAlreadyExistException{
		
		String data=service.uploadStory(file,userEmail);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(data);
	}
	
	@PostMapping("/createTextStory/{userEmail}")
	public ResponseEntity<?> createTextStory(@RequestParam("caption")String caption,@PathVariable String userEmail) throws StoryAlreadyExistException{
		
		String data=service.uploadTextStory(caption, userEmail);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(data);
	}
	
	
	
	@GetMapping("/getStory/{email}")
	public ResponseEntity<Story> getStoryByUser(@PathVariable String email)
	{
		Story data=service.getStoryByUserEmail(email);
		
		
		return new ResponseEntity<Story>(data,HttpStatus.OK);
	}
	
	@GetMapping("/getAllStories/{email}")
	public ResponseEntity<List<Story>> getAllUserStories(@PathVariable String email)
	{
		List<Story> stories=service.getAllUserStories(email);
		return new ResponseEntity<List<Story>>(stories,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteStory/{email}")
	public ResponseEntity<String> deleteStoryByUserId(@PathVariable String email)
	{
		return new ResponseEntity<String>(service.deleteStoryByUserId(email),HttpStatus.OK);
	}
	
	@Scheduled(cron = "0 * * * * *") // Run every minute
	public void cleanupExpiredStories() {
		service.getStoriesOlderThan24Hours();
	        
	}
	
	

}
