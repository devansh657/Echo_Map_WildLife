import React from 'react'
import Sidebar from '../common/Sidebar'
import "../css/profile.css"
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';
import { useNavigate } from 'react-router-dom';

function Profile() {
  const navigate=useNavigate();
  return (
    <>
    <Sidebar/>
    <div className='user-det'>
    <img className='profile-photo' src='/logo512.png'>

    </img>
    <div className='username'>
        Test User
    </div>
    </div>

    <div className='edit-options'>
        <button className='edit-btn' onClick={()=>navigate('/changeusername')}> <Btn_Row text={"Change Username"}/> </button>
        <button className='edit-btn' onClick={()=>navigate('/changepassword')}> <Btn_Row text={"Change Password"}/></button>
        <button className='edit-btn' onClick={()=>navigate('/privacypolicy')}> <Btn_Row text={"Privacy Policy"}/> </button>
        <button className='edit-btn' onClick={()=>navigate('/changebio')}> <Btn_Row text={"Change Bio"}/> </button>
        <button className='edit-btn' onClick={()=>navigate('/ChangePFP')}> <Btn_Row text={"Change PFP"}/> </button>
        <button className='edit-btn' onClick={()=>navigate('/deleteaccount')}> <Btn_Row text={"Delete Account"}/> </button>
    </div>
    </>
  )
}

function Btn_Row({ text }) {
  return (
    <div className='row'>
      <div>{text}</div>
      <ArrowForwardIcon />
    </div>
  );
}


export default Profile