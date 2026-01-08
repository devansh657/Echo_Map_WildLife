import React from 'react'
import BasicUI from '../Map/BasicUI'
import Sidebar from '../common/Sidebar'

function Map() {
  return (
    <>
    <Sidebar/>
    {/* <div style={{ width: "100vw", height: "100vh" }}> */}
    <BasicUI />  
    {/* </div> */}
    </>
  )
}

export default Map