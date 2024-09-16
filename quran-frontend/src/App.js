import React from "react";
import AyaComponent from "./components/AyaComponent";
import CreateAyaComponent from "./components/CreateAyaComponent";
import './App.css'; // Import the CSS file

const App = () => {
    return (
        <div className="container">
            <h1>Quran Aya Pronunciation Checker</h1>
            <section>
                <AyaComponent />
            </section>
            <section>
                <CreateAyaComponent />
            </section>
        </div>
    );
};

export default App;
