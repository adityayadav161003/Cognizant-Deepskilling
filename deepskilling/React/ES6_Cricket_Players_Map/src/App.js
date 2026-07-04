import React from 'react';

export default function App() {
    const players = [
        { name: 'Virat Kohli', runs: 12000 },
        { name: 'Rohit Sharma', runs: 9000 },
        { name: 'MS Dhoni', runs: 10000 }
    ];
    return (
        <div style={{ padding: '20px' }}>
            <h2>Indian Cricket Players</h2>
            {players.map((p, i) => (
                <p key={i}>Name: {p.name}, Runs: {p.runs}</p>
            ))}
        </div>
    );
}
