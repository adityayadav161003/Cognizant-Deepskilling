import React from 'react';

export default function CalculateScore({ name, school, total, goal }) {
    const percent = ((total / 500) * 100).toFixed(2);
    return (
        <div className="format">
            <h1>Student Info</h1>
            <p>Name: {name}</p>
            <p>School: {school}</p>
            <p>Percent: {percent}%</p>
            <p>Goal Achieved: {total >= goal ? 'YES' : 'NO'}</p>
        </div>
    );
}
