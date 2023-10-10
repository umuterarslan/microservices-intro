package dev.umuterarslan.orderservice.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "order_items")
data class OrderItem(
    @Id
    val id: UUID = UUID.randomUUID(),
    val productId: String = "",
    val quantity: Int = 0,
    val price: Double = 0.0,
    @ManyToOne(cascade = [CascadeType.DETACH])
    @JoinColumn(name = "order_id")
    val order: Order = Order(),
)
