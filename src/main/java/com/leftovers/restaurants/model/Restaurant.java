package com.leftovers.restaurants.model;


import lombok.*;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_no", length = 15)
    private String phoneNo;

    @Column(name = "website", nullable = false)
    private String website;

    @Column(name = "open_time")
    private Time openTime;

    @Column(name = "close_time")
    private Time closeTime;

    @Column(name = "rating")
    @Builder.Default
    private BigDecimal rating = new BigDecimal("5.0");

    @Column(name = "rating_count")
    @Builder.Default
    private Integer ratingCount = 0;

    @Column(name = "address_id")
    private Integer addressId;

    @OneToOne
    @JoinColumn(name = "address_id", insertable = false, updatable = false)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private Address address;

    @OneToMany
    @JoinColumn(name="restaurant_id")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Food> menuItems;
}