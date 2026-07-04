import React, { useState } from 'react';

export default function App() {
    const [usd, setUsd] = useState(1);
    return (
        <div style={{ padding: '20px' }}>
            <h2>USD to INR Converter</h2>
            <input type="number" value={usd} onChange={(e) => setUsd(e.target.value)} />
            <p>Value in INR: {usd * 83} INR</p>
        </div>
    );
}
