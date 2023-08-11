package com.ata.tacocloud.tacos.data;

import com.ata.tacocloud.tacos.TacoOrder;

public interface OrderRepository {
    TacoOrder save (TacoOrder tacoOrder);
}
