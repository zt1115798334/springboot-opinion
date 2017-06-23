package com.zt.opinion;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class BaseTests {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private WebApplicationContext context;

	protected MockMvc mockMvc;

	@Before
	public void setupMockMvc() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	public ResultActions perform(String url, Object params){
		try {
			MockHttpServletRequestBuilder builder = post(url)
					.contentType(MediaType.APPLICATION_JSON_UTF8);
			if(params instanceof String){
				builder.content(params.toString());
			}else{
				ObjectMapper mapper = new ObjectMapper();
				builder.content(mapper.writeValueAsString(params));
			}
			return mockMvc.perform(builder).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * <p>Description: 执行controller请求并返回congtroller的执行结果，
	 * 适用于controller的方法中只包含一个参数的情况</p>
	 * @param url
	 * @param params
	 * @return
	 * @author wjc
	 * @date 2017年2月6日
	 */
	protected String performAndGetResponse(String url, Object params){
		String result = null;
		try {
			MockHttpServletRequestBuilder builder = post(url)
					.contentType(MediaType.APPLICATION_JSON_UTF8);
			if(params instanceof String){
				builder.content(params.toString());
			}else{
				ObjectMapper mapper = new ObjectMapper();
				builder.content(mapper.writeValueAsString(params));
			}
			result = mockMvc.perform(builder)
					.andReturn().getResponse().getContentAsString();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 
	 * <p>Description: 执行controller请求并返回congtroller的执行结果，
	 * 适用于controller的方法中包含多个参数的情况</p>
	 * @param url
	 * @param params
	 * @return
	 * @author wjc
	 * @date 2017年2月6日
	 */
	protected String performAndGetResponse(String url, Map<String, Object> params){
		String result = null;
		try {
			MockHttpServletRequestBuilder builder = post(url)
					.contentType(MediaType.APPLICATION_JSON_UTF8);
			if(params != null){
				for(Map.Entry<String, Object> entry : params.entrySet()){
					builder.param(entry.getKey(), entry.getValue()+"");
				}
			}
			result = mockMvc.perform(builder)
					.andReturn().getResponse().getContentAsString();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
