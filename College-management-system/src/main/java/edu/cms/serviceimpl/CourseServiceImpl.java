package edu.cms.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cms.entity.Course;
import edu.cms.exception.ResourceNotFoundException;
import edu.cms.model.CourseDTO;
import edu.cms.repository.CourseRepository;
import edu.cms.service.CourseService;
import edu.cms.util.CourseConverter;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CourseConverter converter;
	
	@Override
	public String createCourse(Course course) {
		String msg = null;
		
		courseRepository.save(course);
		if(course!=null) {
			msg = "Course Details Saved Successfully.";
		}
		return msg;
	}

	@Override
	public CourseDTO getCourseById(int id) {
		Course course = courseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course","Id",id));
		return converter.convertCourseEntityToDTO(course);
	}

	@Override
	public List<CourseDTO> getAllCourseDetails() {
		List<Course> Courses =courseRepository.findAll();
		List<CourseDTO> courseDTO = new ArrayList<>();
		for(Course t: Courses)
		{
			courseDTO.add(converter.convertCourseEntityToDTO(t));
		}
		return courseDTO;
	}

	@Override
	public CourseDTO updateCourseById(int id, Course course) {
		Course cs = courseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course","Id",id));
		
		//we will get data from client and set in the existing course
		cs.setSubId(course.getSubId());
		cs.setSubName(course.getSubName());
		cs.setPrice(course.getPrice());
		
		courseRepository.save(cs);
		return converter.convertCourseEntityToDTO(cs);
	}

	@Override
	public String deleteCourseById(int id) {
		String msg = null;
		Optional<Course> course = courseRepository.findById(id);
		if(course.isPresent())
		{
			courseRepository.deleteById(id);
			msg = "Course with id "+id+" has been deleted!";
		}
		else
		{
			throw new ResourceNotFoundException("Course", "Id", id);
		}
		
		return msg;
	}

	@Override
	public void deleteAllCourseDetails() {

		courseRepository.deleteAll();
	}

}
