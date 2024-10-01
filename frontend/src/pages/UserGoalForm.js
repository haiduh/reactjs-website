import React, { useState } from 'react';
import ChooseSubjects from '../components/ChooseSubjects'
import ChooseTopics from '../components/ChooseTopics';
function UserGoalForm() {
  const [subjectIds, setSubjectIds] = useState([]);

  const handleSubjectChange = (newSubjectIds) => {
    setSubjectIds(newSubjectIds);
  };

  console.log(subjectIds);

    return (
      <div>
        <div>Form</div>
        <div>
          <ChooseSubjects onSubjectChange={handleSubjectChange}/>
          <ChooseTopics subjectIds={subjectIds}/>
 
        </div>
      </div>
            
    );
  }
  
  export default UserGoalForm;