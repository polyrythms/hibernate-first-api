package org.example.model;

import jakarta.persistence.*;

@Entity //указывает связь сущности и дб
@Table(name = "Person") //соответсвующая таблица в бд
// (можно не указываться, если имя класса совпадает)
public class Person {
    @Id
    @Column(name = "id") //сопоставляет колонки дб полям класса
    //стратегия для последовательности в бд. Генератор указывается отдельно
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "seq_generator_person")
    @SequenceGenerator(name="seq_generator_person",
    sequenceName = "person_id_seq", //имя в бд
            allocationSize = 1 //множитель ID: 20, 40, 60
    )
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")

    private int age;

    public Person() {}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
