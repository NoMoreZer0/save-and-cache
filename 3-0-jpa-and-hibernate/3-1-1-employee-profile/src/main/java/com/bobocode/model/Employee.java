package com.bobocode.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * todo:
 * - configure JPA entity
 * - specify table name: "employee"
 * - configure auto generated identifier
 * - configure not nullable columns: email, firstName, lastName
 *
 * - map unidirectional relation between {@link Employee} and {@link EmployeeProfile} on the child side
 */

@Entity
@Data
@NoArgsConstructor
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String fistName;
    private String lastName;
}
