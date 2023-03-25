package edu.cms.service;

import java.util.List;

import edu.cms.entity.Course;
import edu.cms.model.CourseDTO;

public interface CourseService {

	String createCourse(Course course);
	CourseDTO getCourseById(int id);
	List<CourseDTO> getAllCourseDetails();
	CourseDTO updateCourseById(int id, Course course);
	String deleteCourseById(int id);
	void deleteAllCourseDetails();
	
}
