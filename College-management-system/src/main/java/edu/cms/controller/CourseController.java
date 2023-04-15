package edu.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cms.entity.Course;
import edu.cms.model.CourseDTO;
import edu.cms.service.CourseService;
import edu.cms.util.CourseConverter;
import javax.validation.Valid;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {

	@Autowired
	private CourseService courseService; 
	@Autowired
	private CourseConverter convertor1;
	
	// To save  the details 
	@PostMapping("/createCourse")
	public String createCourse( @Valid@RequestBody Course course)
	{
		return courseService.createCourse(course);
		
	}
	//To show the details by Id
	@GetMapping("/getCourseById/{id}")
	public CourseDTO getCourseById(@PathVariable("id") int id)
	{
		return courseService.getCourseById(id);
	}
	
	//To show the all the details
	@GetMapping("/getAllCourses")
	public List<CourseDTO> getAllCourseDetails()
	{
		return courseService.getAllCourseDetails();
	}
	
	//To update the details by Id
	@PutMapping("/updateCourse/{id}")
	public CourseDTO updateCourseDetails(@Valid @PathVariable("id") int id, @RequestBody CourseDTO courseDTO)
	{
		Course course=convertor1.convertDTOToCourseEntity(courseDTO);
		return courseService.updateCourseDetails(id, course);
	}
	
	//To delete the details by Id
	@DeleteMapping("/deleteCourseById/{id}")
	public String deleteCourserById(@PathVariable("id") int id)
	{
		return courseService.deleteCourseById(id);
	}

	// To assign the details by teacherId and courseId
	@PostMapping("/assignTeachertoCourse/{teachId}/{courseId}")
	public CourseDTO assignTeachertoCourse(@PathVariable ("teachId") int teacherId,@PathVariable("courseId") int courseId)
	{
		return 	courseService.assignTeachertoCourse(teacherId, courseId);
	}
	//To delete All details from course
	
	@DeleteMapping("/deleteAllCourse")
	public ResponseEntity<String> deleteAllCourse()
	{
		courseService.deleteAllCoursedetails();
		return new ResponseEntity<String>("Course details deleted succesfully" ,HttpStatus.OK);
		
	}
	
	
}
