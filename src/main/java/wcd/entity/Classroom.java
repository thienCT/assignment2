package wcd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classes")
@Getter
@Setter
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //    @Column(name = "nameXYZ")
    @Column(unique = true, name = "name")
    private String name;
    @OneToMany(mappedBy = "classroom")
    private List<Student> students;
}
