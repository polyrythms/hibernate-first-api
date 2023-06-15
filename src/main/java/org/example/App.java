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

            //для примера получим сущность из таблицы в дб
            Person person = session.get(Person.class, 1); //получаем Person с id == 1
            System.out.println(person.getName());
            System.out.println(person.getAge());

            //закроем сессию
            session.getTransaction().commit();
        } finally {
            //зкароем фабрику
            sessionFactory.close();
        }
    }
}
