import { useState } from 'react';
import LocalUserList from './LocalUserList';
import UserList from './UserList';
import FakePostList from './FakePostList';

function Dashboard() {
  const [currentView, setCurrentView] = useState('dashboard');

  const renderView = () => {
    switch (currentView) {
      case 'local':
        return <LocalUserList />;
      case 'api':
        return <UserList />;
      case 'fake':
        return <FakePostList />;
      default:
        return (
          <div>
            <h2>Dashboard</h2>
            <p>Select an option to view data:</p>
          </div>
        );
    }
  };

  return (
    <div className="App">
      <div className="dashboard">
        <button onClick={() => setCurrentView('local')}>Local Users</button>
        <button onClick={() => setCurrentView('api')}>Users API</button>
        <button onClick={() => setCurrentView('fake')}>Fake API Posts</button>
        <button onClick={() => setCurrentView('dashboard')}>Home</button>
      </div>
      {renderView()}
    </div>
  );
}

export default Dashboard;