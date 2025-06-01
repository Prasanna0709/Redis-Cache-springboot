package com.prasanna.test_app.service;

import com.prasanna.test_app.custom.IllegalArgumentsException;
import com.prasanna.test_app.dto.StudentDto;
import com.prasanna.test_app.entity.StudentEntity;
import com.prasanna.test_app.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest{

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;


    // Test for addStudent where the Valid Details is given
    @Test
    void addStudentTest_WithValidInput_ReturnsCreated(){
        StudentDto studentDto = StudentDto.builder()
                .name("Joel")
                .email("joel1@gmail.com")
                .department("ECE")
                .build();

        StudentEntity saved = StudentEntity.builder()
                .id("1")
                .name("Joel")
                .email("joel1@gmail.com")
                .department("ECE")
                .joinedAt(LocalDateTime.now())
                .build();

        when(studentRepository.save(any(StudentEntity.class))).thenReturn(saved);
        StudentDto responseDto = studentService.addStudent(studentDto);

        //Asserts for checking whether the responseDto is valid :-
        assertNotNull(responseDto);
        assertEquals("Joel",responseDto.getName());
        assertEquals("joel1@gmail.com",responseDto.getEmail());
        assertEquals("ECE",responseDto.getDepartment());
        assertNotNull(responseDto.getJoinedAt());
    }

    // Test for addStudent when we Provide null object :-
    @Test
    void  addStudent_WithNull_ReturnsIllegalArgumentException(){
        assertThrows(IllegalArgumentsException.class,() -> studentService.addStudent(null));
    }

    //Test for updateStudent method when
    @Test
    void updateStudent_WhenValidInput_ReturnsOk(){
        StudentDto studentDto = StudentDto.builder()
                .id("1")
                .name("Raju")
                .email("raju1@gmail.com")
                .department("ECE")
                .build();

        StudentEntity existingEntity = StudentEntity.builder()
                .id("1")
                .name("Rajiv")
                .email("raju@gmail.com")
                .department("CSE")
                .joinedAt(LocalDateTime.now())
                .build();

        StudentEntity updatedEntity = StudentEntity.builder()
                        .id("1")
                        .name("Raju")
                        .email("raju1@gmail.com")
                        .department("ECE")
                        .joinedAt(LocalDateTime.now())
                        .build();

        when(studentRepository.save(any(StudentEntity.class))).thenReturn(updatedEntity);
        StudentDto responseDto = studentService.updateStudent(studentDto);

        assertNotNull(responseDto);
        assertEquals("1",studentDto.getId());
        assertEquals("Raju",responseDto.getName());
        assertEquals("raju1@gmail.com",responseDto.getEmail());
        assertEquals("ECE",responseDto.getDepartment());
        assertNotEquals(existingEntity.getName(),responseDto.getName());
        assertNotNull(studentDto.getJoinedAt());
    }
}
