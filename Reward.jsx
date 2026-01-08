import React, { useState } from 'react';
import axios from 'axios';
import { Button, Typography } from '@mui/material';
import trophyImage20 from '../ManageEvents/Trophy20.webp';
import trophyImage40 from '../ManageEvents/Trophy40.webp';
import trophyImage60 from '../ManageEvents/Trophy60.webp';
import trophyImage80 from '../ManageEvents/Trophy80.webp';
import trophyImage100 from '../ManageEvents/Trophy100.webp';
import trophyImage150 from '../ManageEvents/Trophy150.webp';
 
function ClaimReward() {
    const [showPopUp, setShowPopUp] = useState(false);
    const checkPoints = async (points) => {
        try {
            const response = await axios.get('http://localhost:8080/user/rewards', {
                params: { username:localStorage.getItem("User") } 
            });
            console.log(response.data); 
            setShowPopUp(true); 
        } catch (error) {
            console.error('Error Claiming rewards:', error);
        }
    };
    return (
        <div style={{ textAlign: 'center' }}>
            <Typography variant="h4">Rewards System</Typography>
            <div className="claimSection" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', flexDirection: 'column' }}>
                <Typography variant="h5">Claim Your Reward</Typography>
                <div className="row" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                    <div className="column" style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', marginRight: '20px' }}>
                        <div className="rewardItem" style={{ marginBottom: '20px' }}>
                            <img src={trophyImage20} alt="Trophy" style={{ width: '100px', height: '100px' }} />
                            <Typography variant="subtitle1">20 Points</Typography>
                            <Button variant="contained" onClick={() => checkPoints(20)}>Claim Reward</Button>
                        </div>
                        <div className="rewardItem" style={{ marginBottom: '20px' }}>
                            <img src={trophyImage40} alt="Trophy" style={{ width: '100px', height: '100px' }} />
                            <Typography variant="subtitle1">40 Points</Typography>
                            <Button variant="contained" onClick={() => checkPoints(40)}>Claim Reward</Button>
                        </div>
                        <div className="rewardItem">
                            <img src={trophyImage60} alt="Trophy" style={{ width: '100px', height: '100px' }} />
                            <Typography variant="subtitle1">60 Points</Typography>
                            <Button variant="contained" onClick={() => checkPoints(60)}>Claim Reward</Button>
                        </div>
                    </div>
                    <div className="column" style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                        <div className="rewardItem" style={{ marginBottom: '20px' }}>
                            <img src={trophyImage80} alt="Trophy" style={{ width: '100px', height: '100px' }} />
                            <Typography variant="subtitle1">80 Points</Typography>
                            <Button variant="contained" onClick={() => checkPoints(80)}>Claim Reward</Button>
                        </div>
                        <div className="rewardItem" style={{ marginBottom: '20px' }}>
                            <img src={trophyImage100} alt="Trophy" style={{ width: '100px', height: '100px' }} />
                            <Typography variant="subtitle1">100 Points</Typography>
                            <Button variant="contained" onClick={() => checkPoints(100)}>Claim Reward</Button>
                        </div>
                        <div className="rewardItem">
                            <img src={trophyImage150} alt="Trophy" style={{ width: '100px', height: '100px' }} />
                            <Typography variant="subtitle1">150 Points</Typography>
                            <Button variant="contained" onClick={() => checkPoints(150)}>Claim Reward</Button>
                        </div>
                    </div>
                </div>
            </div>
            {showPopUp && (
                <div className="popUpContainer">
                    <div className="popUpContent">
                        <div className="sparkles"></div>
                        <Typography variant="h3">Your Rewards are claimed!</Typography>
                        <Button variant="contained" onClick={() => setShowPopUp(false)}>Close</Button>
                    </div>
                </div>
            )}
        </div>
    );
}

export default ClaimReward;