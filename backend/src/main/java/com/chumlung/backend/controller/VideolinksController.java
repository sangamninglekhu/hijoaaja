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
import com.chumlung.backend.model.Videolinks;
import com.chumlung.backend.service.VideolinksService;

@Controller
//@CrossOrigin(origins = "http://www.chumlung.com", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class VideolinksController {

	@Autowired
	private VideolinksService videolinksService;

	@RequestMapping("/videolinks/showall")
	@ResponseBody
	public List<Videolinks> showAllVideolinks() {
		return videolinksService.findAll();
	}

	@PostMapping("/admin/videolinks/add")
	@ResponseBody
	public ResponseEntity<?> addVideolink(@RequestBody(required = true) Videolinks videolink) {
		try {
			videolinksService.save(videolink);
			return ResponseEntity.status(HttpStatus.OK).body("Videolink added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Server Error");

		}
	}

	@PostMapping("/admin/videolinks/delete")
	@ResponseBody
	public ResponseEntity<?> deleteVideolink(@RequestBody JSONObject jsonRequest) {
		try {
			Long videolink_id = ((Number) jsonRequest.get("id")).longValue();
			Optional<Videolinks> videolinkToDeleteCheck = videolinksService.findById(videolink_id);
			if (videolinkToDeleteCheck.isPresent()) {
				videolinksService.deleteById(videolink_id);
				return ResponseEntity.status(HttpStatus.OK).body("Videolink deleted successfully");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Videolink not found");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Server Error");
		}
	}

	@PostMapping("/admin/videolinks/update")
	@ResponseBody
	public ResponseEntity<?> updateVideolink(@RequestBody(required = true) Videolinks videolink) {
		try {
			Optional<Videolinks> videolinkToUpdateCheck = videolinksService.findById(videolink.getId());
			if (videolinkToUpdateCheck.isPresent()) {
				Videolinks videolinktpUpdate = videolinkToUpdateCheck.get();
				videolinktpUpdate.setLink(videolink.getLink());
				videolinktpUpdate.setStatus(videolink.getStatus());
				videolinktpUpdate.setNews_id(videolink.getNews_id());
				videolinksService.save(videolinktpUpdate);
				return ResponseEntity.status(HttpStatus.OK).body("Videolink updated successfully");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Videolink not found");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Server Error");
		}
	}

	@PostMapping("/admin/videolinks/addtohome")
	@ResponseBody
	public ResponseEntity<?> addToHome(@RequestBody(required = true) Videolinks videolink) {
		try {
			Optional<Videolinks> videolinkToHomeCheck = videolinksService.findById(videolink.getId());
			if (videolinkToHomeCheck.isPresent()) {
				Videolinks videolinkToHome = videolinkToHomeCheck.get();
				videolinkToHome.setStatus(1);
				videolinksService.save(videolinkToHome);
				return ResponseEntity.status(HttpStatus.OK).body("Videolink added to home successfully");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Videolink not found");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Serverr Error");
		}
	}

	
	@PostMapping("/admin/videolinks/removefromhome")
	@ResponseBody
	public ResponseEntity<?> removeFromHome(@RequestBody(required = true) Videolinks videolink) {
		try {
			Optional<Videolinks> videolinkToHomeCheck = videolinksService.findById(videolink.getId());
			if (videolinkToHomeCheck.isPresent()) {
				Videolinks videolinkToHome = videolinkToHomeCheck.get();
				videolinkToHome.setStatus(0);
				videolinksService.save(videolinkToHome);
				return ResponseEntity.status(HttpStatus.OK).body("Videolink removed from home successfully");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Videolink not found");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Serverr Error");
		}
	}

}
