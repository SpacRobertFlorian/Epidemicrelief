package eu.accesa.internship.epidemicrelief.controller;

import eu.accesa.internship.epidemicrelief.model.DeliveryDateThreshold;
import eu.accesa.internship.epidemicrelief.repository.DeliveryDateThresholdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/configuration")
public class DeliveryDateThresholdController {
    private final DeliveryDateThresholdRepository repository;

    public DeliveryDateThresholdController(DeliveryDateThresholdRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/deliverydatethreshold")
    public String index(Model model) {
        Iterable<DeliveryDateThreshold> threshold = repository.findAll();
        model.addAttribute("deliveryDateThreashold", threshold.iterator().next());

        return "date_delivered_threshold";
    }

    @PostMapping("/deliverydatethreshold")
    public String editThreshold(@Valid DeliveryDateThreshold threshold) {
        Optional<DeliveryDateThreshold> databaseThreshold = repository.findById(1L);
        if (databaseThreshold.isPresent()) {
            databaseThreshold.get().setDeliveryDateThreshold(threshold.getDeliveryDateThreshold());
            repository.save(databaseThreshold.get());
        }

        return "redirect:/configuration/deliverydatethreshold";
    }
}
