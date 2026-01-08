// Modal.jsx
import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { ContainedButton, SubTitle, TextButton } from '../../components/ReusableComponents';
import "../../css/volunteering.css"

function Modal({ show, onClose,username }) {
    // console.log(organizer)
    const navigate = useNavigate();
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [requiredVolunteers, setRequiredVolunteers] = useState('');
    const [error, setError] = useState('');
    const backendUrl = process.env.REACT_APP_API_URL;

    const resetForm = () => {
        setTitle('');
        setDescription('');
        setStartDate('');
        setEndDate('');
        setRequiredVolunteers('');
        setError('');
    };

    const handleSubmit = async (e) => {
        console.log("title",title);
        e.preventDefault();
        try {
            console.log("username",username)
            const organizer=username;
            if (!requiredVolunteers || isNaN(requiredVolunteers)) {
                setError('Number of volunteers is required and must be a number');
                return;
            }
            // const data= await axios.get('http://localhost:8080/user/find', { params: { username: username } });
            // // console.log("x",organizer.data);
            // const organizer = data.data
            console.log("volint",requiredVolunteers)
        const resp = await axios.post(`${backendUrl}/events/createEvent`, {
                title: title,
                description: description,
                startDate: startDate,
                endDate: endDate,
                requiredVolunteers: parseInt(requiredVolunteers, 10), 
                username: organizer
            });

            if (resp.data.status == "Added") {
                resetForm();
                onClose();
            }

            // console.log(resp.data.status)
            
        } catch (error) {
            // console.log("error",error)
            // setError(error.response.data.message);
        }
    };

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
                    <TextButton text={'Cancel'} action={ ()=>{onClose(); resetForm();}} />
                </div>
                {error && <div className="error">{error}</div>}
            </div>
        </div>
    );
}

export default Modal;
