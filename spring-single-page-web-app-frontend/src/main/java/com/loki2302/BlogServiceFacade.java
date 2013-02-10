package com.loki2302;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.loki2302.dto.AuthenticationResultDTO;
import com.loki2302.dto.PostDTO;
import com.loki2302.dto.ServiceResult;
import com.loki2302.dto.UserDTO;

@Path("BlogService")
@Produces("application/json")
@Consumes("application/json")
public interface BlogServiceFacade {
	@POST
	@Path("createUser")
	ServiceResult<UserDTO> createUser(
			@QueryParam("userName") String userName, 
			@QueryParam("password") String password);

	@POST
	@Path("authenticate")
	ServiceResult<AuthenticationResultDTO> authenticate(
			@QueryParam("userName") String userName,
			@QueryParam("password") String password);

	@POST
	@Path("createPost")
	ServiceResult<PostDTO> createPost(
			@QueryParam("sessionToken") String sessionToken, 
			@QueryParam("text") String text);

	@GET
	@Path("getPost")
	ServiceResult<PostDTO> getPost(
			@QueryParam("sessionToken") String sessionToken, 
			@QueryParam("postId") long postId);

	@POST
	@Path("deletePost")
	ServiceResult<Object> deletePost(
			@QueryParam("sessionToken") String sessionToken, 
			@QueryParam("postId") long postId);

	@POST
	@Path("updatePost")
	ServiceResult<PostDTO> updatePost(
			@QueryParam("sessionToken") String sessionToken, 
			@QueryParam("postId") long postId,
			@QueryParam("text") String text);
}