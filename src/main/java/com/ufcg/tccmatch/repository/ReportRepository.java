package com.ufcg.tccmatch.repository;

import com.ufcg.tccmatch.model.Report;

import java.util.Optional;

public interface ReportRepository extends AbstractRepository<Report> {
    Optional<Report> findByReporterRole(String reporterRole);
}
