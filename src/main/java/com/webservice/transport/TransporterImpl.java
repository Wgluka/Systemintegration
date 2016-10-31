package com.webservice.transport;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webservice.model.input.Message;
import com.webservice.model.input.OtherDTO;
import com.webservice.model.input.ScoreDTO;
import com.webservice.model.output.ScoreInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yukai on 2016/10/28.
 */
//@Service
public class TransporterImpl implements Transporter {

    private static final Logger logger = LoggerFactory.getLogger(TransporterImpl.class);

    private RestTemplate template;

    private String url;

    public TransporterImpl(){
    }

    public TransporterImpl(String url){
        template = new RestTemplate();
        this.url = url;
        logger.info(this.url);
//        init();
    }

    public void init(){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        if (template == null ) {
            logger.info("template is null ");
            return ;
        }

//        HttpEntity requestEntity = new HttpEntity(headers);
//        template.setMessageConverters(new MappingJackson2HttpMessageConverter());

//        ResponseEntity<String> response = template.getForEntity(this.url, String.class);
//        Message message = template.getForObject(this.url, Message.class);
//        logger.info(message.toString());

//        Message message = JSON.parseObject(response.getBody(), Message.class);

//        logger.info(response.toString());
//        logger.info(response.getBody());

//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setObjectMapper(new ObjectMapper());
//        logger.info(response.);
    }

    public List<ScoreInfo> getData(){
        ResponseEntity<String> response = template.getForEntity(this.url, String.class);
        Message message = JSON.parseObject(response.getBody(), Message.class);

        return convertToScoreDTOs(message.getData());
    }

    private List<ScoreInfo> convertToScoreDTOs(List<OtherDTO> otherDTOs){
        List<ScoreInfo> scoreInfos = new ArrayList<ScoreInfo>();
        for(OtherDTO otherDTO : otherDTOs){
            scoreInfos.add(convertToScoreDTO(otherDTO));
        }
        return scoreInfos;
    }

    private ScoreInfo convertToScoreDTO(OtherDTO otherDTO){
        ScoreInfo scoreInfo = new ScoreInfo();
        scoreInfo.setScore(otherDTO.getScore());
        scoreInfo.setName(otherDTO.getUsername());
        return scoreInfo;
    }

}
