package com.vladislavk.testcrud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vladislavk.testcrud.configuration.AppConfig;
import com.vladislavk.testcrud.entity.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = AppConfig.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class TestcrudApplicationTests {

	@Autowired
	private MockMvc mvc;

	private String jsonBody = null;

	@Before
	public void setup() {
		Product product = new Product();
		product.setName("Short");
		product.setBrand("Zara");
		product.setPrice(1234);
		product.setQuantity(4);

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();

			jsonBody = objectWriter.writeValueAsString(product);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@WithMockUser("admin")
	@Test
	public void getProductsTest() {
		try {
			mvc.perform(get("/api/v1/all")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk())
					.andExpect(content()
					.contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(jsonPath("$[0].name", is("T-shift")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@WithMockUser("admin")
	@Test
	public void postCreateProductTest() {
		try {
			mvc.perform(post("/api/v1/admin/insert")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(jsonBody))
					.andExpect(status().isCreated());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@WithMockUser("admin")
	@Test
	public void deleteProductTest() {
		try {
			mvc.perform(delete("/api/v1/admin/delete/1")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@WithMockUser("admin")
	@Test
	public void putUpdateProductTest() {
		try {
			mvc.perform(put("/api/v1/admin/update/1")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(jsonBody))
					.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
