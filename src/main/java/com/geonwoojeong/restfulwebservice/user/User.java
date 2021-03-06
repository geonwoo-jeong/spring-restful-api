package com.geonwoojeong.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(value = { "password"})
//@JsonFilter("UserInfo")
@ApiModel("User Detail Info")
@Entity
public class User {

  @Id
  @GeneratedValue
  private Integer id;

  @Size(min = 2, message = "minimum 2")
  @ApiModelProperty("Please enter user name")
  private String name;

  @Past(message = "Past date")
  @ApiModelProperty("Please enter join date")
  private Date joinDate;

//  @JsonIgnore
@ApiModelProperty("Please enter password")
  private String password;

//  @JsonIgnore
  private String ssn;

  @OneToMany(mappedBy = "user")
  private List<Post> posts;

  public User(int id, String name, Date joinDate, String password, String ssn) {
    this.id = id;
    this.name = name;
    this.joinDate = joinDate;
    this.password = password;
    this.ssn = ssn;
  }
}
