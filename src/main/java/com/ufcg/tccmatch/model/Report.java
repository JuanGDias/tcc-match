package com.ufcg.tccmatch.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Report extends  AbstractEntity{
    
    private long idReporter;

    private String reporterName;

    private String report;

    private String reporterRole;

    @ManyToOne
    private TccReal tcc;

    public Report() {
    }

    public Report(long idReporter, String reporterName, String report, String reporterRole, TccReal tcc){
        this.idReporter = idReporter;
        this.reporterName = reporterName;
        this.report = report;
        this.reporterRole = reporterRole;
        this.tcc = tcc;
    }

    public long getIdReporter() {
        return idReporter;
    }

    public String getReport() {
        return report;
    }

    public String getReporterName() {
        return reporterName;
    }

    public String getReporterRole() {
        return reporterRole;
    }

    public void setIdReporter(long idReporter) {
        this.idReporter = idReporter;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public void setReporterRole(String reporterRole) {
        this.reporterRole = reporterRole;
    }

    public TccReal getTcc() {
        return tcc;
    }

    public void setTcc(TccReal tcc) {
        this.tcc = tcc;
    }
}
