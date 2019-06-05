package org.jresearch.tekies.resources.api;

import org.jresearch.tekies.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDao extends JpaRepository<Item, Long> {
//Fix DAO parameters
}
