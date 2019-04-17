package kursevicius.karolis.dbforms.controller;

import kursevicius.karolis.dbforms.bean.*;
import kursevicius.karolis.dbforms.karoliomodel.Brand;
import kursevicius.karolis.dbforms.karoliomodel.Car;
import kursevicius.karolis.dbforms.karoliomodel.CarModel;
import kursevicius.karolis.dbforms.karoliomodel.RentPlace;
import kursevicius.karolis.dbforms.service.CarModelService;
import kursevicius.karolis.dbforms.service.CarService;
import kursevicius.karolis.dbforms.service.RentPlaceService;
import kursevicius.karolis.dbforms.service.ReportService;
import lombok.Value;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.of;

@Value
@Controller
public class CarController {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    CarService carService;
    CarModelService carModelService;
    RentPlaceService rentPlaceService;
    ReportService reportService;

    @RequestMapping("/cars")
    public String getCarsPage(Model model) {
        List<CarDto> cars = carService.findAll().stream()
                .map(this::toCarDto)
                .collect(Collectors.toList());
        List<RentPlaceDto> rentPlaceDtos = rentPlaceService.findAll().stream()
                .map(this::toRentPlaceDto)
                .collect(Collectors.toList());
        rentPlaceDtos.add(0, new RentPlaceDto(null, ""));
        model.addAttribute("cars", cars);
        model.addAttribute("searchRequest", new SearchRequest("", "", "", ""));
        model.addAttribute("rentPlaces", rentPlaceDtos);
        return "car/cars";
    }

    @RequestMapping("/cars/{id}")
    public String getCarPage(Model model, @PathVariable(name = "id") Long id) {
        CarDto car = of(carService.getById(id)).map(this::toCarDto).get();
        model.addAttribute("car", car);
        return "car/view";
    }

    @RequestMapping("/cars/{id}/delete")
    public String deleteCar(@PathVariable(name = "id") Long id) {
        carService.delete(id);
        return "redirect:/index";
    }

    @RequestMapping("/cars/new")
    public String getNewCarForm(Model model) {
        List<CarModelDto> carModelDtos = carModelService.findAll().stream()
                .map(this::toCarModelDto)
                .collect(Collectors.toList());
        List<RentPlaceDto> rentPlaceDtos = rentPlaceService.findAll().stream()
                .map(this::toRentPlaceDto)
                .collect(Collectors.toList());
        model.addAttribute("car", new CarFormRequest("", "", "", "", ""));
        model.addAttribute("carModels", carModelDtos);
        model.addAttribute("rentPlaces", rentPlaceDtos);
        return "car/form";
    }

    @PostMapping("/cars")
    public String createCar(CarFormRequest carFormRequest) {
        Car car;
        if (StringUtils.isEmpty(carFormRequest.carId))
            car = carService.createCar(toCreateCarRequest(carFormRequest));
        else
            car = carService.updateCar(toUpdateCarRequest(carFormRequest));
        return "redirect:/cars/" + car.getId();
    }

    @RequestMapping("/cars/{id}/update")
    public String getUpdateCarForm(Model model, @PathVariable(name = "id") Long id) {
        List<CarModelDto> carModelDtos = carModelService.findAll().stream()
                .map(this::toCarModelDto)
                .collect(Collectors.toList());
        List<RentPlaceDto> rentPlaceDtos = rentPlaceService.findAll().stream()
                .map(this::toRentPlaceDto)
                .collect(Collectors.toList());
        Car car = carService.getById(id);
        CarFormRequest carFormRequest = toCarFormRequest(car);
        model.addAttribute("car", carFormRequest);
        model.addAttribute("carModels", carModelDtos);
        model.addAttribute("rentPlaces", rentPlaceDtos);
        return "car/form";
    }

    @PostMapping("/search-cars")
    public String search(Model model, SearchRequest searchRequest) {
        SearchCarsRequest searchCarsRequest = toSearchCarsRequest(searchRequest);
        List<CarDto> cars = carService.searchCars(searchCarsRequest).stream()
                .map(this::toCarDto)
                .collect(Collectors.toList());
        List<RentPlaceDto> rentPlaceDtos = rentPlaceService.findAll().stream()
                .map(this::toRentPlaceDto)
                .collect(Collectors.toList());
        rentPlaceDtos.add(0, new RentPlaceDto(null, ""));
        model.addAttribute("cars", cars);
        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("rentPlaces", rentPlaceDtos);
        return "car/cars";
    }

    @RequestMapping("/report")
    public String getReportPage(Model model) {
        model.addAttribute("reportRequest", new ReportRequest("", ""));
        return "car/report/report";
    }

    @PostMapping("/report-result")
    public String getReport(ReportRequest reportRequest) {
        return String.format(
                "redirect:/report/yearFrom=%s/yearTo=%s",
                reportRequest.registrationYearFrom,
                reportRequest.registrationYearTo);
    }

