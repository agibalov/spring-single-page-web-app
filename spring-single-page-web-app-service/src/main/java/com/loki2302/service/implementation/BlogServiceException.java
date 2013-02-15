package com.loki2302.service.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loki2302.dto.BlogServiceErrorCode;

public class BlogServiceException extends Exception {
	private static final long serialVersionUID = -6064575307294795999L;
	
	private final BlogServiceErrorCode blogServiceErrorCode;
	private final Map<String, List<String>> fieldErrors;
	
	public BlogServiceException(
			BlogServiceErrorCode blogServiceErrorCode,
			Map<String, List<String>> fieldErrors) {
		this.blogServiceErrorCode = blogServiceErrorCode;
		this.fieldErrors = fieldErrors;
	}
	
	public BlogServiceException(BlogServiceErrorCode blogServiceErrorCode) {
		this(blogServiceErrorCode, new HashMap<String, List<String>>());
	}
	
	public BlogServiceException(
			BlogServiceErrorCode blogServiceErrorCode, 
			String fieldName, 
			String fieldError) {
		this(blogServiceErrorCode, new HashMap<String, List<String>>());
		fieldErrors.put(fieldName, new ArrayList<String>());
		fieldErrors.get(fieldName).add(fieldError);
	}
	
	public BlogServiceErrorCode getBlogServiceErrorCode() {
		return blogServiceErrorCode;
	}
	
	public Map<String, List<String>> getFieldErrors() {
		return fieldErrors;
	}
}