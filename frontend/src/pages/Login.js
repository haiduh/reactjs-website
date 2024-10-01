
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import email_icon from '../components/Assets/email.png';
import password_icon from '../components/Assets/password.png';
import { GithubLoginButton } from "react-social-login-buttons";
import '../css/SignUp.css';

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const emailRegex = /^[A-Za-z0-9+_.-]+@(gmail\.com|yahoo\.com|hotmail\.com)$/;

  const validateForm = () => {
    if (email.trim() === "" || password.trim() === "") {
      alert("Please fill in all text fields.");
      return false;
    } else if (emailRegex.test(email) === false) {
      alert("Invalid email address. Please enter a valid email.");
      return false;
    }
    return true;
  };

  const handleLogin = async () => {
    setErrorMessage("");
    const isFormValid = validateForm();

    if (!isFormValid) {
      return;
    }

    const dataLogin = {
      email: email,
      password: password,
    };

    try {
      const response = await axios.post('http://localhost:8080/students/login', dataLogin);

      console.log('Login response:', response);

      if (response.status === 200) {
        const token = response.data.token;
        if (token) {
          sessionStorage.setItem('jwt', token);
          console.log('JWT Token:', token);
          navigate('/');
        } else {
          console.error('Invalid JWT token:', token);
          alert('An error occurred during login. Please try again later.');
        }
      } else {
        console.error('Login failed:', response.data);
        if (response.status === 401) {
          alert('Invalid email or password');
        } else {
          alert('Login failed. Please try again.');
        }
      }
    } catch (error) {
      console.error('Error during login:', error);
      if (error.response && error.response.status === 401) {
        alert('Invalid email or password');
      } else {
        alert('An error occurred during login. Please try again later.');
      }
    }

    setEmail("");
    setPassword("");
  };

  return (
    <div className='signup-container'>
      <div className="container">
        <div className="header">
          <div className="text">Login</div>
          <div className="underline"></div>
        </div>

        <div className="inputs">
          <div className="input">
            <img src={email_icon} alt="" />
            <input type="email" placeholder="Email Id" value={email} onChange={(e) => setEmail(e.target.value)} />
          </div>
          <div className="input">
            <img src={password_icon} alt="" />
            <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
          </div>
        </div>

        {errorMessage && <div className="error-message">{errorMessage}</div>}

        {/* Lost Password link */}
        <div className="forgot-password">Lost Password ? <span>Click Here!</span></div >


        <button className="submit" onClick={handleLogin}>Login</button>
        <GithubLoginButton iconSize='25px' style={{ background: '#000000', width: '250px' }} iconColor='#FFFFFF' align='center' />
      </div>
    </div>
  );
};

export default Login;