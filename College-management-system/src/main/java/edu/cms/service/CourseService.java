package edu.cms.service;

import java.util.List;

import edu.cms.entity.Course;
import edu.cms.model.CourseDTO;

public interface CourseService {

	String createCourse(Course course);//To save the course details
	CourseDTO getCourseById(int id);// To get the course details by id
	List<CourseDTO> getAllCourseDetails();//To view all course details
	CourseDTO updateCourseDetails(int id, Course course);// To update course details
	String deleteCourseById(int id); //To delete course details by id
	CourseDTO assignTeachertoCourse(int teacherId, int courseId);//assign Teacher to Course 
	void deleteAllCoursedetails();// To delete all course details
}
