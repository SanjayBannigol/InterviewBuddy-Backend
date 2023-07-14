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

	    private String remark;

	    private Integer score;
	    
	    private Integer attitude;
	    
	    private Integer learnability;
	    
	    private Integer teamBehaviour;
	    
	    private String result;
	    
	    private LocalDate date;
	    
	    private Integer presentationSkills;

	    private Integer goalOriented;

	    private Integer aptitude;

	    private Integer ethics;

	    private Integer academics;

	    private Integer businessAcumen;
	 
	    private Integer appearanceAndPresentablity;

	    private Integer quailtyAwareness;

	    private String practicalKnowledge;

	    private String communication;

	    private String interestInPosition;

	    private String potential;

	    private String theoreticalKnowledge;

	    private String englishInterviewValidation;

	    private String relocate;
	    
	    // Constructors, getters, and setters
	}


