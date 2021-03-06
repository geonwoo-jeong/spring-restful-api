package com.geonwoojeong.restfulwebservice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jpa")
public class UserJpaController {

  private final UserRepository userRepository;

  @GetMapping("/users")
  public List<User> retrieveAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/users/{id}")
  public User retrieveUser(@PathVariable int id) {
    Optional<User> user = userRepository.findById(id);

    if (user.isPresent()) {
      throw new UserNotFoundException(String.format("ID[%s] not found", id));
    }

    EntityModel<User> model = EntityModel.of(user.get());
    WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
    model.add(linkTo.withRel("all-users"));

    return user.get();
  }

  @GetMapping("/users/{id}/posts")
  public List<Post> retrieveAllPostsByUser(@PathVariable int id) {
    Optional<User> user = userRepository.findById(id);

    if (!user.isPresent()) {
      throw new UserNotFoundException(String.format("ID[%s} not found", id));
    }

    return user.get().getPosts();
  }
}
