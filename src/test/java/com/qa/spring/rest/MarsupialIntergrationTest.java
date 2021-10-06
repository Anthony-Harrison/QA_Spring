package com.qa.spring.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.spring.data.Marsupial;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up the mockMVC object
@Sql(scripts = { "classpath:marsupial-schema.sql",
		"classpath:marsupial-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class MarsupialIntergrationTest {

	@Autowired // inject the mockMVC object into this class
	private MockMvc mvc; // object for sending mock http requests

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		// Test data
		final Marsupial testMarsupial = new Marsupial(null, "Anth", "Koala", "Green");
		String testMarsupialAsJSON = this.mapper.writeValueAsString(testMarsupial);
		// Expected data
		final Marsupial savedMarsupial = new Marsupial(2, "Anth", "Koala", "Green");
		String savedMarsupialAsJSON = this.mapper.writeValueAsString(savedMarsupial);
		// method, path, head, body
		RequestBuilder req = post("/createMarsupial").contentType(MediaType.APPLICATION_JSON)
				.content(testMarsupialAsJSON);

		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkContent = content().json(savedMarsupialAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkContent);

	}

//	@Test // More concise way to write the above.
//	void testCreateAbridged() throws Exception {
//		final String testMarsupialAsJSON = this.mapper
//				.writeValueAsString(new Marsupial(null, "Anth", "Koala", "Green"));
//		final String savedMarsupialAsJSON = this.mapper.writeValueAsString(new Marsupial(2, "Anth", "Koala", "Green"));
//
//		this.mvc.perform(post("/createMarsupial").contentType(MediaType.APPLICATION_JSON).content(testMarsupialAsJSON))
//				.andExpect(status().isCreated()).andExpect(content().json(savedMarsupialAsJSON));
//	}

	@Test
	void testGetAllMarsupials() throws Exception {
		final Marsupial savedMarsupial = new Marsupial(1, "Wally", "Wallabee", "grey");

		List<Marsupial> marsupialList = new ArrayList<>();
		marsupialList.add(savedMarsupial);
		String savedMarsupialList = this.mapper.writeValueAsString(marsupialList);

		RequestBuilder request = get("/getAllMarsupials").contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedMarsupialList);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testGetById() throws Exception {
		final Marsupial savedMarsupial = new Marsupial(1, "Wally", "Wallabee", "grey");
		String savedMarsupialAsJSON = this.mapper.writeValueAsString(savedMarsupial);

		RequestBuilder request = get("/getMarsupial/" + savedMarsupial.getId());

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedMarsupialAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testUpdate() throws Exception {
		final Marsupial updatedMarsupial = new Marsupial(1, "Anth", "Wallabee", "grey");
		String updatedMarsupialAsJSON = this.mapper.writeValueAsString(updatedMarsupial);

		RequestBuilder updateRequest = put("/updateMarsupial/" + 1).contentType(MediaType.APPLICATION_JSON)
				.content(updatedMarsupialAsJSON);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkContent = content().json(updatedMarsupialAsJSON);

		this.mvc.perform(updateRequest).andExpect(checkStatus).andExpect(checkContent);

	}

	@Test
	void testDelete() throws Exception {

		RequestBuilder deleteRequest = delete("/removeMarsupial/" + 1);
		ResultMatcher checkStatus = status().isNoContent();

		this.mvc.perform(deleteRequest).andExpect(checkStatus);
	}

}
