import React, { useState } from "react";
import "../css/signup.css";
import { ContainedButton, SubTitle, TextButton, Title } from "../components/ReusableComponents";
import { useNavigate } from "react-router-dom";
import axios from 'axios';

export function Register() {

  const navigate = useNavigate();
  const [first_name, setFname] = useState('');
  const [last_name, setLname] = useState('');
  const [username, setUsername] = useState('');
  const [emailAddress, setEmail] = useState('');
  const [user_type, setUsertype] = useState('');
  const [password, setPassword] = useState('');
  const [cpassword, setCpassword] = useState('');

  const [error,setError]=useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (password !== cpassword) {
      setError("Passwords do not match.");
      return;
    }else{
      setError(" ");
    }

    try {

      const resp = await axios.post('http://localhost:8080/authentication/signup', {
        first_name,
        last_name,
        username,
        emailAddress,
        user_type,
        password,
      });
     
        console.log(resp);
       
  
    } catch (error) {
      console.error(error); 
       switch(error.response.data.status){
          case "Success":
            navigate('/login');
            break;
          case "Username":
            console.log(error.response.data.message)
            setError(error.response.data.message);
            break;
          case "Email":
            setError(error.response.data.message);
            break;
          case "Error":
            setError(error.response.data.message);
            break;
          default:
            setError(" ");
            break;
        }
    }
  };



  return (

    <>
      <Title />
      <div className="frame">

        <SubTitle text="Sign Up" />
        <form onSubmit={handleSubmit}>
          <div className="inputs">
            <div className="name">

              <input
                className='input'
                type="text"
                value={first_name}
                onChange={(e) => setFname(e.target.value)}
                placeholder="First Name"
                required />

              <input
                className='input'
                type="text"
                value={last_name}
                onChange={(e) => setLname(e.target.value)}
                placeholder="Last Name"
                required />
            </div>

            <div className="other-fields">
              <input
                className='input'
                type="text"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                placeholder="Username"
                required />

              <input
                className='input'
                type="text"
                value={emailAddress}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Email Address"
                required />

              <input
                className='input'
                type="text"
                value={user_type}
                onChange={(e) => setUsertype(e.target.value)}
                placeholder="Please state whether you are using this as a student, traveller, etc..."
                required />
            </div>

            <div className="passwords">
              <input
                className='input'
                type="text"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Password"
                required />

              <input
                className='input'
                type="text"
                value={cpassword}
                onChange={(e) => setCpassword(e.target.value)}
                placeholder="Confirm Password"
                required />
            </div>
          </div>
          {error && <div className="error">{error}</div>}
          <div className="buttons">
            <ContainedButton text="Next" type="submit" />
            <TextButton text='Cancel' action={() => navigate("/")} />
          </div>
          
        </form>
        
      </div>

    </>

  )
}
export default Register;