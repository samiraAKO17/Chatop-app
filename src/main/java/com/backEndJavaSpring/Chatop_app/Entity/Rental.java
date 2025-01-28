package com.backEndJavaSpring.Chatop_app.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Float surface;
    @Getter
    @Setter
    private Float price;
    @Getter
    @Setter
    private String picture;
    @Getter
    @Setter
    private String description;
    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name = "owner_id")
    private User owner;
    @Getter
    @Setter
    private Date created_at;
    @Getter
    @Setter
    private Date updated_at;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


}
