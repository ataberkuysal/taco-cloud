package com.ata.tacocloud.tacos.web;

import com.ata.tacocloud.tacos.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    @GetMapping("/current")
    public String orderForm(@ModelAttribute TacoOrder tacoOrder, Model model) {
        /*model.addAttribute("tacoOrder", new TacoOrder());*/
        return "orderForm";
    }
    /*
    @ModelAttribute
    public TacoOrder getTacoOrder(){
        return new TacoOrder();
    }
    */
    @PostMapping
    public String processOrder(TacoOrder order,
                               SessionStatus sessionStatus, Errors errors) {
        if(errors.hasErrors()){
            return "orderForm";
        }
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
