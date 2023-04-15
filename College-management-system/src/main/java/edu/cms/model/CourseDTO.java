package edu.cms.model;

import edu.cms.entity.Teacher;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
	public class CourseDTO {

		
		private int subId;
		@NotNull(message="Sub Id cannot be null")
		private String subName;
		@NotNull(message="Price of the course cannot be null")
		@Size(min=3)
		private double price;
		
		private Teacher teacher;
	}
