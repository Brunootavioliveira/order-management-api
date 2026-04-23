package br.com.fiap.CheckPoint1.repository;

import br.com.fiap.CheckPoint1.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
