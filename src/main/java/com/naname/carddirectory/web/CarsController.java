package com.naname.carddirectory.web;

import com.naname.carddirectory.data.Car;
import com.naname.carddirectory.data.CarsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping(value = "/cars")
public class CarsController {

    private final CarsRepository carsRepository;
    private final FieldValidator fieldValidator;

    @Autowired
    public CarsController(CarsRepository carsRepository, FieldValidator fieldValidator){
        this.carsRepository = carsRepository;
        this.fieldValidator = fieldValidator;
    }

    @GetMapping
    @Cacheable("allCars")
    public String getCars(@RequestParam(name = "page") int pageNumber, @RequestParam(name="body", required = false) String body, Model model){
        model.addAttribute("car", new Car());
        Page<Car> cars;
        PageRequest pageRequest = PageRequest.of(pageNumber-1, 11, Sort.by(Sort.Direction.DESC, "date"));
        if (body == null)
            cars = carsRepository.findAll(pageRequest);
        else
            switch (body){
                case "sedan":
                    cars = carsRepository.findCarsByBody("Sedan", pageRequest);
                    break;
                case "truck":
                    cars = carsRepository.findCarsByBody("Truck", pageRequest);
                    break;
                case "pickup":
                    cars = carsRepository.findCarsByBody("Pickup", pageRequest);
                    break;
                default:
                    cars = carsRepository.findAll(pageRequest);
            }
        model.addAttribute("cars", cars);
        model.addAttribute("isLastPage", !cars.hasNext());
        model.addAttribute("isFirstPage", !cars.hasPrevious());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageCount", cars.getTotalPages() == 0 ? 1 : cars.getTotalPages());
        model.addAttribute("canAdd", !cars.hasPrevious());
        return "index";
    }

    @PostMapping(value = "/search")
    public String redirectToCar(@RequestBody MultiValueMap <String, String> body){
        String url = "/cars/" + URLEncoder.encode(body.get("number").get(0), StandardCharsets.UTF_8);
        return "redirect:" + url;
    }

    @GetMapping("/{number}")
    @Cacheable("cars")
    public String getCarByNumber(@PathVariable(name = "number") String number, Model model){
        log.info("getting car by number: {}", number);
        Optional<Car> car = carsRepository.findById(number);
        if (car.isPresent()){
            model.addAttribute("cars", car.get());
            model.addAttribute("canAdd", false);
            model.addAttribute("isFirstPage", true);
            model.addAttribute("isLastPage", true);
            model.addAttribute("pageNumber", 1);
            model.addAttribute("pageCount", 1);
            return "index";
        } else
            throw new CarNotFoundException(number);
    }

    @PostMapping
    public @ResponseBody Map<String, Boolean> validateForms(@RequestBody Car car){
        log.info(car.toString());

        Map<String, Boolean> json = new HashMap<>();
        json.put("isNumberValid", fieldValidator.checkCarNumber(car.getNumber()));
        json.put("isYearValid", fieldValidator.checkYear(String.valueOf(car.getYear())));

        if (carsRepository.findById(car.getNumber()).isPresent())
            json.put("isUnique", false);
        else
            json.put("isUnique", true);

        boolean isAllFieldsValid = true;
        for (Map.Entry<String, Boolean> entry : json.entrySet()) {
            log.info(entry.getKey() + " " + entry.getValue());
            if (!entry.getValue())
                isAllFieldsValid = false;
        }

        if (isAllFieldsValid){
            carsRepository.save(car);
        }
        return json;
    }

    @DeleteMapping("/{number}")
    @CacheEvict("cars")
    public String putCar(@PathVariable(name = "number") String number){
        Optional<Car> car = carsRepository.findById(number);
        if (car.isPresent()){
            carsRepository.deleteById(number);
            return "redirect:/";
        } else
            throw new CarNotFoundException(number);
    }
}
