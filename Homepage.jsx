import React from 'react'
import "../css/homepage.css"
import { Title } from "../components/ReusableComponents";
import { useNavigate } from 'react-router-dom';
function Homepage() {

  const navigation = useNavigate();

  return (
    <div className='homepage-container'>
      <div className='top-bar'>
        
          <Title />
        

        <div className='buttons'>
          <TopbarButton title="Explore Map!" action={() => navigation("/map")} />
          <TopbarButton title="Log In" action={() => navigation("/login")} />
          <TopbarButton title="Sign Up" action={() => navigation("/signup")} />

        </div>

      </div>

      <div className='video-container'>
        <img className="homepage-image" src="/assets/homepageImage.jpg" alt="Homepage" />
        {/* <video autoPlay loop muted >
          <source src="/assets/forestVideo.mp4" type="video/mp4" />
        </video> */}

        <div className='tagline'>
          Empowering conservation through interactive forest mapping.
        </div>
      </div>
    </div>
  )
}

function TopbarButton({ title, action }) {
  return (
    <button className='button' onClick={action}>{title}</button>
  )
}

export default Homepage
