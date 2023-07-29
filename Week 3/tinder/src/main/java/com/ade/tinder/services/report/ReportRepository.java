package com.ade.tinder.services.report;

import com.ade.tinder.services.report.models.Report;
import com.ade.tinder.services.report.models.ReportCategory;
import com.ade.tinder.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ReportRepository {

    public static ReportRepository shared = new ReportRepository();

    private ReportRepository() {}

    private List<Report> reports = Arrays.asList(
            new Report(1, 1, 3, 4, "지가 차은우래여.", "2023-01-01-00:00:00", false),
            new Report(2, 2, 8, 7, "계속 잡코인 홍보해요.", "2023-01-01-00:00:00", false),
            new Report(3, 3, 7, 10, "시발을 입에 달고 사네요.", "2023-01-01-00:00:00", false),
            new Report(4, 4, 18, 15, "성적 수치심을 일으켰어요.", "2023-01-01-00:00:00", false)
    );
    private int nextReportId = 5;
    public void addNewReport(int categoryId, int reporterId, int userId, String body) {
        ArrayList<Report> list = new ArrayList<>(this.reports);
        list.add(new Report(this.nextReportId, categoryId, userId, reporterId, body, DateUtils.getDate(), false));
        this.reports = list;
        this.nextReportId += 1;
    }
    public void cancelReport(int reportId, int reporterId) throws Exception {
        ArrayList<Report> list = new ArrayList<>(this.reports);
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).getId() != reportId) continue;
            if (list.get(i).getReporterId() != reporterId) {
                throw new Exception("invalid userId");
            }
            Report updatedReport = list.get(i);
            updatedReport.setCancelled(true);
            list.set(i, updatedReport);
            return;
        }
        throw new Exception("report not found");
    }

    private List<ReportCategory> reportCategories = Arrays.asList(
            new ReportCategory(1, "도용/사칭"),
            new ReportCategory(2, "금전요구/투자종용"),
            new ReportCategory(3, "폭력/언어폭력"),
            new ReportCategory(4, "성희롱/성폭력"),
            new ReportCategory(5, "기타")
    );
}
