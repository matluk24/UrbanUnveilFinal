package it.unicam.cs.ids.UrbanUnveil.api.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ContentControllerTest {

	@Autowired
	private UserController userC;
	
    @Autowired
    private MockMvc mockMvc;

    @Test
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

        Content c = new Content(u, null, "Test Title", "Test Description", "");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String a= ow.writeValueAsString(c);
        MockMultipartFile content = new MockMultipartFile("content", "", "application/json", a.getBytes());
        
        // Eseguire la richiesta di upload
        mockMvc.perform(multipart("/content/uploadContent")
        		.file(content)
                .file(file)
                .param("title", "Test Title")
                .param("descr", "Test Description"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.descr").value("Test Description"));
    }
}
