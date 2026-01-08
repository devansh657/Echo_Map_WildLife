import React, { useState, useEffect } from 'react';
import Sidebar from "../../common/Sidebar";
import { Fab } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import ViewListIcon from '@mui/icons-material/ViewList';
import SortIcon from '@mui/icons-material/Sort';
import IconButton from '@mui/material/IconButton';
import CloseIcon from '@mui/icons-material/Close';
import "../../css/volunteering.css";
import Modal from './Modal';
import { ContainedButton, OutlinedButton, TextButton } from '../../components/ReusableComponents';
import axios from 'axios';

function Vol() {
    const [showModal, setShowModal] = useState(false);
    const [filter, setFilter] = useState("false");
    const [events, setEvents] = useState([]); 
    const [change,setChange]= useState(false);
    const backendUrl = process.env.REACT_APP_API_URL;
    useEffect(() => {
        axios.get(`${backendUrl}/events/allEvents/${localStorage.getItem("User")}/${filter}`)
            .then(response => {
                console.log("response", response);
                setEvents(response.data); 
            })
            .catch(error => console.error("Failed to fetch events:", error)); 
    }, [filter]); 
    
    const openModal = () => setShowModal(true);
    const closeModal = () => setShowModal(false);
    // console.log(events[11].title)
    return (
        <>
            <Sidebar />
            <Modal show={showModal} onClose={closeModal} username={localStorage.getItem("User")} />
            <div className="events-container">
                {events && events.map((event) => ( 
                    <EventPost key={event.event_id} title={event.title} des={event.description} start={event.startDate} end={event.endDate} organizer={event.username} volsCount={event.volunteerCount} reqVol={event.requiredVolunteers} eventId={event.event_id} enrolled={event.enrolled} filter={filter}/>
                ))}
            </div>
            <Fab
                aria-label="add"
                sx={{
                    position: "absolute",
                    bottom: 50,
                    right: 120,
                    width: "50px",
                    height: "50px",
                    fontSize: "2rem",
                    backgroundColor: "transparent", 
                    "&:hover": {
                    backgroundColor: "transparent",
                    opacity: 0.7, 
                    }
                }}
                onClick={() => filter==="true"?setFilter("false"):setFilter("true")}
                >
                {filter==="false"&&
                <SortIcon sx={{ fontSize: "inherit" ,color:"#05386B"}} />
                }
                   {filter!="false"&&
                <ViewListIcon sx={{ fontSize: "inherit" ,color:"#05386B"}} />
                }
            </Fab>
            <Fab
                color="primary"
                aria-label="add"
                sx={{
                    position: "absolute",
                    bottom: 50,
                    right: 60,
                    width: "50px",
                    height: "50px",
                    fontSize: "2rem",
                    backgroundColor: "#59c174"
                }}
                onClick={openModal}
            >
                <AddIcon sx={{ fontSize: "inherit" }} />
            </Fab>
        </>
    );
}

function EventPost({ title, des, start, end,organizer,volsCount, reqVol, eventId ,enrolled,filter}) {
    const[error,setError]=useState('');
    const backendUrl = process.env.REACT_APP_API_URL;

    const enrolToEvent = async () => {
        const user = localStorage.getItem("User");
        try {
            console.log(user);
            console.log(eventId);
            const resp = await axios.put(`${backendUrl}/events/enroll/${eventId}/${user}`);
            console.log("Enrollment Response:", resp.data);
            setError('')
        } catch (error) {
            console.error("Error enrolling in event:", error);
            setError(error.response.data.message);
        }
    };

    const deleteEvent = async () => {
        const user = localStorage.getItem("User");
        try {
            const resp = await axios.delete(`${backendUrl}/events/delete/${eventId}/${user}`);
            setError('');
        }catch(error){
            // setError(error.response.data.message)
        }
    }
    return (
        <div className='event'>
            <div className='event-details'>
            <div className='event-title'>{title}</div>
            <div className='event-des'>{des}</div>
            {organizer!=null &&
            <div className='organizer'>
                Organizer Username: {organizer}
            </div>}
            <div className='event-dates'>
                {reqVol-volsCount} out of {reqVol} spaces left
            </div>
            <div className='event-dates'>
                <div>Start Date: {start}</div>
                <div>End Date: {end}</div>
            </div>
            </div>
            <div className='btn'>
            {enrolled && filter==="false" && <OutlinedButton text={"Unenroll"} action={enrolToEvent}/>}
            {!enrolled && filter==="false" && <ContainedButton text={"Enroll"} action={enrolToEvent}/>}
            {filter==="true" && 
            <>
            <OutlinedButton text={"Modify Event Details"} />
            <IconButton
            onClick={deleteEvent} 
            sx={{
                position: "relative",
                width: "40px",
                height: "40px",
                fontSize: "2rem",
                color: "red", 
                backgroundColor: "transparent",
                "&:hover": {
                backgroundColor: "transparent"
                }
            }}
            >
                <CloseIcon sx={{ fontSize: "inherit" }} />
            </IconButton>
            </>
            }
            <div className='error'>{error}</div>
            </div>
        </div>
    );
}

export default Vol;
