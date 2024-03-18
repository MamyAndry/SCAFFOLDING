import React from 'react';
import { ${					<Route path="/bureauvote" element={<Bureauvote />} />
					<Route path="/commune" element={<Commune />} />
					<Route path="/district" element={<District />} />
					<Route path="/fokontany" element={<Fokontany />} />
					<Route path="/region" element={<Region />} />
	
}
					<Route path="/bureauvote" element={<Bureauvote />} />
					<Route path="/commune" element={<Commune />} />
					<Route path="/district" element={<District />} />
					<Route path="/fokontany" element={<Fokontany />} />
					<Route path="/region" element={<Region />} />
	
owserRouter as Router, Route, Routes } from 'react-router-dom';
import Bureauvote from "./components/bureauvote/Bureauvote";
import Commune from "./components/commune/Commune";
import District from "./components/district/District";
import Fokontany from "./components/fokontany/Fokontany";
import Region from "./components/region/Region";


function App() {
    return (
        <Router>
            <div className="App">
                <Routes>
					<Route path="/bureauvote" element={<Bureauvote />} />
					<Route path="/commune" element={<Commune />} />
					<Route path="/district" element={<District />} />
					<Route path="/fokontany" element={<Fokontany />} />
					<Route path="/region" element={<Region />} />
	
                </Routes>
            </div>
        </Router>
    );
}

export default App;
