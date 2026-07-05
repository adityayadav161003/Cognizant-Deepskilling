import React, { useEffect, useState } from 'react';

export default function App() {
    const [user, setUser] = useState(null);

    useEffect(() => {
        fetch('https://randomuser.me/api/')
            .then(res => res.json())
            .then(data => setUser(data.results[0]));
    }, []);

    return (
        <div style={{ padding: '20px' }}>
            {user ? (
                <div>
                    <h3>{user.name.first} {user.name.last}</h3>
                    <p>Email: {user.email}</p>
                </div>
            ) : <p>Loading user...</p>}
        </div>
    );
}
