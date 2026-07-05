import React from 'react';

export default function App() {
    return (
        <div style={{ padding: '20px' }}>
            <Product name="Laptop" price={999.99} qty={1} />
        </div>
    );
}

function Product({ name, price, qty }) {
    return (
        <div>
            <h3>Product: {name}</h3>
            <p>Price: ${price}</p>
            <p>Quantity: {qty}</p>
        </div>
    );
}
