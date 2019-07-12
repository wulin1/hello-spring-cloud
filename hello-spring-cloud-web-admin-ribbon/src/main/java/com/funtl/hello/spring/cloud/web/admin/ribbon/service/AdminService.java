package com.funtl.hello.spring.cloud.web.admin.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminService {
    @Autowired
    private RestTemplate restTemplate;
  @HystrixCommand(fallbackMethod = "hiError")
    public  String  sayHi(String message){
        return restTemplate.getForObject("http://hello-spring-cloud-service-damin/hi?message" + message,String.class);
    }
    public String hiError(String message){

        return String.format("Hi your message is : %s but request bad"+ message,String.class);
    }
}
