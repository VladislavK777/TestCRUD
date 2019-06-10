package com.vladislavk.testcrud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vladislavk.testcrud.configuration.AppConfig;
import com.vladislavk.testcrud.configuration.jwt.JWTTokenAuthService;
import com.vladislavk.testcrud.entity.Product;
import io.jsonwebtoken.Jwts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.vladislavk.testcrud.model.ConstantsToken.*;
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

	@Autowired
	private JWTTokenAuthService jwtTokenAuthService;

	private String jsonBody = null;
	private String authToken = null;

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

		List<GrantedAuthority> grantedAuths = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
		Authentication authentication = new UsernamePasswordAuthenticationToken("admin", "user", grantedAuths);
		authToken = TOKEN_PREFIX + jwtTokenAuthService.addAuthentication(authentication);
	}

	@Test
	public void getProductsTest() {
		try {
			mvc.perform(get("/api/v1/all")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.header(HEADER_AUTH, authToken))
					.andExpect(status().isOk())
					.andExpect(content()
					.contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(jsonPath("$[0].name", is("T-shit")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void postCreateProductTest() {
		try {
			mvc.perform(post("/api/v1/admin/insert")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(jsonBody)
					.header(HEADER_AUTH, authToken))
					.andExpect(status().isCreated());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deleteProductTest() {
		try {
			mvc.perform(delete("/api/v1/admin/delete/1")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.header(HEADER_AUTH, authToken))
					.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void putUpdateProductTest() {
		try {
			mvc.perform(put("/api/v1/admin/update/1")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(jsonBody)
					.header(HEADER_AUTH, authToken))
					.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getTokenTest() {
		Authentication authentication = new UsernamePasswordAuthenticationToken("admin", "user");
		String token = jwtTokenAuthService.addAuthentication(authentication);
		String name = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();
		Assert.assertEquals("admin", name);
	}
}
