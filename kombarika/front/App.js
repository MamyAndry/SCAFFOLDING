import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import Bureauvote from "./components/bureauvote/Bureauvote";
import Commune from "./components/commune/Commune";
import District from "./components/district/District";
import Fokontany from "./components/fokontany/Fokontany";
import Region from "./components/region/Region";


function App() {
    return (
        <Router>
            <div className="App">
                <div className='row'>
                    <nav className="navbar navbar-light bg-light">
                        <div className="container-fluid">
                            <ul className='nav'>
                                <li>
                                    <a className="navbar-brand" href="/">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" className="bi bi-house-fill d-inline-block align-text-top" viewBox="0 0 16 16">
                                            <path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L8 2.207l6.646 6.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293z"/>
                                            <path d="m8 3.293 6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293z"/>
                                        </svg>
                                        <img src="#" alt="icon" width="30" height="24" className="d-inline-block align-text-top"/> 
                                    </a>
                                </li>
                                <li>
                                    <button className="btn" type="button" data-bs-toggle="collapse" data-bs-target="#collapseNavbar" aria-controls="collapseNavbar" aria-expanded="false">
                                        <span className="navbar-toggler-icon"></span>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
                <div className='row'>
                    <div className='col-auto collapse collapse-horizontal' id='collapseNavbar'>
                        <ul className="nav flex-column">
	<li className="nav-item">
		<a className="nav-link active" aria-current="page" href="/bureauvote">Bureauvote</a>
	</li>
	
	<li className="nav-item">
		<a className="nav-link active" aria-current="page" href="/commune">Commune</a>
	</li>
	
	<li className="nav-item">
		<a className="nav-link active" aria-current="page" href="/district">District</a>
	</li>
	
	<li className="nav-item">
		<a className="nav-link active" aria-current="page" href="/fokontany">Fokontany</a>
	</li>
	
	<li className="nav-item">
		<a className="nav-link active" aria-current="page" href="/region">Region</a>
	</li>
	
	
                        </ul>
                    </div>
                    <div className='col-1'></div>
                    <div className='col-9'>
                        <Routes>
					<Route path="/bureauvote" element={<Bureauvote />} />
					<Route path="/commune" element={<Commune />} />
					<Route path="/district" element={<District />} />
					<Route path="/fokontany" element={<Fokontany />} />
					<Route path="/region" element={<Region />} />
		
                        </Routes>
                    </div>           
                    </div>
            </div>
        </Router>
    );
}

export default App;
