package com.pes.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
/**
 * model class for chatGPT response with answer
 *
 */
public class Choice implements Serializable {
    private static final long serialVersionUID = 1L;
	private Integer index;
    String text;
    @JsonProperty("finish_reason")
    private String finishReason;
}




