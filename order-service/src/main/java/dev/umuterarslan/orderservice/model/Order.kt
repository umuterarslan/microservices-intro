package dev.umuterarslan.orderservice.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "orders")
data class Order(
    @Id
    val id: UUID = UUID.randomUUID(),
    val orderNumber: String = "",
    val userId: String = "",
    val address: String = "",
    val createdDate: Date = Date(),
    @OneToMany(mappedBy = "order")
    val items: List<OrderItem> = ArrayList<OrderItem>()
)