package it.unicam.cs.ids.UrbanUnveil.api.Utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.ImageContent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ImageContentHandler implements ContentHandler {

    @Override
    public Content handleContent(Content content, MultipartFile file) throws IOException {
        try (InputStream in = file.getInputStream()) {
            BufferedImage image = ImageIO.read(in);
            int width = image.getWidth();
            int height = image.getHeight();
            String format = file.getContentType().split("/")[1];
            return new ImageContent(content, file.getOriginalFilename(), width, height, format);
        }
    }
}

