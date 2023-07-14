package com.pes.service;

import java.util.List;
import java.util.Optional;

import com.pes.model.request.Feedback;


public interface IFeedbackService {
	
	public List<Feedback> getAllFeedback();
	
	public String saveFeedback(Feedback feedbackData);
	
	public Optional<Feedback> fetechById(int id);

}
