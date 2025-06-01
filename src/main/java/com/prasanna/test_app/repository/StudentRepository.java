package com.prasanna.test_app.repository;

import com.prasanna.test_app.entity.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<StudentEntity,String> {
    Optional<StudentEntity> findById(String id);
}
