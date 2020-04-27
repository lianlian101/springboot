package com.test.springboot.demo.test;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSONObject;

public class RestTemplateTest {
    
    @Test
    public void get(){
        RestTemplate template = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "测试");
        String url = "http://localhost:8080/common/get";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.queryParams(params).build().encode().toUri();
        ResponseEntity<String> entity = template.getForEntity(uri, String.class);
        if(entity.getStatusCodeValue() == 200){
            String body = entity.getBody();
            System.out.println(body);
        }
    }
    
    @Test
    public void get2(){
        RestTemplate template = new RestTemplate();
        String url = "http://localhost:8080/common/get?name=测试";
        ResponseEntity<String> entity = template.getForEntity(url, String.class);
        if(entity.getStatusCodeValue() == 200){
            String body = entity.getBody();
            System.out.println(body);
        }
    }
    
    @Test
    public void getJson(){
        RestTemplate template = new RestTemplate();
        template.setRequestFactory(new HttpComponentsClientRestfulHttpRequestFactory());
        String param = "{\"id\":\"1\", \"username\":\"测试\"}";
        String url = "http://localhost:8080/common/getJson";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("cookie", "cookies");
        HttpEntity<?> httpEntity = new HttpEntity<>(param, headers);
        ResponseEntity<String> responseEntity = template.exchange(url, HttpMethod.GET, httpEntity, String.class);
        if(responseEntity.getStatusCodeValue() == 200){
            String body = responseEntity.getBody();
            System.out.println(body);
        }
    }
    
    @Test
    public void post(){
        RestTemplate template = new RestTemplate();
        MultiValueMap<String,Object> param = new LinkedMultiValueMap<String,Object>();
        param.add("id", 1);
        param.add("username", "测试");
        String url = "http://localhost:8080/common/post?password=123测试";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String,Object>> httpEntity = new HttpEntity<MultiValueMap<String,Object>>(param, headers);
        ResponseEntity<String> responseEntity = template.postForEntity(url, httpEntity, String.class);
        if(responseEntity.getStatusCodeValue() == 200){
            String body = responseEntity.getBody();
            System.out.println(body);
        }
    }
    
    @Test
    public void postJson(){
        RestTemplate template = new RestTemplate();
        JSONObject jo = new JSONObject();
        jo.put("id", 1);
        jo.put("username", "测试");
        String url = "http://localhost:8080/common/postJson?key=哈哈哈";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("cookie", "cookies");
        HttpEntity<?> httpEntity = new HttpEntity<>(jo, headers);
        ResponseEntity<String> responseEntity = template.postForEntity(url, httpEntity, String.class);
        if(responseEntity.getStatusCodeValue() == 200){
            String body = responseEntity.getBody();
            System.out.println(body);
        }
    }

}

class HttpComponentsClientRestfulHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {
    @Override
    protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
        if (httpMethod == HttpMethod.GET) {
            return new HttpGetRequestWithEntity(uri);
        }
        return super.createHttpUriRequest(httpMethod, uri);
    }
}

class HttpGetRequestWithEntity extends HttpEntityEnclosingRequestBase {
    public HttpGetRequestWithEntity(final URI uri) {
        super.setURI(uri);
    }

    @Override
    public String getMethod() {
        return HttpMethod.GET.name();
    }
}
