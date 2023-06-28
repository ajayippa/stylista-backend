package com.stoned.app.serviceimpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stoned.app.exception.StoryAlreadyExistException;
import com.stoned.app.model.CustomerSignUp;
import com.stoned.app.model.Story;
import com.stoned.app.repository.CustomerSignupRepo;
import com.stoned.app.repository.StoryRepository;
import com.stoned.app.service.StoryService;
import com.stoned.app.util.ImageUtils;

@Service
public class StoryServiceImplementation implements StoryService{

	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private CustomerSignupRepo customerRepo;
	
	public String uploadStory(MultipartFile file,String caption,String userEmail) throws IOException, StoryAlreadyExistException{
		Story res=storyRepository.findByUserEmail(userEmail);
		if(res==null)
		{
		LocalDateTime currentDateTime = LocalDateTime.now();
		CustomerSignUp customerData=customerRepo.findByEmail(userEmail);
		
		Story data=storyRepository.save(Story.builder()
				.type(file.getContentType())
				.userName(customerData.getUsername())
				.name(customerData.getFirstname())
				.userEmail(userEmail)
				.userId(customerData.getId())
				.captions(caption)
				.timestamp(currentDateTime.toString())
				.image(ImageUtils.compressImage(file.getBytes())).build());
		if(data!=null) {
			return "Success";
		}
		}
		else
		{
			throw new StoryAlreadyExistException("Story Already Exist");
		}
		
		return null;
	}
	
	
	
	public Story getStoryByUserEmail(String email){
		Story stories = storyRepository.findByUserEmail(email);
        
		byte[] data=ImageUtils.decompressImage(stories.getImage());
		stories.setImage(data);
			
		return stories; 
	}
	
	public List<Story> getAllUserStories(String email) {
		List<Story> stories =storyRepository.findByUserEmailNotEquals(email);
		
		for (Story story : stories) {
            byte[] compressedImage = story.getImage();
            byte[] decompressedImage = ImageUtils.decompressImage(compressedImage);
            story.setImage(decompressedImage);
        }
		
		return stories;
	}
	
	public String deleteStoryByUserId(String email) {
		Story data=storyRepository.findByUserEmail(email);
		if(data!=null) {
			storyRepository.delete(data);
			return "success";
		}
		else {
			return "fail";
		}
	}
	
	
	public void getStoriesOlderThan24Hours() {
        LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);
        String date=twentyFourHoursAgo.toString();
        if(storyRepository.findByTimestamp(date)!=null)
        {
        	storyRepository.deleteByTimestamp(date);
        	System.out.println(date);
        }
        return;
        
    }

    
}
