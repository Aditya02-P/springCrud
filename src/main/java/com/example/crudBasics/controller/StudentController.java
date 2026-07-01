package com.example.crudBasics.controller;


import com.example.crudBasics.entity.Student;
import com.example.crudBasics.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println("Inside Controller");
        Student cs = studentService.createStudent(student);
        System.out.println("Exiting Controller");

        return ResponseEntity.status(HttpStatus.CREATED).body(cs);
    }


    //read one student
    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student reqSt= studentService.getStudent(id);
        if(reqSt==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reqSt);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(reqSt);

    }

    //readdAll
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> list = studentService.getAllStudent();

        if(list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(list);
        }

        return ResponseEntity.status(HttpStatus.OK).body(list);

    }

    //UPDATE MAPPING

    @PutMapping("/update/{id}")

    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student cs = studentService.updateStudent(id,student);

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
