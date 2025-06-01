package com.prasanna.test_app.controller;

import com.prasanna.test_app.dto.StudentDto;
import com.prasanna.test_app.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto){
            StudentDto responseDto =  studentService.addStudent(studentDto);
             return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PutMapping
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto){
        StudentDto responseDto = studentService.updateStudent(studentDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id){
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body("Student deleted !");
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable String id){
        StudentDto studentDto = studentService.getStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body(studentDto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<StudentDto>> allStudents(){
        List<StudentDto> studentDtoList = studentService.allStudents();
        return  ResponseEntity.ok(studentDtoList);
    }

}
