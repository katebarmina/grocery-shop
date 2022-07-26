package models;

import java.util.Objects;

public class User {

  private Long id;
  private String email;
  private String password;

  private Role role;

  public User() {}

  public User(String email, String password, Role role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(email, user.email)
        && Objects.equals(password, user.password)
        && role == user.role;
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password, role);
  }
}
