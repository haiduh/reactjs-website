import React, { useState, useEffect } from 'react';
import axios from "axios";
import { useParams } from 'react-router-dom'; // Import useParams
import { useNavigate, Link } from 'react-router-dom';

import '../css/DisplayBoxes.css';

function RevisionRecources() {
    const { topicId } = useParams(); 


    const [revisionResources, setRevisionResources] = React.useState([]);

    useEffect(() => {
      const apiUrl = `http://localhost:8080/revision-resources/${topicId}`
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
            setRevisionResources(response.data);
        })
        .catch((error) => {
          console.error('Error fetching data:', error);
        });
    }, [topicId]);
  
    console.log(revisionResources);



    return(
        <section className='grid'>
        {revisionResources.map((eachRescource) => (
        <div key={eachRescource.id} className='card'>
         <div key={eachRescource.id} className='card'>
          <div className='card__name'>Resource type: {eachRescource.resourceName}</div>
          <div className='card__title'>Topic: {eachRescource.topic}</div>
          <div>Where to Access: <a href={eachRescource.whereToAccess}>{eachRescource.whereToAccess}</a></div>
          <div className='card__description'>Description: {eachRescource.description}</div>
          <div />
        </div>

        </div>
      ))}
      </section>
    );
}

export default RevisionRecources;