package edu.cms.servicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import edu.cms.entity.Course;
import edu.cms.model.CourseDTO;
import edu.cms.repository.CourseRepository;
import edu.cms.service.CourseService;
import edu.cms.util.CourseConverter;

@SpringBootTest
public class CourseServiceTest {

	@Autowired
	private CourseService courseService;
	@MockBean
	private CourseRepository courseRepository;
	@Autowired
	private CourseConverter courseConverter;
	//To test save the course method
	@Test
	void testCreateCourse()
	{
		Course course= Course.builder().subId(500).subName("Physics").price(8000).build();
		Mockito.when(courseRepository.save(course)).thenReturn(course);
		assertThat(courseService.createCourse(course)).isEqualTo("Course details saved sucessfully!!");
		
	}
	//To test show course by id
	@Test
	 void testGetCourseById()
	 {
		 Course course= Course.builder().subId(500).subName("Physics").price(8000).build();
		 Optional<Course> opcourse=Optional.of(course);
		 Mockito.when(courseRepository.findById(course.getSubId())).thenReturn(opcourse);
		 CourseDTO dto=courseConverter.convertCourseEntityDTO(course);
		 assertEquals(dto.getSubName(),"Physics");
		 
	 }
	@Test
	void testUpdateCourseDetails()
	{
		Course course = Course.builder().subId(600).subName("Java").price(9000).build();
		Optional<Course> opCourse=Optional.of(course);
		Course course1=opCourse.get();
		
		Mockito.when(courseRepository.findById(course.getSubId())).thenReturn(opCourse);
		course1.setSubName("DOT Net");
		
		Mockito.when(courseRepository.save(course1)).thenReturn(course1);
		
		CourseDTO dto1= courseConverter.convertCourseEntityDTO(course1);
		
		assertThat(courseService.updateCourseDetails(course1.getSubId(), course1).getSubName());
	}
	
}
