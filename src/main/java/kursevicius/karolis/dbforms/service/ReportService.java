package kursevicius.karolis.dbforms.service;

import kursevicius.karolis.dbforms.bean.GenerateReportRequest;
import kursevicius.karolis.dbforms.bean.GenerateReportResult;

public interface ReportService {
    GenerateReportResult generateReport(GenerateReportRequest request) throws Exception;
}
