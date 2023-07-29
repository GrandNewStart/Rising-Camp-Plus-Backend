package com.ade.tinder.services.report;

import com.ade.tinder.BaseResponse;
import com.ade.tinder.services.user.UserRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ReportController implements  ReportService {
    @Override
    public BaseResponse<Object> getAllReportCategories() {
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("all report categories")
            .data(ReportRepository.shared.getReportCategories())
            .build();
    }

    @Override
    public BaseResponse<Object> getAllReports() {
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("all reports")
            .data(ReportRepository.shared.getReports())
            .build();
    }

    @Override
    public BaseResponse<Object> addNewReport(Map<String, Object> map) {
        int reporterId;
        int userId;
        int categoryId;
        String body;
        try {
            reporterId = (int) map.get("reporterId");
            userId = (int) map.get("userId");
            categoryId = (int) map.get("categoryId");
            body = (String) map.get("body");
        } catch (Exception e) {
            return BaseResponse.builder()
                .status(500)
                .message("FAILURE")
                .info("invalid parameters")
                .data(null)
                .build();
        }
        ReportRepository.shared.addNewReport(categoryId, reporterId, userId, body);
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("report successfully accepted")
            .data(null)
            .build();
    }

    @Override
    public BaseResponse<Object> cancelReport(Map<String, Object> map) {
        int reportId;
        int reporterId;
        try {
            reportId = (int) map.get("reportId");
            reporterId = (int) map.get("reporterId");
        } catch (Exception e) {
            return BaseResponse.builder()
                .status(500)
                .message("FAILURE")
                .info("invalid parameters")
                .data(null)
                .build();
        }
        try {
            ReportRepository.shared.cancelReport(reportId, reporterId);
        } catch (Exception e) {
            return BaseResponse.builder()
                .status(500)
                .message("FAILURE")
                .info(e.getMessage())
                .data(null)
                .build();
        }
        return BaseResponse.builder()
            .status(200)
            .message("SUCCESS")
            .info("report successfully cancelled")
            .data(null)
            .build();
    }


}
