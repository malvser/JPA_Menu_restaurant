package malov.serg;

import javax.persistence.*;


/**
 * Created by Admin on 01.04.2017.
 */
@Entity
@Table(name = "Menu")
@NamedQueries({
        @NamedQuery(name = "Menu.findAll", query = "SELECT c FROM Dish c"),
        @NamedQuery(name = "Menu.discount", query = "SELECT c FROM Dish c WHERE c.discount > 0")
})
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer cost;

    @Column(nullable = false)
    private Integer weight;

    private Integer discount;

    public Dish() {}

    public Dish(String name, Integer cost, Integer weight) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
    }

    public Dish(String name, Integer cost, Integer weight, Integer discount) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", weight=" + weight +
                ", discount=" + discount +
                '}';
    }
}
