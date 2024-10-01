import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import '@fortawesome/fontawesome-free/css/all.min.css'; // Import Font Awesome CSS
import Navbar from './components/Navbar';
import Home from './pages/Home';
import SignUp from './pages/SignUp'; // Import your SignUp component
import './App.css';

import GetRoadmapPage from './pages/GetRoadmapPage';
import Subjects from './pages/Subjects';
import Topics from './pages/Topics'
import DiscoverCourses from './pages/DiscoverCourses';
import RevisionRecources from './pages/RevisionResources';
import UserGoalForm from './pages/UserGoalForm';
import Feedback from  './pages/Feedback';
import Help from './pages/Help';
import Admin from './pages/Admin';
import AdminComponent from './components/AdminComponent';
import AboutUs from './pages/AboutUs';
import Login from './pages/Login';

function App() {
  return (
    <>
      <Router>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/sign-up" element={<SignUp />} />
            <Route path="/login" element={<Login />} />
          <Route path="/get-roadmap" element={<GetRoadmapPage />} />
          <Route path="/subjects" element={<Subjects />} />
          <Route path="/topics/:subjectId" element={<Topics />} />
          <Route path="/revision-resources/:topicId" element={<RevisionRecources />} />
          <Route path="/user-goal-form" element={<UserGoalForm />} />
          <Route path="/help" element={<Help />} />
          <Route path="/discover-courses" element={<DiscoverCourses />} />
          <Route path="/feedback" element={<Feedback />} />
          <Route path="/admin" element={<Admin />} />
          <Route path='/add-new-user' element={<AdminComponent />}></Route>
          <Route path='/update-user/:id' element={<AdminComponent />}></Route>
          <Route path='/aboutus' element={<AboutUs />}></Route>
          {/* Add other routes/components as needed */}
        </Routes>
      </Router>
    </>
  );
}

export default App;
