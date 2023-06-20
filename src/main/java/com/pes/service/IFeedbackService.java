package com.pes.service;

import java.util.List;

import com.pes.model.request.Feedback;


public interface IFeedbackService {
	
	public List<Feedback> getAllFeedback();
	
	public String saveFeedback(Feedback feedbackData);

}
