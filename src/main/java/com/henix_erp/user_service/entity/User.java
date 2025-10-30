package com.henix_erp.user_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.henix_erp.common.tenant.TenantEntity;
import com.henix_erp.common.tenant.TenantEntityListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@EntityListeners(TenantEntityListener.class)
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class User extends TenantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "registration_type")
    private String registrationType;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Transient
    private int roleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    @JsonIgnore
    private Role role;
}
