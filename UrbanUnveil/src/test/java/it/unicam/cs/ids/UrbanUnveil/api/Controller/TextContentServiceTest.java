package it.unicam.cs.ids.UrbanUnveil.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.TextContent;
import it.unicam.cs.ids.UrbanUnveil.api.repo.TextContentRepository;

@SpringBootTest
public class TextContentServiceTest {

    @Mock
    private TextContentRepository textContentRepository; // Supponiamo che tu abbia un repository

    @InjectMocks
    private TextContentService textContentService; // Classe da testare

    private TextContent textContent;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        // Inizializza un oggetto TextContent per i test
        textContent = new TextContent();
        textContent.setTitle("Sample Text Content");
        textContent.setDescr("Description of text content");
        textContent.setPath("textfile.txt");
    }

    @Test
    public void testSaveTextContent() throws IOException {
        // Definisci il comportamento del mock per simulare il salvataggio del contenuto
        when(textContentRepository.save(any(TextContent.class))).thenReturn(textContent);

        // Chiama il metodo da testare
        Content savedContent = textContentService.save(textContent);

        // Asserzioni per verificare il risultato
        assertEquals(textContent.getTitle(), savedContent.getTitle());
        assertEquals(textContent.getDescr(), savedContent.getDescr());
        assertEquals(textContent.getPath(), savedContent.getPath());
    }
}
