package com.bobocode.svydovets.entity;

import com.bobocode.svydovets.bibernate.annotation.Column;
import com.bobocode.svydovets.bibernate.annotation.Entity;
import com.bobocode.svydovets.bibernate.annotation.GeneratedValue;
import com.bobocode.svydovets.bibernate.annotation.Id;
import com.bobocode.svydovets.bibernate.annotation.Table;
import com.bobocode.svydovets.bibernate.constant.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table("orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.MANUAL)
    private Long id;
    @Column
    private String name;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
}
