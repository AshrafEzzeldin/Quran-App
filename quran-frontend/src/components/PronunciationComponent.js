import React, { useState, useEffect } from "react";
import MicRecorder from "mic-recorder-to-mp3";
import axios from "axios";
import './PronunciationComponent.css'; // Import CSS file

const Mp3Recorder = new MicRecorder({ bitRate: 128 });

const PronunciationComponent = ({ onPronunciationCheck, updateResult }) => {
    const [isRecording, setIsRecording] = useState(false);
    const [audioBlob, setAudioBlob] = useState(null);
    const [isBlocked, setIsBlocked] = useState(false);
    const backUrl = process.env.REACT_APP_BACKEND_URL;
        useEffect(() => {
        navigator.getUserMedia(
            { audio: true },
            () => {
                console.log("Microphone permission granted");
                setIsBlocked(false);
            },
            () => {
                console.log("Microphone permission denied");
                setIsBlocked(true);
            }
        );
    }, []);

    const startRecording = () => {

        if (isBlocked) {
            alert("Microphone access is blocked.");
        } else {
            updateResult("Recording");
            Mp3Recorder.start()
                .then(() => {
                    setIsRecording(true);
                })
                .catch((e) => console.error(e));
        }
    };

    const stopRecording = () => {
        Mp3Recorder.stop()
            .getMp3()
            .then(([buffer, blob]) => {
                updateResult(null);
                const file = new File(buffer, "pronunciation.mp3", {
                    type: blob.type,
                    lastModified: Date.now(),
                });
                setAudioBlob(file);
                setIsRecording(false);
            })
            .catch((e) => console.error(e));
    };

    const checkPronunciation = () => {
        if (!audioBlob) {
            alert("Please record your pronunciation first.");
            return;
        }
        updateResult("Checking Pronunciation...");

        const formData = new FormData();
        formData.append("file", audioBlob);

        //    to save the record
        const url = URL.createObjectURL(audioBlob);
        const a = document.createElement("a");
        a.href = url;
        a.download = "pronunciation.mp3";
        a.click();
        URL.revokeObjectURL(url);

        axios
            .post(`${backUrl}/api/pronunciation/check`, formData, {
                headers: {
                    "Content-Type": "multipart/form-data",
                },
            })
            .then((response) => {
                const recognizedWord = response.data;
                onPronunciationCheck(recognizedWord);
            })
            .catch((error) => {
                console.error("Error uploading audio:", error);
            });
    };

    return (
        <div>
            <button onClick={startRecording} disabled={isRecording}>
                Start Recording
            </button>
            <button onClick={stopRecording} disabled={!isRecording}>
                Stop Recording
            </button>
            <button onClick={checkPronunciation} disabled={!audioBlob}>
                Check Pronunciation
            </button>
        </div>
    );
};

export default PronunciationComponent;
