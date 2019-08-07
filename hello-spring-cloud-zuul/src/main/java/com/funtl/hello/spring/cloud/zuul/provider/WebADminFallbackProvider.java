package com.funtl.hello.spring.cloud.zuul.provider;

import com.ctc.wstx.io.CharsetNames;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebADminFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        return "hello-spring-cloud-web-admin-feign";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                ObjectMapper objectMapper =new ObjectMapper();
                Map<String,Object> map =new HashMap<>();
                map.put("ststus",200);
                map.put("message","无法连接,请检查您的网络");
                return new ByteArrayInputStream(objectMapper.writeValueAsString(map).getBytes("UTF-8"));

            }

            @Override
            public HttpHeaders getHeaders(){
                HttpHeaders header =new HttpHeaders();
                header.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return header;
            }
        };
    }
}
