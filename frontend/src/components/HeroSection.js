import React, { useEffect, useRef } from 'react';
import { Link } from 'react-router-dom'; // Import Link from react-router-dom
import '../App.css';
import { Button } from './Button';
import '../css/HeroSection.css';

function HeroSection() {
  
  return (
    <div className='hero-container'>
     <video src="/videos/video-2.mp4" type="video/mp4" autoPlay loop muted></video>
     
      <p>Discover a tailored educational experience just for you.</p>
      <div className="hero-btns">
      
      <div className="hero-btns">
        <Button className='btns' buttonStyle='btn--outline' buttonSize='btn--large'>
        START YOUR JOURNEY
        </Button>
        <Button className='btns' buttonStyle='btn--primary' buttonSize='btn--large'>
        LEARN MORE<i className='far fa-play-circle' />
        </Button>
        </div>
        </div>
        </div>
  );
}

export default HeroSection;