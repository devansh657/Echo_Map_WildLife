import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import Sidebar from '../../common/Sidebar'
import { ContainedButton, SubTitle } from '../../components/ReusableComponents'
import axios from 'axios';

function ChangeBio() {

    const [oldUsername,setOldUsername]=useState('');
    const [newBio,setNewBio]=useState('');
    const navigate = useNavigate();

    const handleSubmit=()=>{
      try {
        const response = axios.put(
            'http://localhost:8080/authentication/changebio',
            { oldUsername, newBio}, 
            { headers: { 'Content-Type': 'application/json' } }
        )
            .then(response => {
              localStorage.setItem("bio",newBio)
              console.log(response.data);
              navigate('/map')
            });

            console.log(response.data); // Log the response from the server
            // Handle success, e.g., show a success message to the user
        } catch (error) {
            console.error('Error changing bio:', error.response.data);
            // Handle error, e.g., show an error message to the user
        }
    };
return (
  <>
      <Sidebar />
      <div className='frame'>
          <SubTitle text={"Change Bio"} />

          <input
              className='input'
              type="text"
              value={oldUsername}
              onChange={(e) => setOldUsername(e.target.value)}
              placeholder="Username"
              required
          />

          <input
              className='input'
              type="text"
              value={newBio}
              onChange={(e) => setNewBio(e.target.value)}
              placeholder="New Bio"
              required
          />

          <ContainedButton text={"Submit"} action={handleSubmit} />
      </div>
  </>
);
}

export default ChangeBio;