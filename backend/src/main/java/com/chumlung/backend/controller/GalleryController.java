package com.chumlung.backend.controller;

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

import com.chumlung.backend.model.Gallery;
import com.chumlung.backend.service.GalleryService;

@Controller
@CrossOrigin(origins = "http://www.hijoaaja.com", maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class GalleryController {

	@Autowired
	private GalleryService galleryService;

	@RequestMapping("/gallery/showall")
	@ResponseBody
	public List<Gallery> showAllGallery() {
		return galleryService.showAll();
	}

	@PostMapping("/gallery/add")
	@ResponseBody
	public ResponseEntity<?> save(@RequestBody(required = true) Gallery gallery) {
		try {
			galleryService.save(gallery);
			return ResponseEntity.status(HttpStatus.OK).body("Success");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
		}
	}

	@PostMapping("/gallery/delete")
	@ResponseBody
	public ResponseEntity<?> delete(@RequestBody JSONObject jsonRequest) {
		Long gallery_id = ((Number) jsonRequest.get("id")).longValue();
		Optional<Gallery> gallery = galleryService.findById(gallery_id);
		if (gallery.isPresent()) {
			galleryService.deleteById(gallery_id);
			return ResponseEntity.status(HttpStatus.OK).body("Success");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image not found.");
		}
	}
	
	@PostMapping("/gallery/addtoslide")
	@ResponseBody
	public ResponseEntity<?> addtoslide(@RequestBody(required=true) Gallery gallery){
		Optional<Gallery> galleryToUpdateCheck = galleryService.findById(gallery.getId());
		if(galleryToUpdateCheck.isPresent()) {
			Gallery galleryToUpdate = galleryService.findById(gallery.getId()).get();
			galleryToUpdate.setStatus(1);
			galleryService.save(galleryToUpdate);
			return ResponseEntity.status(HttpStatus.OK).body("Gallery added to slide successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image not found.");
		}
	}
	
	@PostMapping("/gallery/removefromslide")
	@ResponseBody
	public ResponseEntity<?> removeFromSlide(@RequestBody(required=true) Gallery gallery){
		Optional<Gallery> galleryToUpdateCheck = galleryService.findById(gallery.getId());
		if(galleryToUpdateCheck.isPresent()) {
			Gallery galleryToUpdate = galleryService.findById(gallery.getId()).get();
			galleryToUpdate.setStatus(0);
			galleryService.save(galleryToUpdate);
			return ResponseEntity.status(HttpStatus.OK).body("Gallery removed from slide successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image not found.");
		}
	}


}
