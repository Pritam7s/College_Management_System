package edu.cms.repository;


	import org.springframework.data.jpa.repository.JpaRepository;

	import edu.cms.entity.Course;

	public interface CourseRepository extends JpaRepository<Course, Integer>{

	}


