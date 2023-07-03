package com.pes.model.request;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "feedback")
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String interviewerName;

	private String candidateName;

	private String result;

	private String remark;

	private LocalDate date;

	private Integer attitude;

	private Integer learnability;

	private Integer teamBehaviour;

	// Constructors, getters, and setters
}
