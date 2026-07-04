import React, { useState } from 'react';

export default function App() {
    const [issue, setIssue] = useState('');
    return (
        <div style={{ padding: '20px' }}>
            <h2>Raise Ticket</h2>
            <form onSubmit={(e) => { e.preventDefault(); alert('Ticket Raised: ' + issue); }}>
                <textarea value={issue} onChange={(e) => setIssue(e.target.value)} placeholder="Describe your issue" />
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}
