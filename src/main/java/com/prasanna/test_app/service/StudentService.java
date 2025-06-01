package com.prasanna.test_app.service;

import com.prasanna.test_app.custom.IllegalArgumentsException;
import com.prasanna.test_app.custom.StudentNotFoundException;
import com.prasanna.test_app.dto.StudentDto;
import com.prasanna.test_app.entity.StudentEntity;
import com.prasanna.test_app.mapper.StudentDtoMapper;
import com.prasanna.test_app.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @CacheEvict(value = "allStudentsCache",allEntries = true)
    public StudentDto addStudent(StudentDto studentDto){
        if(studentDto==null){
            throw new IllegalArgumentsException("Please Enter Valid Credentials!");
        }

        StudentEntity studentEntity = StudentEntity.builder()
                .name(studentDto.getName())
                .email(studentDto.getEmail())
                .department(studentDto.getDepartment())
                .joinedAt(LocalDateTime.now())
                .build();

        StudentEntity savedEntity = studentRepository.save(studentEntity);
        return StudentDto.builder()
                        .id(savedEntity.getId())
                        .name(savedEntity.getName())
                        .email(savedEntity.getEmail())
                        .department(savedEntity.getDepartment())
                        .joinedAt(savedEntity.getJoinedAt())
                .build();
    }

    @Caching(evict = {
            @CacheEvict(value = "studentCache",key="#studentDto.id"),
            @CacheEvict(value = "allStudentsCache",allEntries = true)
    })
    public StudentDto updateStudent(StudentDto studentDto){
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findById(studentDto.getId());
        if(optionalStudentEntity.isEmpty()){
            throw new StudentNotFoundException("Student with id "+studentDto.getId()+" Not Found !");
        }

        StudentEntity studentEntity = optionalStudentEntity.get();
        studentEntity.setName(StringUtils.isBlank(studentDto.getName()) ? studentEntity.getName() : studentDto.getName());
        studentEntity.setEmail(StringUtils.isBlank(studentDto.getEmail()) ? studentEntity.getEmail() : studentDto.getEmail());
        studentEntity.setDepartment(StringUtils.isBlank(studentDto.getDepartment()) ? studentEntity.getDepartment() : studentDto.getDepartment());

        StudentEntity studentEntity1 = studentRepository.save(studentEntity);
        return StudentDto.builder()
                        .id(studentEntity1.getId())
                        .name(studentEntity1.getName())
                        .email(studentEntity1.getEmail())
                        .department(studentEntity1.getDepartment())
                        .joinedAt(studentEntity1.getJoinedAt())
                .build();
    }

    @Caching(evict = {
            @CacheEvict(value = "studentCache",key = "#id"),
            @CacheEvict(value = "allStudentsCache",allEntries = true)
    })
    public void deleteStudent(String id){
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findById(id);
        if(optionalStudentEntity.isEmpty()){
            throw new StudentNotFoundException("Student with id "+id+" not found !");
        }

        studentRepository.deleteById(id);
    }

    @Cacheable(value = "studentCache",key = "#id")
    public StudentDto getStudent(String id){
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findById(id);
        if(optionalStudentEntity.isEmpty()){
            throw new StudentNotFoundException("Student not found");
        }

        StudentEntity studentEntity = optionalStudentEntity.get();
        return StudentDto.builder()
                        .id(studentEntity.getId())
                        .name(studentEntity.getName())
                        .email(studentEntity.getEmail())
                        .department(studentEntity.getDepartment())
                        .joinedAt(studentEntity.getJoinedAt())
                .build();
    }

    @Cacheable("allStudentsCache")
    public List<StudentDto> allStudents(){
        List<StudentEntity> studentEntities = studentRepository.findAll();
        return StudentDtoMapper.studentDtoMapper(studentEntities);
    }

}
