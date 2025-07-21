package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchStatsRepository extends JpaRepository<MatchStats, Long> {
    Optional<MatchStats> findByMatch_Name(String name);

    Optional<MatchStats> findByMatch_Id(Long matchPkId);
}
