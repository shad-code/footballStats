package com.example.demo;

import jakarta.persistence.*;

@Entity
public class MatchStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // Team A Stats
    @Column
    private String teamA_goals;
    private String teamA_possession;
    private String teamA_passes;
    private String teamA_shots;
    private String teamA_shotsOnTarget;
    private String teamA_corners;

    // Team B Stats
    private String teamB_goals;
    private String teamB_possession;
    private String teamB_passes;
    private String teamB_shots;
    private String teamB_shotsOnTarget;
    private String teamB_corners;

    @OneToOne
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private Match match;

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    // Getters and setters for all fields (teamA_*, teamB_*) below

    public String getTeamA_goals() { return teamA_goals; }
    public void setTeamA_goals(String teamA_goals) { this.teamA_goals = teamA_goals; }

    public String getTeamA_possession() { return teamA_possession; }
    public void setTeamA_possession(String teamA_possession) { this.teamA_possession = teamA_possession; }

    public String getTeamA_passes() { return teamA_passes; }
    public void setTeamA_passes(String teamA_passes) { this.teamA_passes = teamA_passes; }

    public String getTeamA_shots() { return teamA_shots; }
    public void setTeamA_shots(String teamA_shots) { this.teamA_shots = teamA_shots; }

    public String getTeamA_shotsOnTarget() { return teamA_shotsOnTarget; }
    public void setTeamA_shotsOnTarget(String teamA_shotsOnTarget) { this.teamA_shotsOnTarget = teamA_shotsOnTarget; }

    public String getTeamA_corners() { return teamA_corners; }
    public void setTeamA_corners(String teamA_corners) { this.teamA_corners = teamA_corners; }

    public String getTeamB_goals() { return teamB_goals; }
    public void setTeamB_goals(String teamB_goals) { this.teamB_goals = teamB_goals; }

    public String getTeamB_possession() { return teamB_possession; }
    public void setTeamB_possession(String teamB_possession) { this.teamB_possession = teamB_possession; }

    public String getTeamB_passes() { return teamB_passes; }
    public void setTeamB_passes(String teamB_passes) { this.teamB_passes = teamB_passes; }

    public String getTeamB_shots() { return teamB_shots; }
    public void setTeamB_shots(String teamB_shots) { this.teamB_shots = teamB_shots; }

    public String getTeamB_shotsOnTarget() { return teamB_shotsOnTarget; }
    public void setTeamB_shotsOnTarget(String teamB_shotsOnTarget) { this.teamB_shotsOnTarget = teamB_shotsOnTarget; }

    public String getTeamB_corners() { return teamB_corners; }
    public void setTeamB_corners(String teamB_corners) { this.teamB_corners = teamB_corners; }
}
