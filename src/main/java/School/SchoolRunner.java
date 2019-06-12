package School;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;

import javax.persistence.EntityTransaction;

import javax.persistence.Persistence;

import javax.persistence.Query;

import javax.persistence.TypedQuery;



public class SchoolRunner {



    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory

                = Persistence.createEntityManagerFactory("baza_mySQL");



        EntityManager entityManager

                = entityManagerFactory.createEntityManager();



        System.out.println("Is ok: " + entityManager.isOpen());



        EntityTransaction tx = entityManager.getTransaction();

        tx.begin();

//        HighSchool hs = new HighSchool("SDA High School 2");

//        Address address = new Address("Malmeda", "B-stok", 2);

//        Student st1 = new Student("Wieczny student");

//        Student st2 = new Student("Bylbym profesorem");

//

//        hs.setAddress(address);

//        hs.addStudent(st1);

//        hs.addStudent(st2);

//        address.setSchool(hs);

//

//        entityManager.persist(hs);

//        entityManager.persist(address);

//        entityManager.persist(st1);

//        entityManager.persist(st2);



        HighSchool sda = entityManager.find(HighSchool.class, 19L);

        System.out.println("School info:");

        System.out.println(sda);

        System.out.println("School address info:");

        System.out.println(sda.getAddress());



        System.out.println("School students info:");

        System.out.println(sda.getStudent(0));

        System.out.println(sda.getStudent(1));



        tx.commit();

        System.out.println("----------------------");

        tx.begin();



        TypedQuery<Address> query = entityManager.createNamedQuery("adres", Address.class);

        List<Address> ad = query.getResultList();



        ad.stream().forEach(System.out::println);



        System.out.println("Adress id = 18");

        TypedQuery qById = entityManager.createNamedQuery("adresById", Address.class);

        qById.setParameter("idAddress", 18L);

        Address foundAdr = (Address) qById.getSingleResult();

        System.out.println(foundAdr);



        System.out.println("----------------------");

        Query qStudent = entityManager.createNamedQuery("findByStudentNameLike");

        qStudent.setParameter("name_param", "pro");

        List<Object> fHS_l = qStudent.getResultList();

        Object[] fHS = (Object[]) fHS_l.get(0);

        HighSchool hs_1 = (HighSchool) fHS[0];

        System.out.println(hs_1);

        System.out.println("\t" + hs_1.getStudent(0));



        Student s_1 = (Student) fHS[1];

        System.out.println(s_1);



        tx.commit();



        entityManager.close();

        entityManagerFactory.close();



        System.exit(0);

    }



}

