package com.ade.tinder.services.report.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Report {
    private int id;
    private int categoryId;
    private int reporterId;
    private int userId;
    private String reason;
    private String createAt;
    private boolean cancelled;
}
