import { useState, useEffect } from 'react';

function LocalUserList() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('/users.json')
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        setUsers(data);
        setLoading(false);
      })
      .catch(error => {
        setError(error.message);
        setLoading(false);
      });
  }, []);

  if (loading) return <div className="loading">Loading...</div>;
  if (error) return <div className="error">Error: {error}</div>;

  return (
    <div>
      <h2>Local Users</h2>
      <ul className="user-list">
        {users.map(user => (
          <li key={user.id} className="user-item">
            <strong>{user.name}</strong><br />
            Email: {user.email}<br />
            Phone: {user.phone}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default LocalUserList;