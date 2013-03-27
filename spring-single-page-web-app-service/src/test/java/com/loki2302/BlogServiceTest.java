package com.loki2302;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.loki2302.dto.AuthenticationResultDTO;
import com.loki2302.dto.BlogServiceErrorCode;
import com.loki2302.dto.PostDTO;
import com.loki2302.dto.ServiceResult;
import com.loki2302.dto.UserDTO;
import com.loki2302.service.BlogServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class BlogServiceTest {

	@Autowired
	BlogServiceImpl blogService;
	
	@Test
	public void sessionExpiresAfter3Seconds() throws InterruptedException {
		createUser("loki2302", "qwerty");
		String sessionToken =  authenticate("loki2302", "qwerty");		
		
		createPost(sessionToken, "test1");
		Thread.sleep(3500);		
		
		ServiceResult<PostDTO> createPostResult = blogService.createPost(
				sessionToken, 
				"test2");
		assertFalse(createPostResult.ok);
		assertNull(createPostResult.payload);		
		assertEquals(
				BlogServiceErrorCode.SessionExpired, 
				createPostResult.blogServiceErrorCode);
		
		createPostResult = blogService.createPost(
				sessionToken, 
				"test3");
		assertFalse(createPostResult.ok);
		assertNull(createPostResult.payload);		
		assertEquals(
				BlogServiceErrorCode.NoSuchSession, 
				createPostResult.blogServiceErrorCode);
	}
		
	@Test
	public void canCreatePost() {
		UserDTO user = createUser("loki2302", "qwerty");
		String sessionToken =  authenticate("loki2302", "qwerty");
		
		PostDTO post = createPost(
				sessionToken, 
				"text goes here");
		assertTrue(post.PostId > 0);
		assertEquals("text goes here", post.Text);
		assertEquals("loki2302", post.UserName);
		assertEquals(user.UserId, post.UserId);
	}
	
	private String getLongPostText() {
	    StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 1025; ++i) {
            stringBuilder.append('a');
        }
        return stringBuilder.toString();
	}
	
	@Test
	public void cantCreatePostIfTextIsTooLong() {
		createUser("loki2302", "qwerty");
		String sessionToken =  authenticate("loki2302", "qwerty");
		
		ServiceResult<PostDTO> createPostResult = blogService.createPost(
				sessionToken, 
				getLongPostText());
		assertFalse(createPostResult.ok);
		assertNull(createPostResult.payload);		
		assertEquals(
				BlogServiceErrorCode.ValidationError, 
				createPostResult.blogServiceErrorCode);
		assertTrue(createPostResult.fieldErrors.containsKey("text"));
	}
	
	@Test
	public void canUpdatePost() {
		createUser("loki2302", "qwerty");
		String sessionToken = authenticate("loki2302", "qwerty");
		
		PostDTO post = createPost(sessionToken, "text goes here");
		PostDTO updatedPost = updatePost(
				sessionToken, 
				post.PostId, 
				"new text goes here");
		assertEquals(post.PostId, updatedPost.PostId);
		assertEquals(post.UserId, updatedPost.UserId);
		assertEquals(post.UserName, updatedPost.UserName);
		assertEquals("new text goes here", updatedPost.Text);
	}
	
	@Test
	public void cantUpdatePostThatDoesNotExist() {
		createUser("loki2302", "qwerty");
		String sessionToken = authenticate("loki2302", "qwerty");
        
        ServiceResult<PostDTO> updatePostResult = blogService.updatePost(
                sessionToken, 
                123, 
                "new text goes here");
        assertFalse(updatePostResult.ok);
        assertEquals(BlogServiceErrorCode.NoSuchPost, updatePostResult.blogServiceErrorCode);
	}
	
	@Test
	public void cantUpdatePostThatDoesNotBelongToTheUser() {
	    createUser("loki2302", "qwerty");                
        String sessionToken1 = authenticate("loki2302", "qwerty");        
        
        PostDTO post = createPost(sessionToken1, "test post");
        long postId = post.PostId;
        
        createUser("qwerty", "qwerty");
        String sessionToken2 = authenticate("qwerty", "qwerty");
        ServiceResult<PostDTO> updatePostResult = blogService.updatePost(
                sessionToken2,
                postId,
                "new text");
        assertFalse(updatePostResult.ok);
        assertEquals(BlogServiceErrorCode.NoPermissionsToAccessPost, updatePostResult.blogServiceErrorCode);
	}	
	
	@Test
	public void cantUpdatePostIfTextIsTooLong() {
	    createUser("loki2302", "qwerty");
        String sessionToken = authenticate("loki2302", "qwerty");
        
        PostDTO post = createPost(sessionToken, "text goes here");
        long postId = post.PostId;
        
        ServiceResult<PostDTO> updatePostResult = blogService.updatePost(
                sessionToken, 
                postId, 
                getLongPostText());
        assertFalse(updatePostResult.ok);
        assertNull(updatePostResult.payload);       
        assertEquals(
                BlogServiceErrorCode.ValidationError, 
                updatePostResult.blogServiceErrorCode);
        assertTrue(updatePostResult.fieldErrors.containsKey("text"));
	}
	
	@Test
	public void canGetPost() {
		createUser("loki2302", "qwerty");
		String sessionToken = authenticate("loki2302", "qwerty");
		
		PostDTO post = createPost(sessionToken, "text goes here");
		PostDTO retrievedPost = getPost(
				sessionToken, 
				post.PostId);
		assertEquals(post.PostId, retrievedPost.PostId);
		assertEquals(post.Text, retrievedPost.Text);
		assertEquals(post.UserId, retrievedPost.UserId);
		assertEquals(post.UserName, retrievedPost.UserName);
	}
	
	@Test
	public void canGetPosts() {
		createUser("loki2302", "qwerty");
		createUser("qwerty", "qwerty");
		
		String sessionToken1 = authenticate("loki2302", "qwerty");
		String sessionToken2 = authenticate("qwerty", "qwerty");
		
		ServiceResult<List<PostDTO>> getPostsServiceResult = blogService.getPosts(sessionToken1);
		assertTrue(getPostsServiceResult.ok);
		assertEquals(0, getPostsServiceResult.payload.size());
		
		createPost(sessionToken1, "hi there");
		getPostsServiceResult = blogService.getPosts(sessionToken1);
		assertTrue(getPostsServiceResult.ok);
		assertEquals(1, getPostsServiceResult.payload.size());
		
		createPost(sessionToken2, "aaa");
		
		getPostsServiceResult = blogService.getPosts(sessionToken1);
		assertTrue(getPostsServiceResult.ok);
		assertEquals(2, getPostsServiceResult.payload.size());
		
		getPostsServiceResult = blogService.getPosts(sessionToken2);
		assertTrue(getPostsServiceResult.ok);
		assertEquals(2, getPostsServiceResult.payload.size());
	}
	
	@Test
	public void canDeletePost() {
	    createUser("loki2302", "qwerty");
        String sessionToken = authenticate("loki2302", "qwerty");
        
        PostDTO post = createPost(sessionToken, "test post");
        
        List<PostDTO> posts = getPosts(sessionToken);       
        assertEquals(1, posts.size());
        
        ServiceResult<Object> deletePostResult = blogService.deletePost(sessionToken, post.PostId);
        assertTrue(deletePostResult.ok);
        
        posts = getPosts(sessionToken);       
        assertEquals(0, posts.size());
	}
	
	@Test
	public void cantDeletePostThatDoesNotExist() {
		// TODO
	}
	
	@Test
	public void cantDeletePostThatDoesnBelongToTheUser() {
		// TODO
	}
	
	private UserDTO createUser(
			String userName, 
			String password) {
		
		ServiceResult<UserDTO> result = blogService.createUser(
				userName, 
				password);
		
		assertTrue(result.ok);
		assertNotNull(result.payload);
		
		return result.payload;
	}
	
	private String authenticate(
			String userName, 
			String password) {
		
		ServiceResult<AuthenticationResultDTO> result = blogService.authenticate(
				userName, 
				password);
		
		assertTrue(result.ok);
		assertNotNull(result.payload);
		
		return result.payload.SessionToken;
	}
	
	private PostDTO createPost(
			String sessionToken, 
			String text) {
		
		ServiceResult<PostDTO> result = blogService.createPost(
				sessionToken, 
				text);
		
		assertTrue(result.ok);
		assertNotNull(result.payload);
		
		return result.payload;		
	}
	
	private PostDTO getPost(
			String sessionToken, 
			long postId) {
		
		ServiceResult<PostDTO> result = blogService.getPost(
				sessionToken, 
				postId);
		
		assertTrue(result.ok);
		assertNotNull(result.payload);
		
		return result.payload;
	}
	
	private List<PostDTO> getPosts(String sessionToken) {
	    ServiceResult<List<PostDTO>> result = 
	            blogService.getPosts(sessionToken);
	    
	    assertTrue(result.ok);
	    assertNotNull(result.payload);
	    
	    return result.payload;
	}
	
	private PostDTO updatePost(
			String sessionToken, 
			long postId, 
			String text) {
		
	    ServiceResult<PostDTO> result = blogService.updatePost(
                sessionToken,
                postId,
                text);
        
        assertTrue(result.ok);
        assertNotNull(result.payload);
        
        return result.payload;
	}
	
	private void deletePost(
			String sessionToken, 
			long postId) {
		
		ServiceResult<Object> result = blogService.deletePost(
				sessionToken,
				postId);
		
		assertTrue(result.ok);
	}	
}
