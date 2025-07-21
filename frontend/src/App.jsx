import React, { useState } from "react";
import "./App.css";
import footballImg from "./assets/soccer-1678992.jpg";

const API_BASE = "http://localhost:8080";

function App() {
  // State for creating a match
  const [name, setName] = useState("");
  const [date, setDate] = useState("");
  const [createMsg, setCreateMsg] = useState("");

  // State for adding stats
  const [matchPkId, setMatchPkId] = useState("");
  const [teamA, setTeamA] = useState({ goals: "", possession: "", passes: "", shots: "", shotsOnTarget: "", corners: "" });
  const [teamB, setTeamB] = useState({ goals: "", possession: "", passes: "", shots: "", shotsOnTarget: "", corners: "" });
  const [addStatsMsg, setAddStatsMsg] = useState("");

  // State for fetching stats
  const [matches, setMatches] = useState([]);
  const [fetchedStats, setFetchedStats] = useState(null);
  const [fetchError, setFetchError] = useState("");
  const [showMatches, setShowMatches] = useState(false);

  // Create Match handler
  const handleCreateMatch = async (e) => {
    e.preventDefault();
    setCreateMsg("");
    try {
      const res = await fetch(`${API_BASE}/createMatch`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, date }),
      });
      const data = await res.json();
      setCreateMsg(data.message || data.error);
      if (data.id) setMatchPkId(data.id);
    } catch (err) {
      setCreateMsg("Error creating match");
    }
  };

  // Add Match Stats handler
  const handleAddStats = async (e) => {
    e.preventDefault();
    setAddStatsMsg("");
    try {
      const res = await fetch(`${API_BASE}/addMatchStats`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          match: { id: matchPkId },
          teamA_goals: teamA.goals,
          teamA_possession: teamA.possession,
          teamA_passes: teamA.passes,
          teamA_shots: teamA.shots,
          teamA_shotsOnTarget: teamA.shotsOnTarget,
          teamA_corners: teamA.corners,
          teamB_goals: teamB.goals,
          teamB_possession: teamB.possession,
          teamB_passes: teamB.passes,
          teamB_shots: teamB.shots,
          teamB_shotsOnTarget: teamB.shotsOnTarget,
          teamB_corners: teamB.corners,
        }),
      });
      const data = await res.json();
      if (data.error) {
        setAddStatsMsg(data.error);
      } else {
        setAddStatsMsg("Stats added!");
      }
    } catch (err) {
      setAddStatsMsg("Error adding stats");
    }
  };

  // Fetch all matches handler
  const handleFetchMatches = async () => {
    setShowMatches(true);
    setFetchedStats(null);
    setFetchError("");
    try {
      const res = await fetch(`${API_BASE}/matches`);
      const data = await res.json();
      setMatches(data);
    } catch (err) {
      setFetchError("Error fetching matches");
    }
  };

  // Fetch stats for a selected match
  const handleFetchStatsForMatch = async (name) => {
    setFetchedStats(null);
    setFetchError("");
    try {
      const res = await fetch(`${API_BASE}/matchStats/${encodeURIComponent(name)}`);
      const data = await res.json();
      if (data.error) {
        setFetchError(data.error);
      } else {
        setFetchedStats(data);
      }
    } catch (err) {
      setFetchError("Error fetching stats");
    }
  };

  return (
    <div className="container">
      <h1>Football Match Stats</h1>
      <section>
        <h2> Match Stats</h2>
        <button onClick={handleFetchMatches}>Show Matches</button>
        {fetchError && <p>{fetchError}</p>}
        {showMatches && matches.length > 0 && (
          <div className="matches-list">
            <h3>Matches</h3>
            <ul>
              {matches.map((m) => (
                <li key={m.id}>
                  <button onClick={() => handleFetchStatsForMatch(m.name)}>{m.name} ({m.date})</button>
                </li>
              ))}
            </ul>
          </div>
        )}
        {fetchedStats && (
          <div className="stats-display">
            {(() => {
              const nameParts = fetchedStats.name ? fetchedStats.name.split(" ") : ["Team 1", "Team 2"];
              const team1 = nameParts[0] || "Team 1";
              const team2 = nameParts[2] || nameParts[1] || "Team 2";
              return (
                <table className="stats-table">
                  <thead>
                    <tr>
                      <th>Date</th>
                      <th colSpan="2">{fetchedStats.date}</th>
                    </tr>
                    <tr>
                      <td></td>
                      <td>{team1}</td>
                      <td>{team2}</td>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>Goals</td>
                      <td>{fetchedStats.teamA.goals}</td>
                      <td>{fetchedStats.teamB.goals}</td>
                    </tr>
                    <tr>
                      <td>Possession</td>
                      <td>{fetchedStats.teamA.possession}</td>
                      <td>{fetchedStats.teamB.possession}</td>
                    </tr>
                    <tr>
                      <td>Total Passes</td>
                      <td>{fetchedStats.teamA.passes}</td>
                      <td>{fetchedStats.teamB.passes}</td>
                    </tr>
                    <tr>
                      <td>Shots</td>
                      <td>{fetchedStats.teamA.shots}</td>
                      <td>{fetchedStats.teamB.shots}</td>
                    </tr>
                    <tr>
                      <td>Shots on target</td>
                      <td>{fetchedStats.teamA.shotsOnTarget}</td>
                      <td>{fetchedStats.teamB.shotsOnTarget}</td>
                    </tr>
                    <tr>
                      <td>Corners</td>
                      <td>{fetchedStats.teamA.corners}</td>
                      <td>{fetchedStats.teamB.corners}</td>
                    </tr>
                  </tbody>
                </table>
              );
            })()}
          </div>
        )}
      </section>

      <section>
        <h2>Create Match</h2>
        <form onSubmit={handleCreateMatch}>
          <input
            type="text"
            placeholder="Match Name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
          <input
            type="date"
            value={date}
            onChange={(e) => setDate(e.target.value)}
            required
          />
          <button type="submit">Create Match</button>
        </form>
        {createMsg && <p>{createMsg}</p>}
      </section>

      <section>
        <h2>Add Match Stats</h2>
        <form onSubmit={handleAddStats}>
          <div className="teams">
            <div>
              <h3>Team A</h3>
              {Object.keys(teamA).map((key) => (
                <input
                  key={key}
                  type="text"
                  placeholder={key.charAt(0).toUpperCase() + key.slice(1)}
                  value={teamA[key]}
                  onChange={(e) => setTeamA({ ...teamA, [key]: e.target.value })}
                  required
                />
              ))}
            </div>
            <div>
              <h3>Team B</h3>
              {Object.keys(teamB).map((key) => (
                <input
                  key={key}
                  type="text"
                  placeholder={key.charAt(0).toUpperCase() + key.slice(1)}
                  value={teamB[key]}
                  onChange={(e) => setTeamB({ ...teamB, [key]: e.target.value })}
                  required
                />
              ))}
            </div>
          </div>
          <button type="submit">Add Stats</button>
        </form>
        {addStatsMsg && <p>{addStatsMsg}</p>}
      </section>

      
    </div>
  );
}

export default App;
