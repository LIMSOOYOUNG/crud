package com.deft.crud.configuration;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class DateFormatConfiguration {
	
	private static final String dateFormat = "yyyy-MM-dd";
	private static final String datetimeFormat = "yyyy-MM-dd HH:mm:ss";
	

}
