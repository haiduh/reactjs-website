import React, { useState, useEffect } from 'react';
import axios from "axios";
import '../css/DisplayBoxes.css';
import { useNavigate, Link } from 'react-router-dom';


function Subjects() {
    const [subjects, setSubjects] = React.useState([]);

  useEffect(() => {
    const apiUrl = 'http://localhost:8080/subjects';
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
        setSubjects(response.data);
      })
      .catch((error) => {
        console.error('Error fetching data:', error);
      });
  }, []);

  console.log(subjects);


    return(
        <div>
        <div>All subjects: </div>
        <section className='grid'>
        {subjects.map((eachSubject) => (
        <div key={eachSubject.id} className='card'>
          <div className='card__header'>Subject Name: {eachSubject.subject}</div>
          <div className='card__title'>Image: NO IMAGES IN DB YET!</div>
          <Link to={`/topics/${eachSubject.id}`}>
                      <div className="btn">view topics related to subject</div>
                   </Link>

        </div>
      ))}
      </section>
        </div>
    );

}

export default Subjects;