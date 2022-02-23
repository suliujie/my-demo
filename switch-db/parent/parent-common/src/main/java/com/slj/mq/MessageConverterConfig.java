//package com.slj.mq;
//
//import java.util.List;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class MessageConverterConfig implements WebMvcConfigurer {
//
//  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//    Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.xml();
//    builder.indentOutput(true);
//    converters.add(new MappingJackson2XmlHttpMessageConverter(builder.build()));
//  }
//}