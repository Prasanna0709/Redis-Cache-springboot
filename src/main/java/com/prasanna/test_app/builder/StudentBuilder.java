package com.prasanna.test_app.builder;

import com.prasanna.test_app.dto.StudentDto;
import com.prasanna.test_app.entity.StudentEntity;

public class StudentBuilder {

    private StudentBuilder(){}

    public static StudentDto buildStudentDto(StudentEntity studentEntity){
        return StudentDto.builder()
                .id(studentEntity.getId())
                .name(studentEntity.getName())
                .email(studentEntity.getEmail())
                .department(studentEntity.getDepartment())
                .joinedAt(studentEntity.getJoinedAt())
                .build();
    }

}
