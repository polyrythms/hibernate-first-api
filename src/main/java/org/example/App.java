package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


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

            session.createQuery("delete from Person  where age < 30").executeUpdate();
//            createQuery("FROM Person") is deprecated. use:
//            List<Person> people2 = session.createQuery("FROM Person", Person.class).getResultList();



            session.getTransaction().commit();
        } finally {
            //зкароем фабрику
            sessionFactory.close();
        }
    }
}
