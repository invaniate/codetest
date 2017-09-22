package com.pierceecom.blog;

import com.pierceecom.blog.dto.PostEntity;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JerseyBlogIntegrationTest {

	private static final String POST_1 = "{\"id\":1,\"content\":\"content\",\"title\":\"title\"}";
	private static final String POST_2 = "{\"id\":2,\"content\":\"content2\",\"title\":\"title2\"}";
	private static final String APP_PATH = "http://localhost:8080/blog-web/";
	private static final String POST_PATH = "posts/";

	private Client client;

	@Before
	public void init(){
		this.client = ClientBuilder.newClient();
	}

	@Test
	public void test_1_BlogWithoutPosts() {
		Response r = get();
		assertEquals("[]", r.readEntity(String.class));
	}

	@Test
	public void test_2_AddPosts() {
		createAndCheckStatusAndLocation(1L, 201, new PostEntity("title", "content"));
		createAndCheckStatusAndLocation(2L, 201, new PostEntity("title2", "content2"));
	}

    @Test
    public void test_3_GetPost() {
		getByIdAndCheckBody(1L, POST_1);
		getByIdAndCheckBody(2L, POST_2);
    }

	@Test
	public void test_4_TryToGetInexistentPost() {
		getByIdAndCheckStatus(3L, 204);
	}

    @Test
    public void test_5_GetAllPosts() {
		Response r = get();
        assertEquals("[" + POST_1 + "," + POST_2 + "]", r.readEntity(String.class));
    }

    @Test
    public void test_6_DeletePosts() {
		deleteByIdAndCheckStatus(1L, 200);
		deleteByIdAndCheckStatus(2L, 200);
    }

	@Test
	public void test_7_TryToDeleteDeletedPosts() {
		deleteByIdAndCheckStatus(1L, 404);
		deleteByIdAndCheckStatus(2L, 404);
	}

    @Test
    public void test_8_GetAllPostsShouldNowBeEmpty() {
		Response r = get();
		assertEquals("[]", r.readEntity(String.class));
    }

    /* short methods */
	private void getByIdAndCheckStatus(Long id, long statusCode){
		Response response = get(id);
		assertEquals(statusCode, response.getStatus());
	}

	private void getByIdAndCheckBody(Long id, String body){
		Response response = get(id);
		assertEquals(body, response.readEntity(String.class));
	}

	private void createAndCheckStatusAndLocation(Long id, long statusCode, PostEntity entity){
		Response response = post(entity);
		assertEquals(APP_PATH + POST_PATH + id, response.getHeaderString("location"));
		assertEquals(statusCode, response.getStatus());
	}

	private void deleteByIdAndCheckStatus(Long id, long statusCode){
		Response response = delete(id);
		assertEquals(statusCode, response.getStatus());
	}

	/* core methods */
	private Response get(){
		return getClient(POST_PATH).get();
	}

	private Response get(Long id){
		return getClient(POST_PATH + id).get();
	}

	private Response post(PostEntity postEntity){
		return getClient(POST_PATH).post(Entity.entity(postEntity, MediaType.APPLICATION_JSON));
	}

	private Response delete(Long id){
		return getClient(POST_PATH + id).delete();
	}

	private Invocation.Builder getClient(String path){
		return client
			.target(APP_PATH)
			.path(path)
			.request(MediaType.APPLICATION_JSON);
	}

}
