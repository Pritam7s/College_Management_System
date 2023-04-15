package edu.cms.servicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import edu.cms.entity.Course;
import edu.cms.entity.Teacher;
import edu.cms.model.TeacherDTO;
import edu.cms.repository.CourseRepository;
import edu.cms.repository.TeacherRepository;
import edu.cms.service.TeacherService;
import edu.cms.util.TeacherConverter;
//To test Service Layer that this layer method is working fine or not
@SpringBootTest
public class TeacherServiceTest {

	@Autowired
	private TeacherService teacherservice;
	@MockBean
	private TeacherRepository teacherrepository;
	@Autowired 
	private TeacherConverter converter;
	
	//To test the save part of the service 
	@Test
	void testCreateTeacher()
	{
		Teacher teacher=Teacher.builder().firstName("Karan").lastName("Johar").email("karan56@outlook.com").contact("986754321").build();
		Mockito.when(teacherrepository.save(teacher)).thenReturn(teacher);
		assertThat(teacherservice.createTeacher(teacher)).isEqualTo("Teacher details saved successfully!");
		
	}
	//To test the show the details by id 
	@Test
	void testGetTeacherById()
	{
	Teacher teacher= Teacher.builder().firstName("Karan").lastName("Johar").email("karan@outlook.com").contact("98557643435").build();
	
	Optional<Teacher> opTeach =Optional.of(teacher);
	Mockito.when(teacherrepository.findById(teacher.getId())).thenReturn(opTeach);
	
	TeacherDTO dto= converter.convertTeacherEntityToDTO(teacher);
	assertEquals(dto.getFirstName(),teacherservice.getTeacherById(teacher.getId()).getFirstName());
	}
  
	
	//To test the update part of the service
	@Test
	void testUpdateTeacher()
	{
		Teacher teacher=Teacher.builder().firstName("Karan").lastName("Johar").email("karan56@outlook.com").contact("986754321").build();
		
		Optional<Teacher> opTeach=Optional.of(teacher);
		Teacher teacher1=opTeach.get();
		
		Mockito.when(teacherrepository.findById(teacher.getId())).thenReturn(opTeach);
		teacher1.setFirstName("Rahul");
		
		Mockito.when(teacherrepository.save(teacher1)).thenReturn(teacher1);
		
		TeacherDTO dto1=converter.convertTeacherEntityToDTO(teacher1);
		assertThat(teacherservice.updateTeacherDetails(teacher1.getId(), teacher1).getFirstName()).isEqualTo("Rahul");
	}
	//To test getAll teacher details 
	@Test
	void testGetAllTeacherDetails()
	{
		Teacher teacher= Teacher.builder().firstName("Sabdick").lastName("Das").email("sabdickdas97@gmail.com").contact("8479078708").build();
		Teacher teacher1= Teacher.builder().firstName("Rahul").lastName("Das").email("rahuldas99@gmail.com").contact("848908708").build();
		
		List<Teacher> teacherlist =new ArrayList<>();
		teacherlist.add(teacher);
		teacherlist.add(teacher1);
		
		Mockito.when(teacherrepository.findAll()).thenReturn(teacherlist);
		List<TeacherDTO> dto = teacherservice.getAllTeacherDetails();
		List<Teacher> teach= new ArrayList<>();
		}
	
	
}
