
package com.in28minutes.rest.webservices.restfulwebservices.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data //Add getters and setters for all my fields.
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    @Size(min = 2)
    private String name;
    @Past
    private Date birthDate;


    @Override
    public String toString() {
        return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
    }

}
