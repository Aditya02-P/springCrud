package com.example.crudBasics.controller;


import com.example.crudBasics.dto.StudentReqDto;
import com.example.crudBasics.dto.StudentResponseDto;
import com.example.crudBasics.entity.Student;
import com.example.crudBasics.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentReqDto studentReqDto) {
        StudentResponseDto cs = studentService.createStudent(studentReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cs);
    }


    //read one student
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id) {
        StudentResponseDto reqSt= studentService.getStudent(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(reqSt);

    }

    //readdAll
    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        List<StudentResponseDto> list = studentService.getAllStudent();

        if(list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(list);
        }

        return ResponseEntity.status(HttpStatus.OK).body(list);

    }

    //UPDATE MAPPING

    @PutMapping("/{id}  ")

    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id, @RequestBody StudentReqDto student) {
        StudentResponseDto cs = studentService.updateStudent(id,student);

        if(cs==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cs);
        }
        return ResponseEntity.status(HttpStatus.OK).body(cs);
    }

    //DeleteMapping
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        boolean del=studentService.deleteStudent(id);
        if(!del){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with id " + id + " not found");
        }
        return ResponseEntity.ok("Student deleted successfully");
    }

    @PatchMapping("/soft-delete/{id}")

    public ResponseEntity<String> softDeleteStudent(@PathVariable Long id) {
        boolean isDel= studentService.deleteStudentSoftly(id);

        if(!isDel){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with id " + id + " not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Student deleted successfully");
    }
}
