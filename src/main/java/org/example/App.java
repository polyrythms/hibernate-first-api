package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
                addAnnotatedClass(Item.class);
        //создаем фабрику сессий
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        //получаем сессию
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            Person person = session.get(Person.class, 4);
            Item item = session.get(Item.class, 1);
            //обновим сущность Человека в хайбернейте
            item.getOwner().getItems().remove(item);
            //SQL
            item.setOwner(person);
            person.getItems().add(item);
            session.getTransaction().commit();
        } finally {
            //зкароем фабрику
            sessionFactory.close();
        }
    }
}
