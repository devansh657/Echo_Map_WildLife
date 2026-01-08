import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import Sidebar from '../../common/Sidebar'
import { ContainedButton, SubTitle } from '../../components/ReusableComponents'
import axios from 'axios';

function ChangeUsername() {

    const [oldUsername,setOldUsername]=useState('');
    const [newUsername,setNewUsername]=useState('');
    const navigate = useNavigate();

    const handleSubmit=()=>{
      try {
        const response = axios.put(
            'http://localhost:8080/authentication/changeusername',
            { oldUsername, newUsername }, 
            { headers: { 'Content-Type': 'application/json' } }
        )
            .then(response => {
              localStorage.setItem("username",newUsername)
              console.log(response.data);
              navigate('/map')
            });

            console.log(response.data); // Log the response from the server
            // Handle success, e.g., show a success message to the user
        } catch (error) {
            console.error('Error changing username:', error.response.data);
            // Handle error, e.g., show an error message to the user
        }
    };
return (
  <>
      <Sidebar />
      <div className='frame'>
          <SubTitle text={"Change Username"} />

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
              type="text"
              value={newUsername}
              onChange={(e) => setNewUsername(e.target.value)}
              placeholder="New Username"
              required
          />

          <ContainedButton text={"Submit"} action={handleSubmit} />
      </div>
  </>
);
}

export default ChangeUsername;