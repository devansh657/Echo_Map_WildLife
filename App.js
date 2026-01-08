
import './App.css';
import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";
import Homepage from './pages/Homepage'; // Remove the extra ";"
import LoginPage from './pages/LoginPage';
import Signup from './pages/Signup';
import ForgotDetails from './pages/ForgotDetails';
import ResetPassword from './pages/ResetPassword';
import Verification from './pages/Verification';
import TermsAndConditions from './pages/TermsAndConditions';
import Verified from './pages/Verified';
import Map from './pages/Map';
import HubHome from './pages/HubHome';
import Profile from './pages/Profile';
import ChangeUsername from './pages/EditProfile/ChangeUsername';
import Volunteering from './pages/Volunteering';
import Vol from './pages/ManageEvents/Vol';
import CreatePost from './pages/CreatePost';

import ChangePassword from './pages/EditProfile/ChangePassword';
import ChangeBio from './pages/EditProfile/ChangeBio';
import ChangePFP from './pages/EditProfile/ChangePFP';
import ClaimReward from './pages/ManageEvents/Reward';

function App() {
  return (
  
    <div className="App">
        <div className='root-container'>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Homepage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/forgotdetails" element={<ForgotDetails />} />
          <Route path="/resetpassword" element={<ResetPassword />} />
          <Route path="/verification " element={<Verification />} />
          <Route path="/termsandconditions" element={<TermsAndConditions />} />
          <Route path="/verified" element={<Verified />} />
          <Route path="/map" element={<Map />} />
          <Route path="/hubhome" element={<HubHome />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/changeusername" element={<ChangeUsername />} />
          <Route path="/changebio" element={<ChangeBio />} />
          <Route path="/volunteering" element={<Vol />} />
          <Route path="/createpost" element={<CreatePost />} />
          <Route path="/rewards" element={<ClaimReward />} />
          <Route path="/ChangePFP" element={<ChangePFP />} />
        </Routes>
      </BrowserRouter>
    </div>
    </div>
  );
}

export default App;