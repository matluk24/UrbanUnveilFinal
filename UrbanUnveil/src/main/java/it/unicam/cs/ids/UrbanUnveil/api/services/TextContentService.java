package it.unicam.cs.ids.UrbanUnveil.api.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import it.unicam.cs.ids.UrbanUnveil.api.models.Content;
import it.unicam.cs.ids.UrbanUnveil.api.models.TextContent;
import it.unicam.cs.ids.UrbanUnveil.api.repo.ContentRepository;

public class TextContentService implements ContentService<TextContent> {

	
	private ContentRepository repo;
	
	@Autowired
	public TextContentService(ContentRepository r) {
			repo=r;
	}
	
	public TextContentService() {
		
	}
	
	@Override
	public Content save(TextContent content) {
		return repo.save(content);
	}

	@Override
	public Content load(Long id) {
		if(repo.existsById(id)) {
			return repo.findById(id).get();
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;

	}
	
	@Override
	public Content update(Long id, String t, String d, String p) {
		Content c = this.load(id);
		if(t!=null) {
			c.setTitle(t);
		}
		if(d!=null) {
			c.setDescr(t);
		}
		if(p!=null) {
			c.setPath(t);
		}
		
		return repo.saveAndFlush(c);
		
	}
	
	public String getTextFromFile(Long i) throws FileNotFoundException {
		
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
		Content c = this.load(i);
		String file="";
		
		File f = new File(System.getProperty("user.dir")+"/tmp", c.getTitle()+".txt");
		f.setWritable(true);
		f.setReadable(true);
		c = this.update(i,  c.getTitle(),  c.getDescr(), f.getCanonicalPath());
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
