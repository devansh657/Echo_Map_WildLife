import React from 'react'
import "../css/reusablecomponents.css"

export function Title() {
    return (
        <div className='mainTitle'>
            <div className='title-1'>
                Eco
            </div>
            <div className='title-2'>
                Map
            </div>
        </div>
    )
}

export function SubTitle({text}){
    return(
    <div className='subtitle'>{text}</div>
    )
}

export function ContainedButton({text, action}){
    return(
        <button className='contained-button' onClick={action}>{text}</button>
    )
}

export function TextButton({text,action}){
    return(
        <button className='text-button' onClick={action}>{text}</button>
    )
}

export function OutlinedButton({text,action}){
    return(
        <button className='outline-button' onClick={action}>{text}</button>
    )
}




