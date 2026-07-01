package com.example.crudBasics.service;


import com.example.crudBasics.entity.Student;
import com.example.crudBasics.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq) {
        studentReq.setDeleted(false);
        Student newStudent = studentRepository.save(studentReq);
        return newStudent;
    }

    public Student getStudent(Long id) {
        Optional<Student> student = studentRepository.findByIdAndDeletedIsFalse(id);

        return student.orElse(null);
    }


    public List<Student> getAllStudent() {
        return studentRepository.findByDeletedIsFalse();
    }

    public Student updateStudent(Long id, Student student) {
        Optional<Student> studentOptional = studentRepository.findByIdAndDeletedIsFalse(id);
        if (studentOptional.isEmpty()) {
            return null;
        }
        Student updatedStudent = studentOptional.get();
        updatedStudent.setName(student.getName());
        updatedStudent.setAge(student.getAge());
        updatedStudent.setRollNo(student.getRollNo());
        updatedStudent.setSubject(student.getSubject());
        updatedStudent.setEmail(student.getEmail());
        updatedStudent.setDeleted(false);
        return studentRepository.save(updatedStudent);
    }

    public boolean deleteStudent(Long id) {

        boolean isStudent = studentRepository.existsById(id);
        if (!isStudent) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    public boolean deleteStudentSoftly(Long id) {

        Optional<Student> studentOptional = studentRepository.findByIdAndDeletedIsFalse(id);
        if (studentOptional.isEmpty()) {
            return false;
        }
        Student student = studentOptional.get();
        student.setDeleted(true);
        studentRepository.save(student);

        return false;
    }
}
