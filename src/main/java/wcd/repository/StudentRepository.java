package wcd.repository;

import wcd.entity.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findAll();
    void save(Student student);
    Student findById(int id);
    void update(Student student);
    void delete(int id);

    // advance functions
    List<Student> findByName(String name);
}
