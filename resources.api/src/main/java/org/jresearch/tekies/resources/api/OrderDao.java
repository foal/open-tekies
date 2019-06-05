package org.jresearch.tekies.resources.api;

import org.jresearch.tekies.domain.ItemOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<ItemOrder, Long> {

	public Page<ItemOrder> findAllByCustomer(long customerId, Pageable pageable);

}
