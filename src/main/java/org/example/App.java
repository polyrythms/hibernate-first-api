package org.example;

import org.example.model.Item;
import org.example.model.Passport;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.postgresql.jdbc2.ArrayAssistant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration().
                addAnnotatedClass(Person.class).
                addAnnotatedClass(Passport.class).
                addAnnotatedClass(Item.class);
        //создаем фабрику сессий
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        //получаем сессию
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            Person person = session.get(Person.class, 14);
            session.remove(person);

            session.getTransaction().commit();
        } finally {
            //закроем фабрику
            sessionFactory.close();
        }
    }
}
