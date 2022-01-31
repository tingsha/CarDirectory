package com.naname.carddirectory.web;

import com.naname.carddirectory.data.Car;
import com.naname.carddirectory.data.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Controller
@RequestMapping("/chart")
public class ChartController {

    private final CarsRepository carsRepository;

    @Autowired
    public ChartController(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @GetMapping
    public String getChart(Model model){
        String[] label = {"Sedan", "Pickup", "Truck"};

        PageRequest pageRequest = PageRequest.of(1, 1000);

        long[] point = {carsRepository.findCarsByBody("Sedan", pageRequest).getTotalElements(),
                carsRepository.findCarsByBody("Pickup", pageRequest).getTotalElements(),
                carsRepository.findCarsByBody("Truck", pageRequest).getTotalElements()};

        model.addAttribute("label", label);
        model.addAttribute("point", point);
        model.addAttribute("carsCount", LongStream.of(point).sum());
        model.addAttribute("firstCar", carsRepository.findMinimalDate().toString()
                .replace("T", " ")
                .replaceAll("\\.\\d+", ""));
        model.addAttribute("lastCar", carsRepository.findMaxDate().toString()
                .replace("T", " ")
                .replaceAll("\\.\\d+", ""));
        return "chart";
    }
}
