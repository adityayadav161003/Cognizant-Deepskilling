import React, { useState } from 'react';
import axios from 'axios';

export default function App() {
    const [repos, setRepos] = useState([]);
    
    const fetchRepos = () => {
        axios.get('https://api.github.com/users/octocat/repos')
            .then(res => setRepos(res.data));
    };

    return (
        <div style={{ padding: '20px' }}>
            <button onClick={fetchRepos}>Fetch Github Repos</button>
            <ul>{repos.map(r => <li key={r.id}>{r.name}</li>)}</ul>
        </div>
    );
}
