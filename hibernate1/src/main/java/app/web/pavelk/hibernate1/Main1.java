package app.web.pavelk.hibernate1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Main1 {

    public static void main(String[] args) {

        SessionFactory sessionFactory = getSessionFactory();
        Session session = null;
        try {
            //create
            String sql = Files.lines(Paths.get("hibernate1/sql1.sql")).collect(Collectors.joining(" "));
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();

            for (int i = 0; i < 100; i++) {
                session.save(new Student("name " + i, "mark " + (i / 3)));
            }

            //update
            for (int i = 1; i < 5; i++) {
                Student student1 = session.get(Student.class, (long) i);
                student1.setName("up" + i);
                student1.setMark("mr" + (i * 5));
                session.update(student1);
            }

            //delete
            for (int i = 7; i < 17; i++) {
                Student student1 = session.get(Student.class, (long) i);
                session.delete(student1);
            }

            List<Long> list = new ArrayList<Long>();
            for (int i = 0; i < 50; i++) {
                list.add(ThreadLocalRandom.current().nextLong(1, 100));
            }
            Query q = session.createQuery("DELETE FROM Student s WHERE s.id IN (:list)");
            q.setParameterList("list", list);
            q.executeUpdate();

            session.getTransaction().commit();

            //select
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("from Student as s order by s.id ").setMaxResults(10)
                    .list()
                    .forEach(System.out::println);
            Optional<Student> student = Optional.ofNullable(session.get(Student.class, 1L));
            System.out.println(student.orElse(new Student()));
            Query query = session.createQuery("select count(s) from Student as s");
            System.out.println(query.list().get(0));
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    public static SessionFactory getSessionFactory() {
        Map<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class", "org.postgresql.Driver");
        settings.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        settings.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/student1");
        settings.put("hibernate.connection.username", "postgres");
        settings.put("hibernate.connection.password", "0");
        settings.put("hibernate.current_session_context_class", "thread");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.format_sql", "true");
        settings.put("connection.pool_size", "4");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings).build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(Student.class);
        Metadata metadata = metadataSources.buildMetadata();
        return metadata.getSessionFactoryBuilder().build();
    }
}
