package com.service.mental_health_therapy_center.configuration;

import com.service.mental_health_therapy_center.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class FactoryConfiguration {


 private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration();

        Properties properties = new Properties();

        try (InputStream inputStream = FactoryConfiguration.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (inputStream == null) {
                throw new IOException("Unable to find hibernate.properties");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load hibernate.properties", e);
        }
       configuration.setProperties(properties)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Therapist.class)
                    .addAnnotatedClass(Patient.class)
                    .addAnnotatedClass(Payment.class)
                    .addAnnotatedClass(TherapyProgram.class)
                    .addAnnotatedClass(TherapySession.class)
                    .addAnnotatedClass(Registration.class);

        sessionFactory = configuration.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
    public void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
