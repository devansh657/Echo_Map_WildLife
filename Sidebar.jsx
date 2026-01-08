import React,{useState} from "react";
import '../css/sidebar.css'; 
import MenuIcon from '@mui/icons-material/Menu';
import { ContainedButton, SubTitle, TextButton, Title } from "../components/ReusableComponents";
import { useNavigate } from 'react-router-dom';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';



function Sidebar(){
  const  navigate=useNavigate();
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);

  const toggleSidebar = () => {
    setIsSidebarOpen(!isSidebarOpen);
  };

  return (
    <div className="main">

      <div className="menu-side">
        <button className='icon-btn' onClick={toggleSidebar}>
          <MenuIcon fontSize='large' style={{color:'#05386B', backgroundColor: 'transparent' }} />
        </button>
        <Title/>
        <SidebarComponent isOpen={isSidebarOpen} toggleSidebar={toggleSidebar} />
      </div>

        {/* <button className='icon-btn' onClick={()=>navigate('/profile')}> */}
        <div className="corner-buttons">
        <button className='icon-btn' onClick={()=>navigate('/profile')}>
          <AccountCircleIcon fontSize='large' style={{color:'#05386B', backgroundColor: 'transparent' }}/>
        </button>
        <button className="logout" onClick={()=>navigate('/')}>
          Log Out
        </button>
        </div>
    </div>
  );
}

function SidebarComponent({ isOpen, toggleSidebar }) {
  const  navigate=useNavigate();
  return (
    <div className={isOpen ? "sidebar open" : "sidebar"}>
      
      <button className='icon-btn' onClick={toggleSidebar}>
        <MenuIcon fontSize='large' style={{ backgroundColor: 'transparent',color:'white' }} />
      </button>
      <div className="options">
          <TextButton text="Go To Map" action={()=>navigate('/map')}/>
          <TextButton text="Community Hub Home" action={()=>navigate('/createpost')}/>
          <TextButton text="Volunteer" action={()=>navigate('/volunteering')}/>
          <TextButton text="Donate" action={()=>navigate('/volunteering')}/>
          <TextButton text="View Articles" action={()=>navigate('/volunteering')}/>
        </div>
      
    </div>
  );
}

function ProfileDropdown() {
  const [selectedValue, setSelectedValue] = useState('');

  const handleChange = (event) => {
    setSelectedValue(event.target.value);
  };

  return (
    <div>
      {/* <label htmlFor="dropdown">Choose an option:</label> */}
      <select id="dropdown" value={selectedValue} onChange={handleChange}>
        {/* <option value="">Select...</option> */}
        <option value="option1">Saved</option>
        <option value="option2">Settings</option>
        <option value="option3">Log Out</option>

      </select>
    </div>
  );
}

export default Sidebar;