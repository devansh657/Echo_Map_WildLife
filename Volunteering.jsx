import React, { useState } from 'react';
import Sidebar from "../common/Sidebar";
import { Button, Fab } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import axios from 'axios';
import "../css/volunteering.css";
import { ContainedButton, SubTitle, TextButton } from '../components/ReusableComponents';
import { useNavigate } from 'react-router-dom';

function Volunteering() {
    const navigate = useNavigate();

    const [showModal, setShowModal] = useState(false);
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [requiredVolunteers, setRequiredVolunteers] = useState('');
    const [error, setError] = useState('');

    const openModal = () => {
        resetForm();
        setShowModal(true);
    };

    const closeModal = () => {
        setShowModal(false);
    };

    const resetForm = () => {
        setTitle('');
        setDescription('');
        setStartDate('');
        setEndDate('');
        setRequiredVolunteers('');
        setError('');
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const resp = await axios.post('http://localhost:8080/events/createEvent', {
                title,
                description,
                startDate,
                endDate,
                requiredVolunteers
            });

            if (resp.data.status === "Success") {
                navigate('/map');
            } else {
                setError("Title already exists. Please select a different title");
            }
        } catch (error) {
            setError(error.message);
        }
    };

    const Modal = ({ show, onClose }) => {
        if (!show) {
            return null;
        }

        return (
            <div className="modal-backdrop">
                <div className='frame'>
                    <SubTitle text={"Create New Event"} />
                    <input
                        className='title'
                        type="text"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        placeholder="Title"
                        required />
                    <input
                        className='description'
                        type="text"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        placeholder="Description"
                        required />
                    <div className='date'>
                        <input
                            className='start'
                            type="date"
                            value={startDate}
                            onChange={(e) => setStartDate(e.target.value)}
                            placeholder="Start Date"
                            required />
                        <input
                            className='end'
                            type="date"
                            value={endDate}
                            onChange={(e) => setEndDate(e.target.value)}
                            placeholder="End Date"
                            required />
                    </div>
                    <input
                        className='volunt'
                        type="text"
                        value={requiredVolunteers}
                        onChange={(e) => setRequiredVolunteers(e.target.value)}
                        placeholder="Number of Volunteers Required"
                        required />
                    <div className='btn'>
                        <ContainedButton text={"Create Event"} action={handleSubmit} />
                        <TextButton text={'Cancel'} action={closeModal} />
                    </div>
                    {error && <div className="error">{error}</div>}
                </div>
            </div>
        );
    };

    return (
        <>
            <Sidebar />
            <Modal show={showModal} onClose={closeModal} />
            <Button className="cp-btn" onClick={openModal} >
                <Fab
                    color="primary"
                    aria-label="add"
                    sx={{
                        position: "absolute",
                        bottom: -560,
                        right: 60,
                        width: "50px",
                        height: "50px",
                        fontSize: "2rem",
                        backgroundColor: "#59c174"
                    }}
                >
                    <AddIcon sx={{ fontSize: "inherit" }} />
                </Fab>
            </Button>
        </>
    )
}

export default Volunteering;
