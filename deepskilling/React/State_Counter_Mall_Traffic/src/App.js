import React, { useState } from 'react';

export default function App() {
    const [count, setCount] = useState(0);
    return (
        <div style={{ padding: '20px' }}>
            <h2>Mall Visitors: {count}</h2>
            <button onClick={() => setCount(count + 1)}>Increment</button>
            <button onClick={() => setCount(count > 0 ? count - 1 : 0)}>Decrement</button>
        </div>
    );
}
