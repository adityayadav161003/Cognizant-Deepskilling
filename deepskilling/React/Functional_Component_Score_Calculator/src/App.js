import React from 'react';
import CalculateScore from './Components/CalculateScore';

export default function App() {
    return (
        <div style={{ padding: '20px' }}>
            <CalculateScore name="Alice" school="Global Academy" total={300} goal={280} />
        </div>
    );
}
