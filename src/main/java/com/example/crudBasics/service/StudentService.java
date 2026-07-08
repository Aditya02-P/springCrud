package com.example.crudBasics.service;


import com.example.crudBasics.Exception.ResourceNotFoundEx;
import com.example.crudBasics.dto.StudentReqDto;
import com.example.crudBasics.dto.StudentResponseDto;
import com.example.crudBasics.entity.Student;
import com.example.crudBasics.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentResponseDto createStudent(StudentReqDto studentReq) {

        Student student = dtoToStudent(studentReq);
        Student newStudent = studentRepository.save(student);

        return mapToResponseDto(newStudent);
    }

    private StudentResponseDto mapToResponseDto(Student newStudent) {

        StudentResponseDto responseDto = new StudentResponseDto();

        responseDto.setName(newStudent.getName());
        responseDto.setEmail(newStudent.getEmail());
        responseDto.setAge(newStudent.getAge());
        responseDto.setRollNo(newStudent.getRollNo());
        responseDto.setSubject(newStudent.getSubject());
        responseDto.setCreatedDate(newStudent.getCreatedDate());

        return responseDto;
    }

    private Student dtoToStudent(StudentReqDto dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        student.setRollNo(dto.getRollNo());
        student.setSubject(dto.getSubject());
        student.setCreatedDate(LocalDateTime.now());
        student.setUpdatedDate(LocalDateTime.now());

        return student;
    }

    public StudentResponseDto getStudent(Long id) {
        Optional<Student> student = Optional.of(studentRepository.
                findByIdAndDeletedIsFalse(id).
                orElseThrow(() -> new ResourceNotFoundEx("Student with id " + id + " not found")));

            return mapToResponseDto(student.get());

    }


    public List<StudentResponseDto> getAllStudent() {
        List<Student> list= studentRepository.findByDeletedIsFalse();

        return list.stream().map(this::mapToResponseDto).toList();

    }

    public StudentResponseDto updateStudent(Long id, StudentReqDto student) {

        Optional<Student> studentOptional =
                studentRepository.findByIdAndDeletedIsFalse(id);

        if (studentOptional.isEmpty()) {
            return null;
        }

        Student savedStudent = studentOptional.get();

        savedStudent.setName(student.getName());
        savedStudent.setEmail(student.getEmail());
        savedStudent.setAge(student.getAge());
        savedStudent.setRollNo(student.getRollNo());
        savedStudent.setSubject(student.getSubject());

        Student updatedStudent = studentRepository.save(savedStudent);

        return mapToResponseDto(updatedStudent);
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
