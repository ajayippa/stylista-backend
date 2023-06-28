package com.stoned.app.util;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;




public class ImageUtils {

	 public static byte[] compressImage(byte[] imageBytes) {
		 try {
		        // Read the image bytes into a BufferedImage
		        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
		        BufferedImage image = ImageIO.read(inputStream);

		        // Create a ByteArrayOutputStream to hold the compressed image bytes
		        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		        // Compress the image using ImageIO and JPEG format with a compression quality of 0.8 (80%)
		        ImageIO.write(image, "jpeg", outputStream);

		        return outputStream.toByteArray();
		    } catch (IOException e) {
		        // Handle compression error
		        e.printStackTrace();
		        return imageBytes; // Return original image bytes if compression fails
		    }
	    }



	    public static byte[] decompressImage(byte[] compressedImageBytes) {
	    	try {
	            // Read the compressed image bytes into a BufferedImage
	            ByteArrayInputStream inputStream = new ByteArrayInputStream(compressedImageBytes);
	            BufferedImage compressedImage = ImageIO.read(inputStream);

	            // Create a ByteArrayOutputStream to hold the decompressed image bytes
	            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	            // Decompress the image using ImageIO and write it to the ByteArrayOutputStream
	            ImageIO.write(compressedImage, "jpeg", outputStream);

	            return outputStream.toByteArray();
	        } catch (IOException e) {
	            // Handle decompression error
	            e.printStackTrace();
	            return compressedImageBytes; // Return original compressed image bytes if decompression fails
	        }
	    	
	    }
	    
	   
}
