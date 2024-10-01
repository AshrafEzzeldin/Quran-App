import React, { useState, useEffect } from "react";
import { getAyaById } from '../services/AyaService';
import PronunciationComponent from './PronunciationComponent';
import './AyaComponent.css'; // Import CSS file


const AyaComponent = () => {
    const [aya, setAya] = useState(null);
    const [selectedWord, setSelectedWord] = useState(null);
    const [result, setResult] = useState(null);
    const [ayaId, setAyaId] = useState(1);  
    const [hide, sethide] = useState("hide");  

    useEffect(() => {
        getAyaById(ayaId)
            .then(ayaData => setAya(ayaData))
            .catch(error => {console.error('Error fetching Aya:', error);
                setAyaId(ayaId-1);
                alert("There is no next Aya")
            });
    }, [ayaId]);

    const handleWordSelection = (word) => {
        setSelectedWord(word);
        setResult(null);
    };

    const updateResult = (message) => {
        setResult(message);
    }

    const onPronunciationCheck = (spokenWord) => {
        alert("You have said: " + spokenWord + " and the correct " + aya.correctWord);

        if (spokenWord === aya.correctWord) {
            setResult("Correct pronunciation!");
        } else {
            setResult("Incorrect pronunciation, try again.");
        }
        sethide(null);
    };

    const getNextAya = () => {
        setAyaId(prevId => prevId + 1);
        sethide("hide");
        setResult("");
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

            <div className={hide}>
                <p>The right answer</p>
                <audio key={aya.ansPath} controls disa>
                    <source src={`/assets/${aya.ansPath}`} type="audio/mpeg" />
                </audio>
            </div>
            <button onClick={getNextAya}>Next Aya</button>
        </div>
    );
};

export default AyaComponent;
