package com.pes.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pes.model.request.Feedback;
import com.pes.repository.FeedbackRepo;

@Service
public class FeedbackServiceImpl implements IFeedbackService {

	@Autowired
	private FeedbackRepo feedbackRepo;

	@Override
	public List<Feedback> getAllFeedback() {
		List<Feedback> findAll = feedbackRepo.findAll();
		return findAll;
	}

	@Override
	public String saveFeedback(Feedback feedbackData) {
		Feedback save = feedbackRepo.save(feedbackData);
		return "Succussfully saved feedback from " +save.getInterviewerName() +" for candidate name "+save.getCandidateName() ;
	}


}
