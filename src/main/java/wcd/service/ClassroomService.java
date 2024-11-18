package wcd.service;

import wcd.entity.Classroom;
import wcd.repository.ClassroomRepository;
import wcd.repository.Impl.ClassroomRepositoryImpl;

import java.util.List;

public class ClassroomService {
    private final ClassroomRepository classroomRepository;

    public ClassroomService(){
        this.classroomRepository = new ClassroomRepositoryImpl();
    }

    public List<Classroom> getAllClasses(){
        return classroomRepository.findAll();
    }

    public Classroom getClassroomById(int id) throws Exception {
        Classroom classroom = classroomRepository.findById(id);
        if(classroom == null){
            throw new Exception("Classroom not found!");
        }
        return classroom;
    }

    public void saveClassroom(Classroom classroom) throws Exception{
        if(classroom.getName().isEmpty() || classroom.getName().length() < 6){
            throw new Exception("Please input name");
        }
//        if(classroomRepository.findByName(classroom.getName()) != null){
//            throw new Exception("Name is existed.");
//        }
        classroomRepository.save(classroom);
    }

    public void updateClassroom(Classroom classroom) throws Exception{
        if(classroom.getName().isEmpty() || classroom.getName().length() < 6){
            throw new Exception("Please input name");
        }
        System.out.println(classroom.getName());
        classroomRepository.update(classroom);
    }

    public void deleteClassroom(int id){
        classroomRepository.delete(id);
    }

    public List<Classroom> searchClassroom(String search){
        return classroomRepository.findByName(search);
    }
}
