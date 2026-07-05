import React from 'react';

export default function App() {
    const blogs = [
        { id: 1, title: 'React Basics', content: 'Intro to React components.' },
        { id: 2, title: 'State and Props', content: 'Managing data.' }
    ];
    return (
        <div style={{ padding: '20px' }}>
            <h2>My Blog</h2>
            {blogs.map(b => (
                <div key={b.id}>
                    <h3>{b.title}</h3>
                    <p>{b.content}</p>
                </div>
            ))}
        </div>
    );
}
