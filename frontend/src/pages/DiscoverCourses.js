import React, { useState } from 'react';
import '../css/DiscoverCourses.css';
import { Button } from 'rsuite';
import courseData from '../data/courseData'; 

  function DiscoverCourses() {
    const [clickedButtonIndexes, setClickedButtonIndexes] = useState(Array.from({ length: courseData.length }, () => null));
  
    const handleClick = (courseIndex, buttonIndex) => {
      const newClickedButtonIndex = [...clickedButtonIndexes];
      newClickedButtonIndex[courseIndex] = buttonIndex;
      setClickedButtonIndexes(newClickedButtonIndex);
    };
  
    return (
      <div>
        <div className='title-container'>
          <p style={{ margin: '10px', fontSize:'36px', fontWeight: 'bold'}}>Discover Courses</p>
          <p style={{ margin:'5px', fontSize:'18px'}}>and specify your expertise</p>
          <p style={{fontSize:'18px'}}>to receive a tailored educational experience</p>
        </div>
      <div className='course-wrapper'>
        {courseData.map((data, courseIndex) => (
          <div className='course-container' key={courseIndex}>
            <div className='image-container'>
              <img src={data.imageUrl} alt="" />
            </div>
            <div className='content'>
              <p style={{ margin: '10px' }}>{data.title}</p>
              <p style={{ margin: '10px', fontSize:'12px' }}>Choose your expertise level:</p>
              <div className='button-container'>
                <div className='expertise-buttons'>
                  <Button
                    className={`expertise-button ${clickedButtonIndexes[courseIndex] === 0 ? 'clicked' : ''}`}
                    active
                    appearance="default"
                    onClick={() => handleClick(courseIndex, 0)}
                  >
                    Novice
                  </Button>
                  <Button
                    className={`expertise-button ${clickedButtonIndexes[courseIndex] === 1 ? 'clicked' : ''}`}
                    active
                    appearance="default"
                    onClick={() => handleClick(courseIndex, 1)}
                  >
                    Competent
                  </Button>
                  <Button
                    className={`expertise-button ${clickedButtonIndexes[courseIndex] === 2 ? 'clicked' : ''}`}
                    active
                    appearance="default"
                    onClick={() => handleClick(courseIndex, 2)}
                  >
                    Expert
                  </Button>
                </div>
                <Button className='roadmap-button' active appearance="default">
                  Click here to process Personalised Roadmap
                </Button>
              </div>
            </div>
          </div>
        ))}
      </div>
      </div>
    );
  }
  
  export default DiscoverCourses;