import React, { useState, useEffect } from 'react';
import { FaStar } from 'react-icons/fa';
import '../css/Feedback.css';
import axios from "axios";
import { v4 as uuidv4 } from 'uuid'; //for id
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBook, faGlobe, faLightbulb, faThumbsUp, faEllipsisH, faRoad } from '@fortawesome/free-solid-svg-icons';

function Feedback() {
  const [rating, setRating] = useState(0);
  const [rated, setRated] = useState(false);
  const [submit, setSubmitted] = useState(false);
  const [id, setId] = useState("");
  const [categorySelected, setCategorySelected] = useState(null);
  const [opinion, setOpinion] = useState("");

  useEffect(() => {
    const generatedId = uuidv4(); //generates a user Id 
    setId(generatedId);
  }, []);

  const onStarClick = (index) => {
    setRating(index + 1);
    setRated(true); //when rating is selected
  };

  const onSubmit = (event) => {
    event.preventDefault();

    const feedback = {
      id: id,
      rating: rating,
      opinion: opinion,
      category: categorySelected
    };

    const credentials = btoa(`${"renas"}:${"renas"}`);
    const basicAuth = `Basic ${credentials}`; 

    axios.post('http://localhost:8080/feedback', feedback, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: basicAuth
      }
    })    
    .then(response => {
      console.log('Feedback submitted successfully:', response);
      setSubmitted(true);
    })
    .catch(error => {
      console.error('Error submitting feedback:', error);
    });
  };

  const onCancel = () => {
    setRating(0);
    setRated(false); //Reset const setRated
    setSubmitted(false);
    setCategorySelected(null);
    setOpinion("");
  };

  const handleCategorySelection = (category) => {
    setCategorySelected(category);
  };

  return (
      <div className='rating-container' style={{ height: submit ? '200px' : 'auto' }}>
        {submit ? (
          <p style={{ fontWeight: 'bold', fontSize: '24px', margin: '20px' }}>Thank you for providing feedback!</p>
        ) : (
          <>
            <div className="feedback-text">
              <p style={{ fontWeight: 'bold', fontSize: '24px', margin: '10px' }}>Give Feedback:</p>
              <p style={{ fontSize: '16px', fontWeight: 'bold' }}>How is your user experience with us?</p>
            </div>
            <div className="stars-container">
              {[...Array(5)].map((_, index) => (
                <FaStar
                  key={index}
                  size={60}
                  color={index < rating ? '#1f98f4' : '#FFFFFF'}
                  onClick={() => onStarClick(index)}
                  style={{ cursor: 'pointer' }}
                />
              ))}
            </div>
            <div className="rated-stars">
              <p>You rated {rating} stars</p>
            </div>

            <div>
              <p style={{ fontSize: '16px', margin: '10px', fontWeight: 'bold' }}>Choose a subject and give us your opinion:</p>
              <div className="category-container">
                <div className={`category-icon ${categorySelected === 'Courses' && 'selected'}`} onClick={() => handleCategorySelection('Courses')}>
                  <FontAwesomeIcon icon={faBook} />
                  <span>Courses</span>
                </div>
                <div className={`category-icon ${categorySelected === 'Website' && 'selected'}`} onClick={() => handleCategorySelection('Website')}>
                  <FontAwesomeIcon icon={faGlobe} />
                  <span>Website</span>
                </div>
                <div className={`category-icon ${categorySelected === 'Suggestions' && 'selected'}`} onClick={() => handleCategorySelection('Suggestions')}>
                  <FontAwesomeIcon icon={faLightbulb} />
                  <span>Suggestions</span>
                </div>
              </div>
              <div className="category-container">
                <div className={`category-icon ${categorySelected === 'Compliments' && 'selected'}`} onClick={() => handleCategorySelection('Compliments')}>
                  <FontAwesomeIcon icon={faThumbsUp} />
                  <span>Compliments</span>
                </div>
                <div className={`category-icon ${categorySelected === 'Roadmap' && 'selected'}`} onClick={() => handleCategorySelection('Roadmap')}>
                  <FontAwesomeIcon icon={faRoad} />
                  <span>Roadmap</span>
                </div>
                <div className={`category-icon ${categorySelected === 'Other' && 'selected'}`} onClick={() => handleCategorySelection('Other')}>
                  <FontAwesomeIcon icon={faEllipsisH} />
                  <span>Other</span>
                </div>
              </div>
            </div>

            {categorySelected && (
              <form className="opinion-form" onSubmit={onSubmit}>
                <label htmlFor="opinion">Your Opinion:</label>
                <div className="opinion-text-container">
                  <textarea id="opinion" name="opinion" rows="3" value={opinion} onChange={(e) => setOpinion(e.target.value)} required />
                </div>
              </form>
            )}
            <div className="buttons-container">
              <button className={rated && rating > 0 && categorySelected && opinion ? 'submit active' : 'submit'} onClick={onSubmit} disabled={!rated || rating === 0}>Submit</button>
              <button className='cancel' onClick={onCancel}>Cancel</button>
            </div>
          </>
        )}
      </div>
  );
}

export default Feedback;