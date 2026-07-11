# React Development Best Practices Guide

Core concepts, patterns, and design recommendations for building clean, maintainable, and highly-performant React applications.

## 1. Rules of Hooks
Always follow the two strict rules of React Hooks to prevent state synchronization issues and components rendering unexpectedly:
- **Top-Level Invocation**: Only call hooks at the top level of functional components. Never put hooks inside loops, condition blocks, or nested functions.
- **React Scope**: Only call hooks from React function components or from custom hooks.

### Recommended Code Pattern
```jsx
import React, { useState, useEffect } from 'react';

function UserProfile({ userId }) {
  // Correct: Hooks called at the absolute top-level
  const [profile, setProfile] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    let active = true;
    async function loadData() {
      setLoading(true);
      const data = await fetchUserData(userId);
      if (active) {
        setProfile(data);
        setLoading(false);
      }
    }
    loadData();
    return () => { active = false; }; -- cleanup keeps component free of memory leaks
  }, [userId]);

  if (loading) return <p>Loading Profile...</p>;
  return <div>{profile.name}</div>;
}
```

## 2. Lists and Keys (Optimizing Rendering)
When mapping arrays to list elements, always pass a stable, unique identifier as the `key` prop. Never use the array index as the key when items inside the list can be reordered, deleted, or inserted dynamically.

### Recommended Code Pattern
```jsx
// Correct: Use a unique record ID
const listItems = tasks.map((task) =>
  <li key={task.id}>
    {task.title}
  </li>
);
```

## 3. Context API (State Scope)
Use the Context API to distribute global states (like selected themes, locales, or user authentications) across deep component trees without manual props tunneling.

### Recommended Code Pattern
```jsx
import React, { createContext, useContext, useState } from 'react';

const ThemeContext = createContext();

export function ThemeProvider({ children }) {
  const [theme, setTheme] = useState('light');
  const toggleTheme = () => setTheme(prev => prev === 'light' ? 'dark' : 'light');
  
  return (
    <ThemeContext.Provider value={{ theme, toggleTheme }}>
      {children}
    </ThemeContext.Provider>
  );
}

export const useTheme = () => useContext(ThemeContext);
```

## 4. Separation of Concerns
Keep components clean by separating UI presentation from state/data fetching logic. Custom hooks are an excellent pattern to package data retrieval operations away from HTML presentations.
