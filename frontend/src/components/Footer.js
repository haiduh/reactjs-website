import React from 'react'
import { Button } from './Button'
import '../css/Footer.css';
import { Link } from 'react-router-dom';

function Footer() {
  return (
    <div className='footer-container'>
        <section className="footer-subscription">
            <p className="footer-subscription-heading">

            Stay informed about personalized learning opportunities!
            </p>
            <p className="footer-subscription-text">
            Subscribe to our newsletter and receive updates on tailored educational content.
            </p>
            <div className="input-areas">
                <form>
                    
                    
                        <input type="email" name="email" placeHolder="Your Email" className ="footer-input"/>
                        <Button buttonStyle='btn--outline'>Subscribe</Button>
                </form>
            </div>

        </section>
        <div className="footer-links">
          <div className="footer-link-wrapper">
          <div className="footer-link-items">
            
          </div>  
          </div>
        </div>
      
    </div>
  )
}

export default Footer
