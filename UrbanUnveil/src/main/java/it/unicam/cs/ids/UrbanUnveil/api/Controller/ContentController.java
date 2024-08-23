package it.unicam.cs.ids.UrbanUnveil.api.Controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;

import it.unicam.cs.ids.UrbanUnveil.api.DesignPattern.ContentServiceFactory;
import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.ImageContent;
import it.unicam.cs.ids.UrbanUnveil.api.models.TextContent;
import it.unicam.cs.ids.UrbanUnveil.api.models.VideoContent;
import it.unicam.cs.ids.UrbanUnveil.api.services.ContentService;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentServiceFactory contentServiceFactory;
	private static final String UPLOAD_DIR = "./Utils/Content/";
    
    public ContentController(ContentServiceFactory o) {
    	contentServiceFactory=o;
	}
	
	public ContentController() {
		
	}
    


    @PostMapping("/uploadContent")
    public ResponseEntity<Content> ContentUpload(@RequestBody Content content, MultipartFile file) throws IOException{
        if (file.isEmpty()) {
            return new ResponseEntity<Content>(content, HttpStatus.BAD_REQUEST);
        }



            // Gestione specifica basata sul tipo di file
        	content = hendlerContentCostructor(content, file);
            
            //estraggo il Service giusto e salvo il file nella cartella di destinazione
            ContentService contentService = contentServiceFactory.getService(content);
            file.transferTo(new File(content.getPath()));
            
            
            Content c = contentService.save(content);
            HttpStatus httpStatus = HttpStatus.CONFLICT;
            if(c.equals(content)) {
            	httpStatus = HttpStatus.OK;
            }
            return new ResponseEntity<Content>(c, httpStatus);
        }
       
    @GetMapping("/load")
    public ResponseEntity<Content> loadContent(@RequestBody Content content) throws IOException {
        // Logica per determinare il tipo di contenuto basato su contentEnum

        ContentService contentService = contentServiceFactory.getService(content);
        
        // TODO prendere il content giusto
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
         
        
        return new ResponseEntity<Content>(content, httpStatus);
        	
        }
    
    @DeleteMapping("/delete")
    public  ResponseEntity<Content>  deleteContent(@RequestBody Content content) throws IOException {
        // Logica per determinare il tipo di contenuto basato su contentEnum

        ContentService contentService = contentServiceFactory.getService(content);
        
        Content c = contentService.delete(content.getId());
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
         
        if(c==null) {
        	httpStatus = HttpStatus.OK;
        }
        
        return new ResponseEntity<Content>(c, httpStatus);
        	
    }
    
    @PostMapping("/update")
    public ResponseEntity<Content> updateContent(@RequestBody Content content) throws IOException {
        ContentService contentService = contentServiceFactory.getService(content);
        
        Content c = contentService.update(content);
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        
        if(c.equals(content)) {
        	httpStatus = HttpStatus.OK;
        }else if (c == null)
			httpStatus = HttpStatus.BAD_REQUEST;
        
        return new ResponseEntity<Content>(c, httpStatus);
    }
    public Content hendlerContentCostructor(Content content, MultipartFile file) {
        
    	String contentType = file.getContentType();

    	
    	if (contentType != null) {
            if (contentType.startsWith("image/")) {
            	
            	return hendlerImageContent(content, file);
            	
            } else if (contentType.startsWith("video/")) {

            	return hendleVideoContent(content, file);
            	
            } else if (contentType.startsWith("text/plain")) {
  
            	return hendlerTextContent(content, file);
            }
        }
    	return null;
    	
    }
    
    public Content hendlerImageContent(Content content, MultipartFile file) {
    	File dest = new File(UPLOAD_DIR +"image/"+ file.getOriginalFilename());
    	//TODO add param type
    	
    	return new ImageContent(content, dest.toString());
    }
    public Content hendleVideoContent(Content content, MultipartFile file) {
    	File dest = new File(UPLOAD_DIR +"video/"+ file.getOriginalFilename());
    	//TODO add param type
    	
    	return new VideoContent(content, dest.toString());
    }
    public Content hendlerTextContent(Content content, MultipartFile file) {
    	File dest = new File(UPLOAD_DIR +"file/"+ file.getOriginalFilename());
    	//TODO add param type
    	
    	return new TextContent(content, dest.toString());
    }
    
}
