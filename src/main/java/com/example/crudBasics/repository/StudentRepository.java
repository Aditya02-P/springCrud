package com.example.crudBasics.repository;


import com.example.crudBasics.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByIdAndDeletedIsFalse(Long id);
    List<Student> findByDeletedIsFalse();

    @Query("""
            SELECT s FROM Student s
            WHERE s.deleted = false
              AND (LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(s.subject) LIKE LOWER(CONCAT('%', :keyword, '%')))
            """)
    List<Student> searchByNameOrSubject(@Param("keyword") String keyword);

}
