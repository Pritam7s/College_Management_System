package edu.cms.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import edu.cms.entity.Course;
import edu.cms.model.CourseDTO;
@Component
public class CourseConverter {

	
	//convert from CourseDTO to Course Entity
			public Course convertDTOToCourseEntity(CourseDTO courseDTO)
			{
				Course course= new Course();
				if(courseDTO!=null)
				{
					BeanUtils.copyProperties(courseDTO, course);
				}
				 return course;
}
			
			//convert from Course to CourseDTO
			public CourseDTO convertCourseEntityDTO(Course course)
			{
			CourseDTO courseDTO = new CourseDTO();
			if(course!=null)
			{
				BeanUtils.copyProperties(course, courseDTO);
			}
		
			return courseDTO;
}
}