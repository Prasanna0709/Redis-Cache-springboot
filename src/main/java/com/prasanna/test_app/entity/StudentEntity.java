package com.prasanna.test_app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "studentDetails")
public class StudentEntity {
    @Id
    private String id;
    private String name;
    private String email;
    private String department;
    private LocalDateTime joinedAt;
}
