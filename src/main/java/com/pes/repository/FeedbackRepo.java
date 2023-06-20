package com.pes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pes.model.request.Feedback;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer>{

}
