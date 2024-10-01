import React, { useState, useEffect } from 'react';
import axios from "axios";
import '../css/ChooseRelevantSubjects.css';

function ChooseSubjects({onSubjectChange}) {
    const [subjects, setSubjects] = React.useState([]);
    const [selectedSubjectIds, setSelectedSubjectIds] = useState([]);



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

  const toggleSubjectSelection = (subjectId) => {
    setSelectedSubjectIds((prevSelected) => {
      const isSelected = prevSelected.includes(subjectId);
      const newSelected = isSelected
        ? prevSelected.filter((id) => id !== subjectId)
        : [...prevSelected, subjectId];
        onSubjectChange(newSelected);
      return newSelected;
    });
  };

     

    return(
        <section className='grid'>
        {subjects.map((eachSubject) => (
        <div key={eachSubject.id} className='card'>
          <div className='card__title'>{eachSubject.subject}</div>
          <button onClick={() => toggleSubjectSelection(eachSubject.id)}>chose this subject</button>
        </div>
      ))}
      </section>
    );
}
export default ChooseSubjects;