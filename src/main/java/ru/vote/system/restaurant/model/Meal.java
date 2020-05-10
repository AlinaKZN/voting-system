package ru.vote.system.restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import ru.vote.system.restaurant.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m WHERE m.restaurant.id=:restaurantId"),
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id"),
        @NamedQuery(name = Meal.GET_MENU, query = "SELECT m FROM Meal m " +
                "WHERE m.restaurant.id=:restaurantId AND m.date = :date"),
//        @NamedQuery(name = Meal.UPDATE, query = "UPDATE Meal m SET m.dateTime = :datetime, m.calories= :calories," +
//                "m.description=:desc where m.id=:id and m.user.id=:userId")
})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date"}, name = "meals_unique_restaurant_date_idx")})
@Setter
@Getter
@RequiredArgsConstructor
@ToString
public class Meal extends AbstractNamedEntity {
    public static final String ALL_SORTED = "Meal.getAll";
    public static final String DELETE = "Meal.delete";
    public static final String GET_MENU = "Meal.getMenu";

    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 5000)
    private int price;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    @JsonBackReference
    private Restaurant restaurant;

 /*   public Meal() {
    }

    public Meal(String name, int price, LocalDate date) {
        this(null, name, price, date);
    }

    public Meal(Integer id, String name, int price, LocalDate date) {
        super(id, name);
        this.price = price;
        this.date = date;
    }*/
}
