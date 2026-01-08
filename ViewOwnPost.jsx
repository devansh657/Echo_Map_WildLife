import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Sidebar from '../../common/Sidebar'; // Import Sidebar component
import './viewownpost.css'; // Import your CSS file

function ViewOwnPost() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Fetch posts belonging to the logged-in user
    axios.get('http://localhost:8080/viewOwnPosts', {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}` // Assuming you store the access token in localStorage
      }
    })
      .then(response => {
        setPosts(response.data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching own posts:', error);
        setLoading(false);
      });
  }, []); // Empty dependency array ensures this effect runs only once on component mount

  return (
    <>
      <Sidebar />
      <div className='view-area'>
        <h1>View Own Posts</h1>
        {loading ? (
          <p>Loading...</p>
        ) : (
          <ul className='post-list'>
            {posts.map(post => (
              <li key={post.id} className='post-item'>
                <h3 className='post-title'>{post.title}</h3>
                <p className='post-content'>{post.content}</p>
                <p className='post-author'>Author: {post.author}</p>
                <p className='post-created-at'>Created At: {post.createdAt}</p>
              </li>
            ))}
          </ul>
        )}
      </div>
    </>
  );
}

export default ViewOwnPost;
