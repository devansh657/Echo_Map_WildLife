import React from 'react';
import { useState } from "react";
import "../css/t&c.css";
export const TermsAndConditions = () => {


  return (
    <>
      <a href="/" className='heading'>
        <h1 className='heading'><span>Eco</span>Map</h1>
      </a>
      <div className="TOSForm">
        {/* <img src={image} alt="image not found" width="400px" height="120px" /> */}
        <div id="t&c">
          Terms and Conditions for EcoMap<br />

          <br />1. Acceptance of Terms<br />

          <br />By accessing and using EcoMap, you agree to comply with and be bound by these terms and conditions. If you do not agree with any part of these terms, please do not use our website. <br />

          <br />2. Intellectual Property<br />

          <br />All content on this website, including text, graphics, logos, images, and software, is the property of EcoMap and is protected by intellectual property laws. You may not reproduce, distribute, or use any content from this website without our prior written permission. <br />

          <br />3. User Conduct<br />

          <br />You agree to use EcoMap for lawful purposes only. You are prohibited from engaging in any activity that may disrupt the functionality of the site, violate the rights of others, or violate any applicable laws. <br />

          <br />4. Privacy Policy<br />

          <br />Your use of EcoMap is also governed by our Privacy Policy. <br />

          <br />5. Disclaimer of Warranties<br />

          <br />EcoMap makes no warranties, expressed or implied, regarding the accuracy or reliability of the content on this website. Your use of the site is at your own risk. <br />

          <br />6. Limitation of Liability<br />

          <br />We will not be liable for any direct, indirect, incidental, consequential, or punitive damages arising out of your access to or use of EcoMap. <br />

          <br />7. Changes to Terms<br />

          <br />EcoMap reserves the right to modify these terms and conditions at any time. It is your responsibility to check for updates. Continued use of the website after changes are made constitutes acceptance of the new terms. <br />

          <br />Contact Information:<br />

          <br />[Your Email Address]
          <br />[Your Phone Number]
        </div>
        <input type="checkbox" id="checkbox" />
        <label for="agree">Yes, I have read and agree to the terms and conditions of the website.</label><br />
        <button type="confirm" id="confirm" onclick="">Confirm</button>
        <button type="cancel" id="cancel" onclick="">Cancel</button>

      </div>

    </>
  )

}
export default TermsAndConditions;