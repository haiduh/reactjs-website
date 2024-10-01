import React, { useState, useEffect } from 'react';
import axios from "axios";
import { useParams } from 'react-router-dom'; // Import useParams
import { useNavigate, Link } from 'react-router-dom';

import '../css/DisplayBoxes.css';

function Topics() {
    const { subjectId } = useParams(); 


    const [topics, setTopics] = React.useState([]);

    useEffect(() => {
      const apiUrl = `http://localhost:8080/topics/${subjectId}`
      const username = 'renas'; // Replace with your actual username *****DO WHEN LOGIN ON FRONT END IS COMPLETED
      const password = 'renas'; // Replace with your actual password
  
      // Encode credentials in Base64
      const credentials = btoa(`${username}:${password}`);
      const basicAuth = `Basic ${credentials}`;
  
      axios.get(apiUrl, {
        headers: {
          Authorization: basicAuth,
        },
      })
        .then((response) => {
          setTopics(response.data);
        })
        .catch((error) => {
          console.error('Error fetching data:', error);
        });
    }, [subjectId]);
  
    console.log(topics);



    return(
        <section className='grid'>
        {topics.map((eachTopic) => (
        <div key={eachTopic.id} className='card'>
          <div className='card__header'>Topic Name:  {eachTopic.topicName}</div>
          <div className='card__title'>Image: NO IMAGES IN DB YET!</div>
          <Link to={`/revision-resources/${eachTopic.id}`}>
                      <div className="btn">view topics related to subject</div>
                   </Link>

        </div>
      ))}
      </section>
    );
}

export default Topics;