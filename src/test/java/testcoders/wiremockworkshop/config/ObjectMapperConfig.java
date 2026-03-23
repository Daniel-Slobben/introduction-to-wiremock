package testcoders.wiremockworkshop.config;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperConfig {
  public static final ObjectMapper objectMapper = new ObjectMapper();

  static {
    objectMapper.findAndRegisterModules();
  }
}
