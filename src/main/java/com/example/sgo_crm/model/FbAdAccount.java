package com.example.sgo_crm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "fbAdAccount")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FbAdAccount {

    @Id
    @Column(name = "adAccount_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adAccountId;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "fbAccount_Id")
    private String fbAccountId;

    @Column(name = "monthly_budget")
    private BigDecimal monthlyBudget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_Id")
    private FbAdPage fbAdPage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FbAdAccount that = (FbAdAccount) o;
        return Objects.equals(adAccountId, that.adAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adAccountId);
    }
}
