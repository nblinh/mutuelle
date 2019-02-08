package com.harmonie.irma.controllers;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harmonie.irma.statics.Question;

@RestController
@RequestMapping("/questions")
public class QuestionController {
	
	@GetMapping
	public ResponseEntity<Object> getQuestion() {
		JSONParser parser = new JSONParser();
	      Object obj=null;
		try {
			obj = parser.parse(Question.care_type_question);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(obj, HttpStatus.OK);
	}

}
