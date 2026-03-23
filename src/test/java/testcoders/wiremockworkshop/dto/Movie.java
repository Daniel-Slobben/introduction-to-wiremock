package testcoders.wiremockworkshop.dto;

import static testcoders.wiremockworkshop.config.ObjectMapperConfig.objectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record Movie(UUID id, String title, LocalDate releaseDate, Set<Actor> actors) {

  static void main() throws JsonProcessingException {
    System.out.println(
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(getDefaultMovie()));
  }

  public static Movie getDefaultMovie() {
    Set<Actor> actors =
        Set.of(
            new Actor(UUID.randomUUID(), "Bruce Wayne"), new Actor(UUID.randomUUID(), "Test1235"));
    return new Movie(UUID.randomUUID(), "Batman", LocalDate.of(2000, 12, 31), actors);
  }
}
