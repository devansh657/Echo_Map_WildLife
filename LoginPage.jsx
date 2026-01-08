import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import "../css/LoginPage.css"
import "../App.css"
import axios from 'axios';
import { ContainedButton, SubTitle, TextButton, Title } from "../components/ReusableComponents"

export function LoginPage() {

  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleLogin = () => {
    // setLoading(true);
    console.log("username",username)
    console.log("password",password)
    // Axios POST request
    
     axios.post('http://localhost:8080/authentication/login', {
      username,
      password
    })
      .then(response => {
        localStorage.setItem("User",username)
        console.log(response.data);
        setLoading(false);
        const token = response.data;
        localStorage.setItem('token', token); 
        navigate('/map')

      })
      .catch(error => {
        console.error("Error",error);
        setLoading(false);
      });
  };

  return (

    <>
      <Title />

      <div className='frame'>
        <SubTitle text={"Sign In"} />
        <div className="inputs">

          <input
            className='username1'
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="Username"
            required />

          <input
            className='password1'
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Password"
            required />
          <button className="forgot-password" onClick={() => navigate('/forgotdetails')}>Forgot Password/Username</button>
        </div>

        <ContainedButton text={"Sign In"} action={handleLogin} />
        <TextButton text={"Cancel"} action={() => navigate("/")} />

        <div className='signup' style={{ textAlign: 'center' }}>
          <span>Don't have an account yet? </span>
          <button className="signuplink" onClick={() => navigate('/signup')}>Sign up</button>
        </div>

      </div>
      </>

  );
};

export default LoginPage;
