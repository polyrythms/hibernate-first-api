package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class App 
{
    public static void main( String[] args )
    {
        //подключим конфигурацию из файла hibernate.properties (не явно)
        //укажем сущность Person
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);
        //создаем фабрику сессий
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        //получаем сессию
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person1 = new Person("Test1", 30);
            Person person2 = new Person("Test2", 40);
            Person person3 = new Person("Test3", 50);
            session.save(person1);
            session.save(person2);
            session.save(person3);


            //закроем сессию
            session.getTransaction().commit();
        } finally {
            //зкароем фабрику
            sessionFactory.close();
        }
    }
}
