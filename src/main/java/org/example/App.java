package org.example;

import org.example.model.*;
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
                addAnnotatedClass(Actor.class).
                addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();


        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Movie movie = new Movie("Reservoir dogs", 1992);
            Actor actor = session.get(Actor.class, 1);

            movie.setActors(new ArrayList<>(Collections.singletonList(actor)));
            actor.getMovies().add(movie);//объект, находясь в persistent состоянии, отслеживается hibernate
            session.persist(movie);
            session.getTransaction().commit();
        }

    }
}
