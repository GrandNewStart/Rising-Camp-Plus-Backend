package com.ade.tinder.services.report;

import com.ade.tinder.config.BaseResponse;
import com.ade.tinder.config.BaseResponseStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController("/reports")
public class ReportController {

    private final ReportRepository reportRepository;

    private final ReportCategoryRepository reportCategoryRepository;

    public ReportController(ReportRepository reportRepository, ReportCategoryRepository reportCategoryRepository) {
        this.reportRepository = reportRepository;
        this.reportCategoryRepository = reportCategoryRepository;
    }

    @GetMapping("/categories")
    public BaseResponse<List<ReportCategory>> getAllReportCategories() {
        return new BaseResponse<>(this.reportCategoryRepository.findAll());
    }

    @GetMapping("")
    public BaseResponse<List<Report>> getAllReports() {
        return new BaseResponse<>(this.reportRepository.findAll());
    }

    @PostMapping("")
    public BaseResponse<Object> addNewReport(@RequestBody Report report) {
        if (report.getCategory() == null || report.getReporter() == null || report.getTargetUser() == null) {
            return new BaseResponse<>(BaseResponseStatus.INVALID_REQUEST);
        }
        report.setCreatedAt(LocalDateTime.now());
        this.reportRepository.save(report);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @DeleteMapping("")
    public BaseResponse<Object> cancelReport(@RequestBody Report report) {
        this.reportRepository.delete(report);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }


}
