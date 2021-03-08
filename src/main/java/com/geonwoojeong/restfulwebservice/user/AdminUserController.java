package com.geonwoojeong.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminUserController {
  private final UserDaoService userDaoService;

  @GetMapping("/users")
  public List<User> retrieveAllUsers() {
    return userDaoService.findAll();
  }

  @GetMapping("/users/{id}")
  public MappingJacksonValue retrieveUser(@PathVariable int id) {
    User user = userDaoService.findOne(id);

    if (user == null) {
      throw new UserNotFoundException(String.format("ID[%s] not found", id));
    }

    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
            .filterOutAllExcept("id", "name", "joinDate", "ssn");

    FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("UserInfo", filter);

    MappingJacksonValue mapping = new MappingJacksonValue(user);

    mapping.setFilters(filterProvider);

    return mapping;
  }

}
