package com.loki2302.service.implementation;

import org.springframework.stereotype.Service;

import com.loki2302.dto.PostDTO;
import com.loki2302.entities.Post;
import com.loki2302.entities.User;

@Service
public class PostMapper {
	public PostDTO build(Post post) {
		return build(post, post.getAuthor());
	}
	
	public PostDTO build(Post post, User author) {
		PostDTO postDto = new PostDTO();
		postDto.PostId = post.getId();
		postDto.Text = post.getText();		
		postDto.UserId = author.getId();
		postDto.UserName = author.getUserName();
		
		return postDto;
	}
}