package kursevicius.karolis.dbforms.service;

import kursevicius.karolis.dbforms.bean.*;
import kursevicius.karolis.dbforms.docgen.FileGenerator;
import kursevicius.karolis.dbforms.karoliomodel.Car;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Value
//@Service
public class ReportServiceImpl /*implements ReportService*/ {
    /*CarService carService;
    ReportDataAssembler reportDataAssembler;
    FileGenerator reportGenerator;

    @Override
    public GenerateReportResult generateReport(GenerateReportRequest request) throws Exception {
        List<Car> cars = carService.searchCars(toSearchCarsRequest(request));
        ReportData reportData = reportDataAssembler.assembleReportData(cars);
        Map<String, ReportData> data = Map.of("data", reportData);
        GenerateRequest generateRequest = new GenerateRequest("car_report.html", data);
        GenerateResult generateResult = reportGenerator.generate(generateRequest);
        return new GenerateReportResult(generateResult.getContent());
    }

    private SearchCarsRequest toSearchCarsRequest(GenerateReportRequest request) {
        SearchCarsRequest result = new SearchCarsRequest();
        result.setRegistrationDateFrom(request.getRegistrationDateFrom());
        request.setRegistrationDateTo(request.getRegistrationDateTo());
        return result;
    }*/
}
