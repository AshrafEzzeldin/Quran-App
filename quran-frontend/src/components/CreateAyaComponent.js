import React, { useState } from "react";
import { createAya } from '../services/AyaService';
import './CreateAyaComponent.css'; // Import CSS file

const CreateAyaComponent = () => {
    const [ayaText, setAyaText] = useState('');    
    const [correctWord, setCorrectWord] = useState('');
    const [options, setOptions] = useState([]);
    const [ansPath, setAnsPath] = useState('');

    const handleOptionChange = (index, value) => {
        const newOptions = [...options];
        newOptions[index] = value;
        setOptions(newOptions);
    };

    const handleAddOption = () => {
        setOptions([...options, '']);
    };

    const handleRemoveOption = (index) => {
        const newOptions = options.filter((_, i) => i !== index);
        setOptions(newOptions);
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const ayaData = {
            ayaText,
            correctWord,
            options,
            ansPath
        };

        createAya(ayaData)
            .then(() => {
                alert("Aya created successfully!");
                setAyaText('');
                setCorrectWord('');
                setAnsPath('');
                setOptions([]);
            })
            .catch(error => console.error('Error creating Aya:', error));
    };

    return (
        <form onSubmit={handleSubmit}>
            <h3>Create Aya</h3>
            <div>
                <label>Aya Text:</label>
                <input
                    type="text"
                    value={ayaText}
                    onChange={(e) => setAyaText(e.target.value)}
                    required
                />
            </div>
            <div>
                <label>Correct Word:</label>
                <input
                    type="text"
                    value={correctWord}
                    onChange={(e) => setCorrectWord(e.target.value)}
                    required
                />
            </div>
            <div>
                <label>Answer Path:</label>
                <input
                    type="text"
                    value={ansPath}
                    onChange={(e) => setAnsPath(e.target.value)}
                    required
                />
            </div>
            <div>
                <label>Options:</label>
                {options.map((option, index) => (
                    <div key={index}>
                        <input
                            type="text"
                            value={option}
                            onChange={(e) => handleOptionChange(index, e.target.value)}
                            required
                        />
                        <button type="button" onClick={() => handleRemoveOption(index)}>Remove</button>
                    </div>
                ))}
                <button type="button" onClick={handleAddOption}>Add Option</button>
            </div>
            <button className="submit"  type="submit">Create Aya</button>
        </form>
    );
};

export default CreateAyaComponent;
