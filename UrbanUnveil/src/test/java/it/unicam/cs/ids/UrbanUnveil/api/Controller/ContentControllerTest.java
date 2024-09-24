package it.unicam.cs.ids.UrbanUnveil.api.Controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.TextContent;
import it.unicam.cs.ids.UrbanUnveil.api.models.User;
import it.unicam.cs.ids.UrbanUnveil.api.services.TextContentService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class ContentControllerTest {

	@Autowired
	private UserController userC;
	
	@Autowired
	private ContentController contentC;
	
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private TextContentService textSer;

    @Test
    @Order(value = 1)
    public void testContentUpload() throws Exception {
        // Creare un file di esempio
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "/test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "This is a test file content.".getBytes()
        );
        User u = new User("Mattia", "Luciani", "mattia@boh.it", "MNBHDGE", "1234", null);
		
		u =userC.add(u).getBody();

        Content c = new Content(u, null, "Test Title", "Test Description", "", file.getContentType());

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json= ow.writeValueAsString(c);
        
        // Eseguire la richiesta di upload
        mockMvc.perform(multipart("/content/uploadContent")
                .file(file)
                .param("contnet", json))
                .andExpect(status().isOk());
        
    }

    @Test
    @Order(value = 2)
    public void testWriteArticle() {
    	
    	try {
			textSer.writeArticle(Integer.toUnsignedLong(1), "Ciaoooo");
			System.out.println(textSer.getTextFromFile(Integer.toUnsignedLong(1)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
