import React, { useState } from 'react';
import "../css/resetpassword.css";
export const ResetPassword = (props) => {
    const [newpassword, setPassword] = useState('');
    const [password, setPass] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(password);
    }

    return (
        <div className="resetpassword">
            <a href="/" className='heading'>
                <h1 className='heading'><span>Eco</span>Map</h1>
            </a>
            <div className='Auth-form-container'>
                <form className="reset-password" onSubmit={handleSubmit}>
                    <h2>Create a strong password</h2>
                    <label for="new password"></label>
                    <input value={newpassword} onChange={(e) => setPassword(e.target.value)} type="password" placeholder="New password" id="password" name="password" />
                    <label for=" retype password"></label>
                    <input value={password} onChange={(e) => setPass(e.target.value)} type="password" placeholder="Retype new password" id="password" name="password" />
                    <p>
                        The password must be at least six characters and should include a combination
                        of numbers, letters, and special characters ($&!%).
                    </p>
                    <button className="resetpasswordbutton">Reset Password</button>
                </form>
            </div></div>
          
    )

}

export default ResetPassword;

