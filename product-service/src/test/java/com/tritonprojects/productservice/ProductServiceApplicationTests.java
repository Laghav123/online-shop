package com.tritonprojects.productservice;

import com.tritonprojects.productservice.dto.ProductRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.7");
	// Plain "new MongoDBContainer()" is depreciated and we should include mongo version as parameter

	@Autowired
	private MockMvc mockMvc; // this is a tool from spring framework
							 // This can create environment and make request
							 // to server and matches response with expected result
	@Autowired
	private ObjectMapper objectMapper;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

//	@Test
//	void shouldCreateProduct() throws Exception {
//		ProductRequestDTO dto = getProductRequest();
//		String productRequestString = objectMapper.writeValueAsString(dto);
//
//		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(productRequestString)
//		).andExpect(status().isCreated());
//	}

	private ProductRequestDTO getProductRequest() {
		return ProductRequestDTO.builder()
				.name("iphone 15")
				.description(null)
				.price(BigDecimal.valueOf(150000))
				.build();
	}

}
