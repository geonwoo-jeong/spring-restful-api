package com.geonwoojeong.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value = { "password"})
@JsonFilter("UserInfo")
public class User {

  private Integer id;

  @Size(min = 2, message = "minimum 2")
  private String name;

  @Past(message = "Past date")
  private Date joinDate;

//  @JsonIgnore
  private String password;

//  @JsonIgnore
  private String ssn;
}
