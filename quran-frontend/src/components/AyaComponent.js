import React, { useState, useEffect } from "react";
import { getAyaById } from '../services/AyaService';
import PronunciationComponent from './PronunciationComponent';
import './AyaComponent.css'; // Import CSS file


const AyaComponent = () => {
    const [aya, setAya] = useState(null);
    const [selectedWord, setSelectedWord] = useState(null);
    const [result, setResult] = useState(null);
    const [ayaId, setAyaId] = useState(1);  // Initial Aya ID

    useEffect(() => {
        getAyaById(ayaId)
            .then(ayaData => setAya(ayaData))
            .catch(error => console.error('Error fetching Aya:', error));
    }, [ayaId]);

    const handleWordSelection = (word) => {
        setSelectedWord(word);
        setResult(null);
    };

    const updateResult =(message)=>{
        setResult(message);
    }

    const onPronunciationCheck = (spokenWord) => {
        alert("You have said: "+spokenWord+" and the correct "+aya.correctWord);
        
        if (spokenWord === aya.correctWord) {
            setResult("Correct pronunciation!");
        } else {
            setResult("Incorrect pronunciation, try again.");
        }
    };

    const getNextAya = () => {
        setAyaId(prevId => prevId + 1);
        setResult("");
        console.log(aya.ansPath);
        
    };

  

    if (!aya) {
        return <div>Loading...</div>;
    }

    return (
        <div className="container">       
            <h2>{aya.ayaText}</h2>
            <div className="options">
                {aya.options.map((option, index) => (
                    <button key={index} onClick={() => handleWordSelection(option)}>
                        {option}
                    </button>
                ))}
            </div>

            <PronunciationComponent onPronunciationCheck={onPronunciationCheck} updateResult={updateResult} />

            {result && <p>{result}</p>}
            
            <p>The right answer</p>
            <audio key={aya.ansPath}  controls>
                <source src={`/assets/${aya.ansPath}`} type="audio/mpeg" />
            </audio>

            <button onClick={getNextAya}>Next Aya</button>
        </div>
    );
};

export default AyaComponent;
