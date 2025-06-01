package com.prasanna.test_app.mapper;

import com.prasanna.test_app.builder.StudentBuilder;
import com.prasanna.test_app.dto.StudentDto;
import com.prasanna.test_app.entity.StudentEntity;

import java.util.List;

public class StudentDtoMapper {

    private StudentDtoMapper(){}

    public static List<StudentDto> studentDtoMapper(List<StudentEntity> studentEntities){
        return studentEntities.stream()
                .map(StudentBuilder::buildStudentDto)
                .toList();
    }

}
