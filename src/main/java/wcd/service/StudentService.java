package wcd.service;

import wcd.entity.Student;
import wcd.repository.StudentRepository;
import wcd.repository.Impl.StudentRepositoryImpl;

import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository = new StudentRepositoryImpl();

    public StudentService(){
        this.studentRepository = new StudentRepositoryImpl();
    }

    public List<Student> getAllClasses(){
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) throws Exception {
        Student student = studentRepository.findById(id);
        if(student == null){
            throw new Exception("Classroom not found!");
        }
        return student;
    }

    public void saveStudent(Student) throws Exception{
        if(Student.getName().isEmpty() || Student.getName().length() < 6){
            throw new Exception("Please input name");
        }
        studentRepository.save(Student);
    }

    public void updateClassroom(Student student) throws Exception{
        if(student.getName().isEmpty() || student.getName().length() < 6){
            throw new Exception("Please input name");
        }
        System.out.println(student.getName());
        studentRepository.update(student);
    }

    public void deleteClassroom(int id){
        studentRepository.delete(id);
    }

    public List<Student> searchClassroom(String search){
        return StudentRepository.findByName(search);
    }
}
