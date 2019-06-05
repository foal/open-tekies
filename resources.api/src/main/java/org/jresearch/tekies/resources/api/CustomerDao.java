package org.jresearch.tekies.resources.api;

import org.jresearch.tekies.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, Long> {
	// fix DAO parameters
}
