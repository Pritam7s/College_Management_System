package edu.cms.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cms.entity.Course;
import edu.cms.entity.Teacher;
import edu.cms.exception.ResourceNotFoundException;
import edu.cms.model.CourseDTO;
import edu.cms.repository.CourseRepository;
import edu.cms.repository.TeacherRepository;
import edu.cms.service.CourseService;
import edu.cms.util.CourseConverter;
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courserepository;
	@Autowired
	private CourseConverter converter;
	@Autowired
	private TeacherRepository teacherrepository;
	
	//To save details 
	@Override
	public String createCourse(Course course) {
		 String msg=null;
		 courserepository.save(course);
		if(course!=null)
		{
			
			msg="Course details saved sucessfully!!";
		}
		
		return msg;
	}
	
	//To Show the course details by Id
	@Override
	public CourseDTO getCourseById(int id) {
		
		Course course =courserepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course", "Id", id));
		return converter.convertCourseEntityDTO(course);
	}
	
	//To Show all course details 
	@Override
	public List<CourseDTO> getAllCourseDetails() {
		List<Course> courses=courserepository.findAll();
		List<CourseDTO>courseDTO= new ArrayList<>();
		for(Course c: courses)
		{
			courseDTO.add(converter.convertCourseEntityDTO(c));
		}
		return courseDTO;
	}
	
	@Override
	public CourseDTO updateCourseDetails(int id, Course course) {
		
		Course existingC =courserepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course", "Id",id));
		
		//we will get the data from client and set the data in existing course
		
		existingC.setSubId(course.getSubId());
		existingC.setSubName(course.getSubName());
		existingC.setPrice(course.getPrice());
		
		courserepository.save(existingC);
		return converter.convertCourseEntityDTO(existingC);
		
	}
	
    //TO delete course by id
	@Override
	public String deleteCourseById(int id) {
		String msg=null;
		Optional<Course>course=courserepository.findById(id);
		if(course.isPresent())
		{
			courserepository.deleteById(id);
			msg="Course with id "+id+" has been deleted!";	
		}
		return msg;
	}
	
   //To assign teacher to course
	@Override
	public CourseDTO assignTeachertoCourse(int teacherId, int courseId) {
		Teacher teacher= teacherrepository.findById(teacherId).get();
		Course course= courserepository.findById(courseId).get();
		
		course.setTeacher(teacher);
		Course course1=courserepository.save(course);
		
		return converter.convertCourseEntityDTO(course1);
	}
	
    //To delete all course details 
	@Override
	public void deleteAllCoursedetails() {
		courserepository.deleteAll();
	}
}
