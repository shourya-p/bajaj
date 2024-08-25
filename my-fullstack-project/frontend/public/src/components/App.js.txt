import React, { useState } from 'react';
import '../styles/App.css';
import api from '../service/api';

const App = () => {
    const [input, setInput] = useState('');
    const [response, setResponse] = useState(null);
    const [selectedOptions, setSelectedOptions] = useState([]);

    const handleSubmit = async () => {
        try {
            const data = JSON.parse(input);
            const res = await api.post('/bfhl', { data });
            setResponse(res.data);
        } catch (error) {
            alert('Invalid JSON input.');
        }
    };

    const handleSelect = (event) => {
        const options = Array.from(event.target.selectedOptions, option => option.value);
        setSelectedOptions(options);
    };

    const renderResponse = () => {
        if (!response) return null;
        const { numbers, alphabets, highest_lowercase_alphabet } = response;
        const filteredData = selectedOptions.includes('Numbers') ? numbers : [];
        if (selectedOptions.includes('Alphabets')) {
            filteredData.push(...alphabets);
        }
        if (selectedOptions.includes('Highest lowercase alphabet')) {
            filteredData.push(...highest_lowercase_alphabet);
        }
        return (
            <div>
                <h2>Response:</h2>
                <pre>{JSON.stringify(filteredData, null, 2)}</pre>
            </div>
        );
    };

    return (
        <div className="App">
            <h1>JSON Input</h1>
            <textarea
                rows="10"
                cols="50"
                value={input}
                onChange={(e) => setInput(e.target.value)}
            />
            <button onClick={handleSubmit}>Submit</button>
            <h2>Select Data to Display</h2>
            <select multiple onChange={handleSelect}>
                <option value="Numbers">Numbers</option>
                <option value="Alphabets">Alphabets</option>
                <option value="Highest lowercase alphabet">Highest lowercase alphabet</option>
            </select>
            {renderResponse()}
        </div>
    );
};

export default App;
