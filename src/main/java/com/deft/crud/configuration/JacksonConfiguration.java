package com.deft.crud.configuration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class JacksonConfiguration {
	
	private static final String dateFormat = "yyyy-MM-dd";
	private static final String timeFormat = "HH:mm:ss";
	private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
	
	@Bean
	public ObjectMapper serializingObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
		javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer());
		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
		javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
		javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
		javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
		
		objectMapper.registerModule(javaTimeModule);
		
		return objectMapper;
	}
	
	public class LocalDateSerializer extends JsonSerializer<LocalDate> {
		
		@Override
		public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
				throws IOException {
			jsonGenerator.writeString(DateTimeFormatter.ofPattern(dateFormat).format(localDate));
		}
	}
	
	public class LocalTimeSerializer extends JsonSerializer<LocalTime> {
			
			@Override
			public void serialize(LocalTime localTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
					throws IOException {
				jsonGenerator.writeString(DateTimeFormatter.ofPattern(timeFormat).format(localTime));
			}
		}
	
	public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
		
		@Override
		public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
				throws IOException {
			jsonGenerator.writeString(DateTimeFormatter.ofPattern(dateTimeFormat).format(localDateTime));
		}
	}
	
	public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
		
		@Override
		public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
				throws IOException, JsonProcessingException {
			return LocalDate.parse(jsonParser.getValueAsString(), DateTimeFormatter.ofPattern(dateFormat));
		}
	}
	
	public class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {
		
		@Override
		public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
				throws IOException, JsonProcessingException {
			return LocalTime.parse(jsonParser.getValueAsString(), DateTimeFormatter.ofPattern(timeFormat));
		}
	}
	
	public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
		
		@Override
		public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
				throws IOException, JsonProcessingException {
			return LocalDateTime.parse(jsonParser.getValueAsString(), DateTimeFormatter.ofPattern(dateTimeFormat));
		}
	}
}
