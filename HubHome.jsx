import React,{useState} from 'react'
import Sidebar from '../common/Sidebar'
import "../css/hubhome.css"
import { Fab } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";


function HubHome() {
  const [showModal, setShowModal] = useState(false);
  return (
        <>
        <Sidebar/>
        <div className='view-area'>
            temporary
        </div>
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
                // onClick={openModal}
            >
                <AddIcon sx={{ fontSize: "inherit" }} />
            </Fab>
        </>
  )
}

export default HubHome