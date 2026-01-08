import React, { useState } from 'react';
import "../css/ForgetPass.css"
import { Title } from '../components/ReusableComponents';
import { SubTitle } from '../components/ReusableComponents';
import { ContainedButton } from '../components/ReusableComponents';
import { TextButton } from '../components/ReusableComponents';
import { useNavigate } from 'react-router-dom';

function ForgotDetails() {
  const [username, setUsername] = useState('')
  const [newPassword, setNewPassword] = useState('')

  const navigate=useNavigate()

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
  };

  const handleNewPasswordChange = (e) => {
    setNewPassword(e.target.value);
  };

  const handleSendLoginLink = () => {

    console.log(`Sending login link for:`);
  };

  return (
    <>
      <Title />
      <div className='frame'>
      <SubTitle text={"Reset Password"} />
      <div className="inputs">

          <input
            className='username'
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="Username"
            required />

          <input
            className='password'
            type="password"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
            placeholder="New Password"
            required />
         
          </div>
          <ContainedButton text={"Next"} action={handleSendLoginLink} />
          <TextButton text={"Cancel"} action={()=>navigate('/login')}/>
      </div>
    </>


  );
};


export default ForgotDetails;