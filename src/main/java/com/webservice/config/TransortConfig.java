package com.webservice.config;

import com.webservice.transport.Transporter;
import com.webservice.transport.TransporterImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yukai on 2016/10/28.
 */
@Configuration
public class TransortConfig {

    @Value(value = "${other.server.rest.url}")
    String url;

    @Bean
    public Transporter transporter(){
        return new TransporterImpl(url);
    }


}
