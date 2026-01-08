import React, { useState } from 'react';
import Sidebar from "../common/Sidebar";
import { Button, Fab } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import axios from 'axios';
import "../css/createpost.css";
import { ContainedButton, SubTitle, TextButton } from '../components/ReusableComponents';
import { useNavigate } from 'react-router-dom';

function CreatePost() {
    const navigate = useNavigate();

    const [showModal, setShowModal] = useState(false);
    const [title, setTitle] = useState('');
    const [caption, setCaption] = useState('');
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
        setCaption('');
        setError('');
    }; 

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const resp = await axios.post('http://localhost:8080/posts/createpost', {
                title,
                caption,
            });

            if (resp.data.status === "Success") {
                navigate('/hubhome');
            } else {
                setError("Post already exists. Please select a different post");
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
                    <SubTitle text={"Create New Post"} />
                    <input
                        className='title'
                        type="text"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        placeholder="Title"
                        required />
                    <input
                        className='caption'
                        type="text"
                        value={caption}
                        onChange={(e) => setCaption(e.target.value)}
                        placeholder="Caption"
                        required />


                    <div className='btn'>
                        <ContainedButton text={"Create Post"} action={handleSubmit} />
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

export default CreatePost;



   