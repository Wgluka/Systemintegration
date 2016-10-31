package com.webservice.config;

import com.webservice.endpoint.ScoresEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.endpoint.mapping.PayloadRootQNameEndpointMapping;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yukai on 2016/10/27.
 */
@EnableWs
@Configuration
public class EndpointConfig extends WsConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(EndpointConfig.class);

    @Bean
    public ServletRegistrationBean messageDispatcher(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean(servlet, "/ScoreService/*");
    }

//    @Bean
//    public DispatcherServlet dispatcherServlet(DispatcherServlet dispatcherServlet){
//        dispatcherServlet.set
//    }

    @Bean(name = "scores")
    public SimpleWsdl11Definition definition(){
        return new SimpleWsdl11Definition(new ClassPathResource("scores.wsdl"));
    }

//    @Bean
    public PayloadRootQNameEndpointMapping payloadRootQNameEndpointMapping(ScoresEndpoint scoresEndpoint){
        PayloadRootQNameEndpointMapping mapping = new PayloadRootQNameEndpointMapping();
        Map map = new HashMap<String, ScoresEndpoint>();
        map.put("{http://www.wgluka.com.cn/2048/}ScoreRequest", scoresEndpoint);
        mapping.setEndpointMap(map);

        if( scoresEndpoint != null ){
            logger.info("it is not null");
        } else {
            logger.info("it is null ing");
        }
        return mapping;
    }
}