    @RequestMapping("/report/yearFrom={yearFrom}/yearTo={yearTo}")
    public void downloadReport(
            HttpServletResponse response,
            @PathVariable("yearFrom") String yearFrom,
            @PathVariable("yearTo") String yearTo) throws Exception {
        GenerateReportRequest generateReportRequest = new GenerateReportRequest();
        generateReportRequest.setRegistrationDateFrom(registrationYearFromToDate(yearFrom));
        generateReportRequest.setRegistrationDateTo(registrationYearToToDate(yearTo));
        GenerateReportResult generateReportResult = reportService.generateReport(generateReportRequest);
        InputStream is = new ByteArrayInputStream(generateReportResult.getContent());
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
        is.close();
    }

    private Date registrationYearFromToDate(String year) {
        if (StringUtils.isEmpty(year))
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.valueOf(year), Calendar.JANUARY, 1);
        return calendar.getTime();
    }

    private Date registrationYearToToDate(String year) {
        if (StringUtils.isEmpty(year))
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.valueOf(year) + 1, Calendar.JANUARY, 1);
        return calendar.getTime();
    }

    private RentPlaceDto toRentPlaceDto(RentPlace rentPlace) {
        return new RentPlaceDto(String.valueOf(rentPlace.getId()), rentPlace.getName());
    }

    private SearchCarsRequest toSearchCarsRequest(SearchRequest searchRequest) {
        SearchCarsRequest result = new SearchCarsRequest();
        result.setPlateNumber(searchRequest.plateNumber);
        String rentPlaceId = searchRequest.rentPlaceId;
        result.setRentPlaceId(StringUtils.isEmpty(rentPlaceId) ? null : Long.valueOf(rentPlaceId));
        result.setRegistrationDateFrom(parseDate(searchRequest.registrationDateFrom));
        result.setRegistrationDateTo(parseDate(searchRequest.registrationDateTo));
        return result;
    }

    private CarFormRequest toCarFormRequest(Car car) {
        return new CarFormRequest(
                car.getId().toString(),
                car.getPlateNumber(),
                toDateString(car.getRegistrationDate()),
                car.getRentPlace().getId().toString(),
                car.getCarModel().getId().toString());
    }

    private String toDateString(Date date) {
        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }

    private CreateCarRequest toCreateCarRequest(CarFormRequest carFormRequest) {
        CreateCarRequest result = new CreateCarRequest();
        String carModelId = carFormRequest.carModelId;
        result.setCarModelId(carModelId == null ? null : Long.valueOf(carModelId));
        result.setPlateNumber(carFormRequest.plateNumber);
        String rentPlaceId = carFormRequest.rentPlaceId;
        result.setRentPlaceId(rentPlaceId == null ? null : Long.valueOf(rentPlaceId));
        result.setRegistrationDate(parseDate(carFormRequest.registrationDate));
        return result;
    }

    private UpdateCarRequest toUpdateCarRequest(CarFormRequest carFormRequest) {
        UpdateCarRequest result = new UpdateCarRequest();
        String carId = carFormRequest.carId;
        result.setCarId(StringUtils.isEmpty(carId) ? null : Long.valueOf(carId));
        String carModelId = carFormRequest.carModelId;
        result.setCarModelId(carModelId == null ? null : Long.valueOf(carModelId));
        result.setPlateNumber(carFormRequest.plateNumber);
        String rentPlaceId = carFormRequest.rentPlaceId;
        result.setRentPlaceId(rentPlaceId == null ? null : Long.valueOf(rentPlaceId));
        result.setRegistrationDate(parseDate(carFormRequest.registrationDate));
        return result;
    }

    private Date parseDate(String date) {
        if (StringUtils.isEmpty(date))
            return null;
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private CarDto toCarDto(Car car) {
        RentPlace rentPlace = car.getRentPlace();
        CarModel carModel = car.getCarModel();
        Brand brand = carModel.getBrand();
        return new CarDto(
                String.valueOf(car.getId()),
                toDateString(car.getRegistrationDate()),
                car.getPlateNumber(),
                String.valueOf(carModel.getId()),
                carModel.getName(),
                String.valueOf(brand.getId()),
                brand.getName(),
                String.valueOf(rentPlace.getId()),
                rentPlace.getName(),
                rentPlace.getAddress());
    }

    private CarModelDto toCarModelDto(CarModel carModel) {
        Brand brand = carModel.getBrand();
        return new CarModelDto(
                String.valueOf(carModel.getId()),
                carModel.getName(),
                String.valueOf(brand.getId()),
                brand.getName());
    }

    @Value
    private class CarFormRequest {
        String carId;
        String plateNumber;
        String registrationDate;
        String rentPlaceId;
        String carModelId;
    }

    @Value
    private class SearchRequest {
        String registrationDateFrom;
        String registrationDateTo;
        String rentPlaceId;
        String plateNumber;
    }

    @Value
    private class ReportRequest {
        String registrationYearFrom;
        String registrationYearTo;
    }
}
