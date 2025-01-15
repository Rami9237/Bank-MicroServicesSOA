package com.example.barememicroservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Bareme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double tauxNominal;

    @Column(nullable = false)
    private int dureeMinimale;

    @Column(nullable = false)
    private int dureeMaximale;

    @Column(nullable = false)
    private double montantMinimal;

    @Column(nullable = false)
    private double montantMaximal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTauxNominal() {
        return tauxNominal;
    }

    public void setTauxNominal(double tauxNominal) {
        this.tauxNominal = tauxNominal;
    }

    public int getDureeMinimale() {
        return dureeMinimale;
    }

    public void setDureeMinimale(int dureeMinimale) {
        this.dureeMinimale = dureeMinimale;
    }

    public int getDureeMaximale() {
        return dureeMaximale;
    }

    public void setDureeMaximale(int dureeMaximale) {
        this.dureeMaximale = dureeMaximale;
    }

    public double getMontantMinimal() {
        return montantMinimal;
    }

    public void setMontantMinimal(double montantMinimal) {
        this.montantMinimal = montantMinimal;
    }

    public double getMontantMaximal() {
        return montantMaximal;
    }

    public void setMontantMaximal(double montantMaximal) {
        this.montantMaximal = montantMaximal;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Bareme bareme = (Bareme) o;
        return getId() != null && Objects.equals(getId(), bareme.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
