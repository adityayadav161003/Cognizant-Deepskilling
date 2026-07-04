import React, { useState } from 'react';

export default function App() {
    const [loggedIn, setLoggedIn] = useState(false);
    return (
        <div style={{ padding: '20px' }}>
            {loggedIn ? (
                <div>
                    <h3>Welcome Resident, Book Event Tickets</h3>
                    <button onClick={() => setLoggedIn(false)}>Logout</button>
                </div>
            ) : (
                <button onClick={() => setLoggedIn(true)}>Login to Book Tickets</button>
            )}
        </div>
    );
}
