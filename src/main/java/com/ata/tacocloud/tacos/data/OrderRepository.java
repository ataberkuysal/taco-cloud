package com.ata.tacocloud.tacos.data;

import com.ata.tacocloud.tacos.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository
extends CrudRepository<TacoOrder, String> {
}
