package edu.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.cms.entity.Course;
import edu.cms.model.CourseDTO;
import edu.cms.service.CourseService;
import edu.cms.util.CourseConverter;
import jakarta.validation.Valid;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseConverter converter;
	
	@PostMapping("/createCourse")
	public String createCourse(@Valid @RequestBody CourseDTO courseDTO)
	{
		final Course course = converter.convertDTOToCourseEntity(courseDTO);
		return courseService.createCourse(course);
	}
	
	@GetMapping("/getCourseById/{id}")
	public CourseDTO getCourseById(@PathVariable("id") int id)
	{
		return courseService.getCourseById(id);
	} 
	
	@GetMapping("/getAllCourses")
	public List<CourseDTO> getAllCourseDetails()
	{
		return courseService.getAllCourseDetails();
	}
	
	@PutMapping("/updateCourse/{id}")
	public CourseDTO updateCourseDetails(@Valid @PathVariable("id") int id,
			@RequestBody CourseDTO courseDTO)
	{
		Course course = converter.convertDTOToCourseEntity(courseDTO);
		return courseService.updateCourseById(id, course);
	}
	
	@DeleteMapping("/deleteCourseById/{id}")
	public String deleteCourseById(@PathVariable("id") int id)
	{
		return courseService.deleteCourseById(id);
	}
	
	@DeleteMapping("/deleteAllCourses")
	public ResponseEntity<String> deleteAllCourses()
	{
		courseService.deleteAllCourseDetails();
		return new ResponseEntity<String>("All Course details have been deleted successfully!", HttpStatus.OK);
	}
}
