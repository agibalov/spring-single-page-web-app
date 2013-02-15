package com.loki2302.service.validation.subjects;

import com.loki2302.service.validation.PostText;

public class PostSubject {
	public final static String TEXT_FIELD = "text";
	
	@PostText
	public String text;
}