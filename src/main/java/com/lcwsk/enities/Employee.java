package com.lcwsk.enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.core.serializer.Serializer;

import java.io.Serializable;

@Entity
@Table(name = "Employee_Table")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "employee name")
    private String name;
    private double salary;
//    @Autowired
//    private Address address;
}

