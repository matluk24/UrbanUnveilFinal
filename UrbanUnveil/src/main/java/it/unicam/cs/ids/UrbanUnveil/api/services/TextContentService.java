package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.TextContent;
import it.unicam.cs.ids.UrbanUnveil.api.repo.TextContentRepository;

@Service
@Qualifier("textContentService")
public class TextContentService implements ContentService {

	@Autowired
	private TextContentRepository repo;
	
	public TextContentService() {
		
	}
	
	@Override
	public Content save(Content content) throws IOException {
		
		if(content instanceof TextContent) {
			TextContent textContent = (TextContent) content;
			if(textContent.equals(repo.save(textContent))){
				return textContent;
			}
		}
		 return new TextContent();
	}

	@Override
	public Content load(Long id) throws IOException {
		if(repo.existsById(id)) {
			return repo.findById(id).get();
		 }
		 return new TextContent();
	}

	@Override
	public Content delete(Long id) throws IOException{
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return null;
		}
		return new TextContent();

	}
	
	@Override
	public Content update(Content content) throws IOException {
		if(content instanceof TextContent) {
			TextContent textContent = (TextContent) content;
			if(textContent.equals(repo.saveAndFlush(textContent))){
				return textContent;
			}
		}
		 return new TextContent();
		
	}
	
	public String getTextFromFile(Long i) throws IOException {
		
		String result="";
		Content c = this.load(i);
		
		if(c.getPath()==null) {
				System.out.println("No article has been written, create a file to start writing an article");
				return result;
			}
			File f = new File(c.getPath());
			Scanner myScanner = new Scanner(f);
			while(myScanner.hasNextLine()) {
				result = result +  myScanner.nextLine();
			}
			myScanner.close();
		return result;
		
	}
	
	public String writeArticle(Long i, String userInput) throws IOException  {
		TextContent c = (TextContent) this.load(i);
		String file="";
		
		File f = new File(c.getPath());
		f.setWritable(true);
		f.setReadable(true);
		c.setPath(f.getCanonicalPath());
		c = (TextContent) this.update(c);
		if(!f.exists()) {
			f.createNewFile();
			System.out.println("The file has been created in the following path: "+f.getCanonicalPath());
		}
		else {
			 file = this.getTextFromFile(c.getId());
			System.out.println("The file already exists you add in it");
		}
		FileWriter writer = new FileWriter(f, true);
		System.out.println(file);
		writer.append(" "+userInput);
		writer.close();
		file = file+" "+userInput;
		
		return file;
	}

}
