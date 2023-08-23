package com.ata.tacocloud.tacos.data.repos;

import com.ata.tacocloud.tacos.data.entities.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository
extends CrudRepository<TacoOrder, String> {
}
