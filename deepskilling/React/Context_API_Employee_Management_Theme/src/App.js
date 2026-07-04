import React, { createContext, useContext, useState } from 'react';

const ThemeContext = createContext();

export default function App() {
    const [theme, setTheme] = useState('light');
    return (
        <ThemeContext.Provider value={{ theme, setTheme }}>
            <Child />
        </ThemeContext.Provider>
    );
}

function Child() {
    const { theme, setTheme } = useContext(ThemeContext);
    return (
        <div style={{ background: theme === 'light' ? '#fff' : '#333', color: theme === 'light' ? '#000' : '#fff', padding: '20px' }}>
            <h2>Current Theme: {theme}</h2>
            <button onClick={() => setTheme(theme === 'light' ? 'dark' : 'light')}>Toggle Theme</button>
        </div>
    );
}
