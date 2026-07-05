import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';

const Trainers = () => <ul><li>John Doe (Spring Core)</li><li>Jane Smith (React)</li></ul>;

export default function App() {
    return (
        <Router>
            <div>
                <nav>
                    <Link to="/trainers">Trainers List</Link>
                </nav>
                <Routes>
                    <Route path="/trainers" element={<Trainers />} />
                </Routes>
            </div>
        </Router>
    );
}
