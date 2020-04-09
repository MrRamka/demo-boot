package com.ybcompany.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Ramil Minyukov - https://github.com/MrRamka
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
/*
The dynamic-update attribute tells Hibernate whether to include unmodified properties in the SQL UPDATE statement.
*/
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 255)
    @Column(nullable = false)
    private String fullName;

    @NotNull
    @Length(min = 4)
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @Length(min = 8)
    @Column(nullable = false)
    private String hashPassword;


    /*
     * Store string value
     */
    @Enumerated(value = EnumType.STRING)
    private Role role;


    /*
     * Creation date
     */
    private LocalDateTime createdAt;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                fullName.equals(user.fullName) &&
                username.equals(user.username) &&
                createdAt.equals(user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, username, createdAt);
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
