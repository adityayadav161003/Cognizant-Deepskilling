import React, { Component } from 'react';

export default class App extends Component {
    state = { posts: [], loading: true };

    componentDidMount() {
        fetch('https://jsonplaceholder.typicode.com/posts?_limit=5')
            .then(res => res.json())
            .then(data => this.setState({ posts: data, loading: false }));
    }

    render() {
        return (
            <div style={{ padding: '20px' }}>
                <h2>JSON Posts list</h2>
                {this.state.loading ? <p>Loading...</p> : (
                    <ul>
                        {this.state.posts.map(p => <li key={p.id}>{p.title}</li>)}
                    </ul>
                )}
            </div>
        );
    }
}
