import React, { useState, useEffect } from 'react';
import axios from "axios";
import '../css/DisplayBoxes.css';

function GetRoadmap() {
  const [roadmap, setRoadmap] = React.useState(null);

  useEffect(() => {
    // Replace the URL with the correct API endpoint
    const apiUrl = 'http://localhost:8080/roadmap/renas';
    const username = 'renas'; // Replace with your actual username
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
        setRoadmap(response.data);
      })
      .catch((error) => {
        console.error('Error fetching data:', error);
      });
  }, []);

  console.log(roadmap);
  
  return (
      <section className='grid'>
        {roadmap?.revisionResourceDaos.map((resource) => (
        <div key={resource.id} className='card'>
          <div className='card__name'>Resource type: {resource.resourceName}</div>
          <div className='card__title'>Topic: {resource.topic}</div>
          <div>Where to Access: <a href={resource.whereToAccess}>{resource.whereToAccess}</a></div>
          <div className='card__description'>Description: {resource.description}</div>
          <div />
        </div>
      ))}
      </section>
  );
}

export default GetRoadmap;
