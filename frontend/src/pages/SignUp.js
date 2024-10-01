import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../css/SignUp.css';
import user_icon from '../components/Assets/person.png';
import email_icon from '../components/Assets/email.png';
import password_icon from '../components/Assets/password.png';
import axios from 'axios';
import { GithubLoginButton } from "react-social-login-buttons";

const SignUp = () => {
  const [action, setAction] = useState("Sign Up");
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const emailRegex = /^[A-Za-z0-9+_.-]+@(gmail\.com|yahoo\.com|hotmail\.com)$/;

  const validateForm = () => {
    let formValid = false;

    if (action === "Sign Up" && (name.trim() === "" || password.trim() === "" || email.trim() === "")) {
      alert("Please fill in all text fields.");
    } else if (action === "Sign Up" && !emailRegex.test(email)) {
      alert("Invalid e-mail address. Please enter a valid email.");
    } else if (action === "Sign Up" && password.length < 8) {
      alert("Password is too short. Please select another password");
    } else if (action === "Login" && (email.trim() === "" || password.trim() === "")) {
      alert("Please fill in all text fields.");
    } else if (action === "Login" && !emailRegex.test(email)) {
      alert("Invalid e-mail address. Please enter a valid email.");
    } else {
      formValid = true;
    }

    return formValid;
  };

  const handleAction = async () => {
    console.log('Handle action called');

    setErrorMessage("");

    const isFormValid = validateForm();

    if (!isFormValid) {
      return;
    }

    if (action === "Sign Up") {
      try {
        const response = await axios.post('http://localhost:8080/students/register', {
          username: name,
          email: email,
          password: password,
        });

        console.log('Registration successful', response);

        navigate('/login');
      } catch (error) {
        console.error('Error during registration:', error);
        console.log('Server response:', error.response);
        setErrorMessage('Error during registration: ' + (error.message || 'Unexpected error'));
      }
    } else if (action === "Login") {
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

      setName("");
      setEmail("");
      setPassword("");
    }
  };

  const renderButtons = () => {
    return (
      <div className="submit-container">
        {action === "Login" ? (
          <>
            <div className="submit gray" onClick={() => handleAction()}>{action}</div>
            <div className="submit" onClick={() => setAction("Sign Up")}>Sign Up</div>
          </>
        ) : (
          <>
            <div className="submit" onClick={() => handleAction()}>{action}</div>
            <div className="submit gray" onClick={() => setAction("Login")}>Login</div>
          </>
        )}
      </div>
    );
  };

  return (
    <div className='signup-container'>
      <div className="container">
        <div className="header">
          <div className="text">{action}</div>
          <div className="underline"></div>
        </div>

        <div className="inputs">
          {action === "Login" ? null : (
            <div className="input">
              <img src={user_icon} alt="" />
              <input type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)} />
            </div>
          )}
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

        {action === "Sign Up" ? null : (
          <div className="forgot-password">Lost Password ?<span> Click Here!</span></div>
        )}

        {renderButtons()}

         
        <a className="gitHubButton" href="http://localhost:8080/login" target="_self">
          <GithubLoginButton iconSize='25px' style={{ background: '#000000', width: '250px' }} iconColor='#FFFFFF' align='center' />
        </a>


       { //action === "Login" && <Link to="/">Go to Home</Link>//
       }
<a className="gitHubButton" href="http://localhost:8080/login" target="_self"></a>
        <GithubLoginButton iconSize='25px' style={{ background: '#000000', width: '250px' }} iconColor='#FFFFFF' align='center' />

      </div>
    </div>
  );
};

export default SignUp;