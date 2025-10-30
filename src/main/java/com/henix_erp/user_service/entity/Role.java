package com.henix_erp.user_service.entity;

import com.henix_erp.common.tenant.TenantEntity;
import com.henix_erp.common.tenant.TenantEntityListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@EntityListeners(TenantEntityListener.class)
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Role extends TenantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    private int isActive;
}
