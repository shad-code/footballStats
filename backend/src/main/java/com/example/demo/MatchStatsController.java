package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class MatchStatsController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MatchStatsRepository matchStatsRepository;

    @PostMapping("/createMatch")
    public Map<String, Object> createMatch(@RequestBody Match match) {
        if (match.getName() == null || match.getName().trim().isEmpty()) {
            return Map.of("error", "Match name is required");
        }

        if (matchRepository.findByName(match.getName()) != null) {
            return Map.of("error", "Match already exists with name = " + match.getName());
        }

        Match saved = matchRepository.save(match);
        return Map.of("message", "Match created", "id", saved.getId());
    }

    @PostMapping("/addMatchStats")
    public Map<String, Object> addMatchStats(@RequestBody MatchStats stats) {
        if (stats.getMatch() == null || stats.getMatch().getId() == null) {
            return Map.of("error", "Match ID is required in the request");
        }

        Long matchPkId = stats.getMatch().getId();
        Optional<Match> optionalMatch = matchRepository.findById(matchPkId);
        if (optionalMatch.isEmpty()) {
            return Map.of("error", "Match not found with ID = " + matchPkId);
        }
        Match match = optionalMatch.get();

        Optional<MatchStats> existing = matchStatsRepository.findByMatch_Id(matchPkId);
        MatchStats toSave;

        if (existing.isPresent()) {
            MatchStats existingStats = existing.get();

            // Team A stats
            existingStats.setTeamA_goals(stats.getTeamA_goals());
            existingStats.setTeamA_passes(stats.getTeamA_passes());
            existingStats.setTeamA_possession(stats.getTeamA_possession());
            existingStats.setTeamA_shots(stats.getTeamA_shots());
            existingStats.setTeamA_shotsOnTarget(stats.getTeamA_shotsOnTarget());
            existingStats.setTeamA_corners(stats.getTeamA_corners());

            // Team B stats
            existingStats.setTeamB_goals(stats.getTeamB_goals());
            existingStats.setTeamB_passes(stats.getTeamB_passes());
            existingStats.setTeamB_possession(stats.getTeamB_possession());
            existingStats.setTeamB_shots(stats.getTeamB_shots());
            existingStats.setTeamB_shotsOnTarget(stats.getTeamB_shotsOnTarget());
            existingStats.setTeamB_corners(stats.getTeamB_corners());

            toSave = existingStats;
        } else {
            stats.setMatch(match);
            toSave = stats;
        }

        match.setStats(toSave); // for bidirectional reference
        matchRepository.save(match); // saves both match and stats due to cascade

        return buildResponse(match);
    }

    @GetMapping("/matchStats/{name}")
    public Map<String, Object> getStats(@PathVariable String name) {
        Match match = matchRepository.findByName(name);
        if (match == null || match.getStats() == null) {
            return Map.of("error", "Match or stats not found");
        }

        return buildResponse(match);
    }

    @GetMapping("/matches")
    public List<Map<String, Object>> getAllMatches() {
        List<Match> matches = matchRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Match match : matches) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", match.getId());
            map.put("name", match.getName());
            map.put("date", match.getDate());
            result.add(map);
        }
        return result;
    }

    private Map<String, Object> buildResponse(Match match) {
        MatchStats stats = match.getStats();

        Map<String, String> teamA = Map.of(
                "goals", stats.getTeamA_goals(),
                "possession", stats.getTeamA_possession(),
                "passes", stats.getTeamA_passes(),
                "shots", stats.getTeamA_shots(),
                "shotsOnTarget", stats.getTeamA_shotsOnTarget(),
                "corners", stats.getTeamA_corners()
        );

        Map<String, String> teamB = Map.of(
                "goals", stats.getTeamB_goals(),
                "possession", stats.getTeamB_possession(),
                "passes", stats.getTeamB_passes(),
                "shots", stats.getTeamB_shots(),
                "shotsOnTarget", stats.getTeamB_shotsOnTarget(),
                "corners", stats.getTeamB_corners()
        );

        return Map.of(
                "name", match.getName(),
                "date", match.getDate(),
                "teamA", teamA,
                "teamB", teamB
        );
    }
}