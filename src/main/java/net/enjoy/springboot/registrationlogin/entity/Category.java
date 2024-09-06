package net.enjoy.springboot.registrationlogin.entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Set;

@Entity
@Table
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long category_id;
    @NotEmpty(message ="Category name required")
    private String name;
    @NotEmpty
    private TransactionType transactionType;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "category")
    private Set<Transaction> transactions;

    public Long getId() {
        return category_id;
    }

    public void setId(Long id) {
        this.category_id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public  String getName() {
        return name;
    }

    public Category(String name, Long id, TransactionType transactionType, User user, Set<Transaction> transactions) {
        this.name = name;
        this.category_id = id;
        this.transactionType = transactionType;
        this.user = user;
        this.transactions = transactions;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Category (){}

    public void setName(String name) {
        this.name = name;
    }

    public  TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
