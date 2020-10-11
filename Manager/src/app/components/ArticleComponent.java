package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Articles;
import app.repositories.ArticlesRepository;

@Component
public class ArticleComponent {

	@Autowired
	ArticlesRepository articleRepo;
	
	@PostConstruct
	public void init() {
		
		if(articleRepo.count()==0) {
			Articles keyboard = new Articles();
			keyboard.setName("keyboard");
			keyboard.setPrice(22.90);
			keyboard.setDescription("Universal qwerty usb keyboard");
			articleRepo.save(keyboard);
			
			Articles mouse = new Articles();
			mouse.setName("mouse");
			mouse.setPrice(13.90);
			mouse.setDescription("USB mouse");
			articleRepo.save(mouse);
			
			Articles speaker = new Articles();
			speaker.setName("speaker");
			speaker.setPrice(45.0);
			speaker.setDescription("Bluetooth speaker with long autonomy");					
			articleRepo.save(speaker);
		}
	}
}
