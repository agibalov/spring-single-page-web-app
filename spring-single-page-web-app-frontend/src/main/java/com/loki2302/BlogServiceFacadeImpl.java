package com.loki2302;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loki2302.dto.AuthenticationResultDTO;
import com.loki2302.dto.PostDTO;
import com.loki2302.dto.ServiceResult;
import com.loki2302.dto.UserDTO;
import com.loki2302.service.BlogService;

@Service("blogServiceFacade")
public class BlogServiceFacadeImpl implements BlogServiceFacade {
	
	@Autowired
	BlogService blogService;
	
	public ServiceResult<UserDTO> createUser(
			String userName,
			String password) {
		return blogService.createUser(userName, password);
	}

	public ServiceResult<AuthenticationResultDTO> authenticate(
			String userName,
			String password) {
		return blogService.authenticate(userName, password);
	}
	
	public ServiceResult<PostDTO> createPost(
			String sessionToken,
			String text) {
		return blogService.createPost(sessionToken, text);
	}

	public ServiceResult<PostDTO> getPost(
			String sessionToken,
			long postId) {
		return blogService.getPost(sessionToken, postId);
	}

	public ServiceResult<Object> deletePost(
			String sessionToken,
			long postId) {
		return blogService.deletePost(sessionToken, postId);
	}

	public ServiceResult<PostDTO> updatePost(
			String sessionToken,
			long postId, 
			String text) {
		return blogService.updatePost(sessionToken, postId, text);
	}
}
