package com.example.coursespring.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.coursespring.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT") //formatação da data para ISO-8601
	private Instant instant;
	
	private Integer orderStatus; // necessario definir metodos para que seja feita a instancia do OrderStatus
	
	@ManyToOne // anotacao para informar que client é uma FK
	@JoinColumn(name = "client_id") // anotacao para criar uma coluna com a FK
	private User client;
	
	/* atributo order foi mapeado pela classe OrderItemPk, para acessar é id.order
	é criado um HashSet pois queremos a lista de itens do pedido*/
	@OneToMany(mappedBy = "id.order") 
	private Set<OrderItem> items = new HashSet<>();
	
	/* relacionamento um para um com payment e cascadeall para que seja
	passado para a classe payment as alteraçoes feitas na classe order*/
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL) 
	private Payment payment;
	
	public Order() {
		
	}
	public Order(Long id, Instant instant, OrderStatus orderStatus, User client) {
		this.id = id;
		this.instant = instant;
		setOrderStatus(orderStatus);
		this.client = client;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Instant getInstant() {
		return instant;
	}
	public void setInstant(Instant instant) {
		this.instant = instant;
	}
	
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null)
		this.orderStatus = orderStatus.getCode();
	}
	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
	}
	
	public Double getTotal() {
		double sum = 0;
		for(OrderItem x:items) {
			sum += x.getSubTotal();
		}
		return sum;
	}
	
	public Set<OrderItem> getItems(){
		return items;
	}
	
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
