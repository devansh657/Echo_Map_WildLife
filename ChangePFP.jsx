import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import Sidebar from '../../common/Sidebar'
import { ContainedButton, SubTitle } from '../../components/ReusableComponents'
import axios from 'axios';

function ChangePFP() {

    const [oldUsername,setOldUsername]=useState('');
    const [newPFP,setNewPFP]=useState('');
    const navigate = useNavigate();

    const handleSubmit=()=>{
      try {
        const response = axios.put(
            'http://localhost:8080/authentication/changeusername',
            { oldUsername, newPFP}, 
            { headers: { 'Content-Type': 'application/json' } }
        )
            .then(response => {
              localStorage.setItem("PFP",newPFP)
              console.log(response.data);
              navigate('/map')
            });

            console.log(response.data); // Log the response from the server
            // Handle success, e.g., show a success message to the user
        } catch (error) {
            console.error('Error changing PFP:', error.response.data);
            // Handle error, e.g., show an error message to the user
        }
    };
return (
  <>
      <Sidebar />
      <div className='frame'>
          <SubTitle text={"Change PFP"} />

          <input
              className='input'
              type="text"
              value={oldUsername}
              onChange={(e) => setOldUsername(e.target.value)}
              placeholder="Old Username"
              required
          />

          <input
              className='input'
              type="file"
              value={newPFP}
              onChange={(e) => setNewPFP(e.target.value)}
              placeholder="New PFP"
              required
          />

          <ContainedButton text={"Submit"} action={handleSubmit} />
      </div>
  </>
);
}

export default ChangePFP;