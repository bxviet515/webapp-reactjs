package com.xuanviet.webapp_reactjs.entity.authentication;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.xuanviet.webapp_reactjs.entity.BaseEntity;
import com.xuanviet.webapp_reactjs.entity.address.Address;
import com.xuanviet.webapp_reactjs.entity.client.PreOrder;
import com.xuanviet.webapp_reactjs.entity.client.Wish;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "user")
public class User extends BaseEntity {
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "gender", nullable = false, columnDefinition = "CHAR")
    private String gender;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false, referencedColumnName = "id", unique = true)
    private Address address;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT")
    private Integer status;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false)
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<Wish> wishs = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PreOrder> preorders = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Order> orders  = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Message> messages = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Room room;

    @OneToOne(mappedBy = "user")
    private Verification verification;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;


    @OneToMany(mappedBy = "user")
    private List<RewardLog> rewardLogs = new ArrayList<>();
}
