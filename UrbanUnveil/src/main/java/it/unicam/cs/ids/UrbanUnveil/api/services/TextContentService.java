package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.TextContent;
import it.unicam.cs.ids.UrbanUnveil.api.repo.ContentRepository;

@Service
public class TextContentService implements ContentService<TextContent> {

	
	private ContentRepository repo;
	private static final String UPLOAD_DIR="/src/main/resources/text";
	
	@Autowired
	public TextContentService(ContentRepository r) {
			repo=r;
	}
	

	public TextContentService() {
		
	}
	
	/*@Override
	public Content save(TextContent content) throws IOException {
		MultipartFile file = content.getFile();
		if (file.isEmpty()) {
			return null;
		}
		 if(content.equals(repo.save(content))){
			 return content;
		 }
		 return new TextContent();
	}*/

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
	
	/*@Override
	public Content update(TextContent c) throws IOException {
		MultipartFile file = c.getFile();
		if (file.isEmpty()) {
			return null;
		}
		 if(c.equals(repo.saveAndFlush(c))){
			 return c;
		 }
		 return new TextContent();
		
	}*/
	
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
		
		File f = new File(UPLOAD_DIR, c.getTitle()+".txt");
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
	
	@Override
	public Content save(TextContent content) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Content update(TextContent c) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
