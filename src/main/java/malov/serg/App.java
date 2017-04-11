package malov.serg;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Admin on 01.04.2017.
 */
public class App {

    static EntityManagerFactory emf;
    static EntityManager em;
    static Query query;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        emf = Persistence.createEntityManagerFactory("JPAMenu");
        em = emf.createEntityManager();
        try {
            while (true) {
                System.out.println("1: add dish");
                System.out.println("2: delete dish");
                System.out.println("3: change dish");
                System.out.println("4: view dish");
                System.out.println("5: select weight of dish");
                System.out.println("6: select discount of dish");
                System.out.print("-> ");

                String s = sc.nextLine();
                switch (s) {
                    case "1":
                        addDish(sc);
                        break;
                    case "2":
                        deleteDish(sc);
                        break;
                    case "3":
                        changeDish(sc);
                        break;
                    case "4":
                        viewClients();
                        break;
                    case "5":
                        selectWeightDish(sc, sc);
                        break;
                    case "6":
                        selectDiscountDish();
                        break;
                    default:
                        return;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        } finally {
            sc.close();
            em.close();
            emf.close();
        }
    }

    private static void addDish(Scanner sc) {
        System.out.print("Enter dish name: ");
        String name = sc.nextLine();
        System.out.print("Enter cost of dish: ");
        String c = sc.nextLine();
        int cost = Integer.parseInt(c);
        System.out.print("Enter weight of dish: ");
        String w = sc.nextLine();
        int weight = Integer.parseInt(w);
        System.out.print("Enter discount of dish: ");
        String d = sc.nextLine();
        int discount = Integer.parseInt(d);

        em.getTransaction().begin();
        try {
            Dish dish = new Dish(name, cost, weight, discount);
            em.persist(dish);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void deleteDish(Scanner sc) {
        System.out.print("Enter dish id: ");
        String sId = sc.nextLine();
        int id = Integer.parseInt(sId);

        Dish c = em.find(Dish.class, id);
        if (c == null) {
            System.out.println("Dish not found!");
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(c);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void changeDish(Scanner sc) {
        System.out.print("Enter name of dish: ");
        String name = sc.nextLine();

        System.out.print("Enter new cost: ");
        String s = sc.nextLine();
        Integer cost = Integer.parseInt(s);

        Dish c = null;
        try {
            query = em.createQuery("SELECT c FROM Dish c WHERE c.name = :name", Dish.class);
            query.setParameter("name", name);
            c = (Dish) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Dish not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        em.getTransaction().begin();
        try {
            c.setCost(cost);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }



    private static void viewClients() {
        query = em.createNamedQuery("Menu.findAll");

        List<Dish> dishs = query.getResultList();

        for (Dish dish : dishs) {
            System.out.println(dish);
        }
    }



    private static void selectWeightDish(Scanner q, Scanner e){
        System.out.println("Enter weight from ");
        String s = q.nextLine();
        int a = Integer.parseInt(s);
        System.out.println("Enter weight to ");
        String d = e.nextLine();
        int g = Integer.parseInt(d);
        String r = String.format("SELECT c FROM Dish c WHERE c.weight > %s and c.weight < %s",a ,g );
        query = em.createQuery(r, Dish.class);

        List<Dish> menu_costs = query.getResultList();

        for(Dish dish : menu_costs)
        {
            System.out.println(dish);
        }
    }

    private static void selectDiscountDish(){

        query = em.createNamedQuery("Menu.discount");
        List<Dish> menu_discount = query.getResultList();

        for(Dish dish : menu_discount)
        {
            System.out.println(dish);
        }
    }







}



