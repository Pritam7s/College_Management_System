package edu.cms.service;

import java.util.List;

import edu.cms.entity.Teacher;
import edu.cms.model.TeacherDTO;

public interface TeacherService {

	String createTeacher(Teacher teacher);//To save teacher details
	TeacherDTO getTeacherById(int id);//To get teachers details by id
	List<TeacherDTO>  getAllTeacherDetails();// To show all teacher details
	TeacherDTO updateTeacherDetails(int id, Teacher teacher);//To update teacher details 
	String deleteTeacherById(int id);// To delete teacher details by id
	void deleteAllTeacherDetails();//To delete all teacher details from database 
	TeacherDTO findTeacherByEmail(String email);//To find teacher details by email
	List<TeacherDTO> findByFirstName(String name);//To find teacher details by first name
	}

