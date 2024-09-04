package it.unicam.cs.ids.UrbanUnveil.api.Controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

import it.unicam.cs.ids.UrbanUnveil.api.DesignPattern.ContentHandlerFactory;
import it.unicam.cs.ids.UrbanUnveil.api.DesignPattern.ContentServiceFactory;
import it.unicam.cs.ids.UrbanUnveil.api.Utils.ContentHandler;
import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.services.ContentService;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentServiceFactory contentServiceFactory;
    
    @Autowired
    private ContentHandlerFactory contentHandlerFactory;
    
    public ContentController(ContentServiceFactory contentServiceFactory, ContentHandlerFactory contentHandlerFactory) {
        this.contentServiceFactory = contentServiceFactory;
        this.contentHandlerFactory = contentHandlerFactory;
    }
	
	public ContentController() {
		
	}
    


    @PostMapping(path = "/uploadContent", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Content> ContentUpload(@RequestPart("content") Content content, @RequestPart("file") MultipartFile file) throws IOException{
        if (file.isEmpty()) {
            return new ResponseEntity<Content>(content, HttpStatus.BAD_REQUEST);
        }



        	// Estraggo l'handler giusto e creo l'oggetto Content
        	String contentType = file.getContentType();
        	ContentHandler handler = contentHandlerFactory.getHandler(contentType);
        	content = handler.handleContent(content, file);
        	
        	System.out.println(content);
            
            //estraggo il Service giusto e salvo il file nella cartella di destinazione
            ContentService contentService = contentServiceFactory.getService(content);
            File f= new File(content.getPath());
            f.createNewFile();
            file.transferTo(f);
            
            
            Content savedContent = contentService.save(content);
            HttpStatus httpStatus = HttpStatus.CONFLICT;
            if(savedContent.equals(content)) {
            	httpStatus = HttpStatus.OK;
            }
            return new ResponseEntity<Content>(savedContent, httpStatus);
        }
    
    @GetMapping("/load")
    public ResponseEntity<Content> loadContent(@PathVariable Long id, @PathVariable String fileType) throws IOException{
    	// Estraggo l'handler giusto e creo l'oggetto Content
    	Content content;
    	HttpStatus httpStatus;

        ContentService contentService = contentServiceFactory.getService(fileType);
        
        content=contentService.load(id);
        
        if(content==null) {
            httpStatus = HttpStatus.NOT_FOUND;

        }
        else {
        	httpStatus= HttpStatus.OK;
        }
        
        return new ResponseEntity<Content>(content, httpStatus);
        	
        }
    
    @DeleteMapping("/delete")
    public  ResponseEntity<Content>  deleteContent(@RequestBody Content content) throws IOException{
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
    public ResponseEntity<Content> updateContent(@RequestBody Content content) throws IOException{
        ContentService contentService = contentServiceFactory.getService(content);
        
        Content c = contentService.update(content);
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        
        if(c.equals(content)) {
        	httpStatus = HttpStatus.OK;
        }else
			httpStatus = HttpStatus.BAD_REQUEST;
        
        return new ResponseEntity<Content>(c, httpStatus);
    }
    
}
