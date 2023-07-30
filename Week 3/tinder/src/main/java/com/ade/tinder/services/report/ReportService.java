package com.ade.tinder.services.report;

import com.ade.tinder.config.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface ReportService {
    @GetMapping("/report-category/all")
    BaseResponse<Object> getAllReportCategories();
    @GetMapping("/report/all")
    BaseResponse<Object> getAllReports();
    @PostMapping("/report")
    BaseResponse<Object> addNewReport(@RequestBody Map<String, Object> map);
    @DeleteMapping("/report")
    BaseResponse<Object> cancelReport(@RequestBody Map<String, Object> map);
}
