package com.loki2302.service.transactionscripts;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loki2302.dto.PostDTO;
import com.loki2302.entities.Post;
import com.loki2302.entities.User;
import com.loki2302.repositories.PostRepository;
import com.loki2302.service.implementation.AuthenticationManager;
import com.loki2302.service.implementation.BlogServiceException;
import com.loki2302.service.implementation.PostMapper;

@Service
public class GetPostsTransactionScript {
	@Autowired AuthenticationManager authenticationManager;	
	@Autowired PostRepository postRepository;	
	@Autowired PostMapper postMapper;		
	@PersistenceContext EntityManager entityManager;
	
	public List<PostDTO> getPosts(
			String sessionToken) throws BlogServiceException {
		
		User user = authenticationManager.getUser(sessionToken);
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PostAndAuthor> criteriaQuery = 
				criteriaBuilder.createQuery(PostAndAuthor.class);
		Root<Post> root = criteriaQuery.from(Post.class);
		Join<Post, User> userExpression = root.join("author");
		criteriaQuery.multiselect(root, userExpression);
				
		TypedQuery<PostAndAuthor> typedQuery = entityManager.createQuery(criteriaQuery);
		List<PostAndAuthor> resultList = typedQuery.getResultList();
		
		List<PostDTO> postDtos = new ArrayList<PostDTO>();
		for(PostAndAuthor postAndUser : resultList) {
			PostDTO postDto = postMapper.build(
					postAndUser.post, 
					postAndUser.author);
			postDtos.add(postDto);
		}
		
		return postDtos;
	}
	
	public static class PostAndAuthor {
		public Post post;
		public User author;
		
		public PostAndAuthor(Post post, User author) {
			this.post = post;
			this.author = author;
		}
	}
}