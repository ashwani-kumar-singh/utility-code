package com.test.component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class RandomComponent {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Value("${conf.path:classpath:audit_configuration.json}")
  private String auditConfigurationPath;

  @PostConstruct
  private void init() throws IOException {
    File file = ResourceUtils.getFile(auditConfigurationPath);
    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
    String content = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
    JsonNode jsonNode = objectMapper.readTree(content);
    System.out.println("content" + objectMapper.writeValueAsString(jsonNode));
  }

}
