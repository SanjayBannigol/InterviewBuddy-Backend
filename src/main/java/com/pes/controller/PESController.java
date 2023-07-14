package com.pes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pes.helper.CompletionRequest;
import com.pes.model.request.Feedback;
import com.pes.model.request.FormData;
import com.pes.model.response.ChatGptResponse;
import com.pes.service.FeedbackServiceImpl;
import com.pes.service.OpenAiApiClient;
import com.pes.service.OpenAiApiClient.OpenAiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pes")
@RequiredArgsConstructor
public class PESController {

	String response;

	// used for converting Java objects to JSON
	@Autowired
	private ObjectMapper jsonMapper; 

	@Autowired
	private OpenAiApiClient client;
	
	@Autowired
	private FeedbackServiceImpl feedbackService;

	@CrossOrigin("http://localhost:4200")
	@PostMapping("/response")
	public ResponseEntity<String> chat(@RequestBody FormData formData) {
		// Construct the request message using @RequestParam values
		try {
			String message = "Generate " + formData.getNumOfQuestions() + " " + formData.getTechnology()
					+ " interview question and answer for " + formData.getProficiency() + " with difficulty level of "
					+ formData.getDifficultyLevel() + " with comma separated answers.";
			System.out.println("-----------------------------------------------------------------------------------------");
			System.out.println(message);
			response = chatWithGpt3(message);
			System.out.println(response);
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Please check internet connectivity!!!",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@CrossOrigin("http://localhost:4200")
	@PostMapping("/feedback")
	public ResponseEntity<?> saveFeedback(@RequestBody Feedback feedbackData) {
		// Construct the request message using @RequestParam values
		try {
				String saveFeedback = feedbackService.saveFeedback(feedbackData);
			return new ResponseEntity<String>(saveFeedback, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Please check internet connectivity!!!",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/showAllFeedback")
	public ResponseEntity<?> showAllFeedback() {
		// Construct the request message using @RequestParam values
		try {
				 List<Feedback> allFeedback = feedbackService.getAllFeedback();
			return new ResponseEntity<List<Feedback>>(allFeedback, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Please check internet connectivity!!!",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin("http://localhost:4200")
    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            Optional<Feedback> byId = feedbackService.fetechById(id);
            if (byId.isPresent()) {
                System.out.println(byId.get().getId());
                return new ResponseEntity<Optional<Feedback>>(byId, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Id not fount", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	

	private String chatWithGpt3(String message) throws Exception {
		// encapsulates the parameters or data required to make a completion request to
		// an API or service
		// And passing the message=question to custom method
		var completion = CompletionRequest.defaultWith(message);

		// object into a JSON string representation.
		var postBodyJson = jsonMapper.writeValueAsString(completion);

		// making a POST request to the OpenAI API using a client object.
		var responseBody = client.postToOpenAiApi(postBodyJson, OpenAiService.GPT_3);

		// Deserializing the response body received from the OpenAI API into a Java
		// object of type ChatGptResponse.
		var completionResponse = jsonMapper.readValue(responseBody, ChatGptResponse.class);

		// returning the first answer from the completionResponse object as a String
		return completionResponse.firstAnswer().orElseThrow();
	}

}
