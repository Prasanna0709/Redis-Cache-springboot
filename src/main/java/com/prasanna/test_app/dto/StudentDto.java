package com.prasanna.test_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto implements Serializable {
    private String id;
    private String name;
    private String email;
    private String department;
    private LocalDateTime joinedAt;
}
