package com.ata.tacocloud.tacos.web;

import com.ata.tacocloud.tacos.data.entities.TacoOrder;
import com.ata.tacocloud.tacos.data.repos.OrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController (OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus) {
        if(errors.hasErrors()){
            return "orderForm";
        }
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
