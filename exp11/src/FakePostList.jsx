import { useState, useEffect } from 'react';
import axios from 'axios';

function FakePostList() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchPosts = () => {
    setLoading(true);
    setError(null);
    axios.get('https://dummyjson.com/posts')
      .then(response => {
        setPosts(response.data.posts);
        setLoading(false);
      })
      .catch(error => {
        setError(error.message);
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchPosts();
  }, []);

  if (loading) return <div className="loading">Loading...</div>;
  if (error) return <div className="error">Error: {error}</div>;

  return (
    <div>
      <h2>Fake API Posts</h2>
      <button onClick={fetchPosts}>Refresh</button>
      <ul className="post-list">
        {posts.map(post => (
          <li key={post.id} className="post-item">
            <strong>{post.title}</strong><br />
            {post.body}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default FakePostList;