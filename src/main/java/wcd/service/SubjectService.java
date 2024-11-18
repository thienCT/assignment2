package wcd.service;
import wcd.entity.Classroom;
import wcd.entity.Subject;
import wcd.repository.Impl.SubjectRepositoryImpl;
import wcd.repository.SubjectRepository;

import java.util.List;

public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = new SubjectRepositoryImpl();
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(int id) throws Exception {
        Subject subject = subjectRepository.findById(id);
        if(subject == null){
            throw new Exception("Classroom not found!");
        }
        return subject;
    }

    public void saveSubject(Subject subject) throws Exception{
        if(subject.getName().isEmpty() || subject.getName().length() < 6){
            throw new Exception("Please input name");
        }

        subjectRepository.save(Subject);
    }

    public void updateSubject(Subject subject) throws Exception{
        if(Subject.getName().isEmpty() || Subject.getName().length() < 6){
            throw new Exception("Please input name");
        }
        System.out.println(Subject.getName());
        subjectRepository.update(Subject);
    }

    public void deleteSubject(int id){
        subjectRepository.delete(id);
    }

    public List<Subject> searchSubject(String search){
        return subjectRepository.findByName(search);
    }
}
