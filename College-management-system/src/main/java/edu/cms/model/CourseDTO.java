package edu.cms.model;

import edu.cms.entity.Teacher;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CourseDTO {

	
	private int subId;
	@NotNull
	private String subName;
	@NotNull
	private double price;
	
	private Teacher teacher;
}
