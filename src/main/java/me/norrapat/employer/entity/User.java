package me.norrapat.employer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(updatable = false)
    private String username;

    @JsonIgnore
    @ToString.Exclude
    private String password;

    @NotEmpty
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {USER, ADMIN, USER_MANAGER}
}
