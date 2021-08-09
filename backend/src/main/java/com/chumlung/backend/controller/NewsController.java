package com.chumlung.backend.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chumlung.backend.model.Contacts;
import com.chumlung.backend.model.News;
import com.chumlung.backend.service.NewsService;

@Controller
@SuppressWarnings("unchecked")
@CrossOrigin(origins = "http://www.hijoaaja.com", maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class NewsController {

	@Autowired
	private NewsService newsService;

	@RequestMapping("/news/showall")
	@ResponseBody
	public List<News> showAllNews() {
		return newsService.showAll();
	}

	@PostMapping("/news/add")
	public ResponseEntity<?> addNews(@RequestBody(required = true) News news) {
		try {
			LocalDateTime now = LocalDateTime.now();
			Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
			Date date = Date.from(instant);
			news.setDate(date);
			
	        String updateNews = news.getBody();
	        updateNews = updateNews.replaceAll("(\r\n|\n)", "<br />");
	        System.out.println(news.getBody());
	        news.setBody(updateNews);
	        
	        news.setImage("/testing/"+news.getId());

			
			newsService.save(news);
			
			JSONObject response = new JSONObject();
			response.put("message", "News added successfully");
			return ResponseEntity.status(HttpStatus.OK)
					.body(response.toString());

//			return ResponseEntity.status(HttpStatus.OK).body("News added successfully");
		} catch (Exception e) {
			JSONObject response = new JSONObject();
			response.put("message", e.getMessage());
			return new ResponseEntity<String>(response.toString(), HttpStatus.BAD_REQUEST);

//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Server Error");
		}
	}

	@PostMapping("/news/update")
	public ResponseEntity<?> updateNews(@RequestBody(required = true) News news) {
		try {
			Optional<News> newsToUpdateCheck = newsService.findById(news.getId());
			if (newsToUpdateCheck.isPresent()) {
				News newsToUpdate = newsService.findById(news.getId()).get();
				newsToUpdate.setAuthor(news.getAuthor());
				newsToUpdate.setBody(news.getBody());
				newsToUpdate.setCaption(news.getCaption());
				newsToUpdate.setImage(news.getImage());
				newsToUpdate.setTitle(news.getTitle());
				newsService.save(newsToUpdate);
				;
				return ResponseEntity.status(HttpStatus.OK).body("News updated successfully");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("News not Found");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Server Error");
		}
	}
	
	@PostMapping("/admin/news/delete")
	public ResponseEntity<?> delete(@RequestBody(required = true)News news){
		try {
		Optional<News> newsToDeleteCheck = newsService.findById(news.getId());
		if(newsToDeleteCheck.isPresent()) {
			System.out.println("Found news");
			News newsToDelete = newsToDeleteCheck.get();
			newsService.delete(newsToDelete); 
			JSONObject response = new JSONObject();
			response.put("message", "Data deleted successfully.");
			return ResponseEntity.status(HttpStatus.OK)
					.body(response.toString());

		}
		else {
			System.out.println("Not Found news");

			JSONObject response = new JSONObject();
			response.put("message", "Record not found");
			return new ResponseEntity<String>(response.toString(), HttpStatus.BAD_REQUEST);
		}
		}catch(Exception e) {
			System.out.println("Exception!!1");

			JSONObject response = new JSONObject();
			response.put("message", e.getMessage());
			return new ResponseEntity<String>(response.toString(), HttpStatus.BAD_REQUEST);

		}
	}
	
	
}
