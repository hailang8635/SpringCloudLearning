package com.forezp.servicehi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
public class ServiceHiApplication {

    public static void main(String[] args) {
        SpringApplication.run( ServiceHiApplication.class, args );
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    String port;

    @Value("${server.port}")
    private String ip;


    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }


    @GetMapping("/client")
    public String client() {
        System.out.println(discoveryClient.description());
        String services = "Services: " + discoveryClient.getServices()+" ip :"+ip;

        System.out.println(services);
        return services;
    }
}



