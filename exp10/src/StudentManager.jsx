import { useState, useEffect } from 'react';

const StudentManager = () => {
  const initialStudents = [
    { id: 1, name: 'Alice', course: 'Math' },
    { id: 2, name: 'Bob', course: 'Science' },
    { id: 3, name: 'Charlie', course: 'History' },
    { id: 4, name: 'Diana', course: 'English' },
    { id: 5, name: 'Eve', course: 'Art' },
  ];

  const [students, setStudents] = useState([]);
  const [newStudent, setNewStudent] = useState({ id: '', name: '', course: '' });

  useEffect(() => {
    const savedStudents = localStorage.getItem('students');
    if (savedStudents) {
      setStudents(JSON.parse(savedStudents));
    } else {
      setStudents(initialStudents);
    }
  }, []);

  useEffect(() => {
    localStorage.setItem('students', JSON.stringify(students));
  }, [students]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewStudent({ ...newStudent, [name]: value });
  };

  const addStudent = () => {
    if (newStudent.id && newStudent.name && newStudent.course) {
      setStudents([...students, { ...newStudent, id: parseInt(newStudent.id) }]);
      setNewStudent({ id: '', name: '', course: '' });
    }
  };

  const deleteStudent = (id) => {
    setStudents(students.filter(student => student.id !== id));
  };

  return (
    <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
      <h1>Student Manager</h1>
      <div style={{ marginBottom: '20px' }}>
        <input
          type="number"
          name="id"
          value={newStudent.id}
          onChange={handleInputChange}
          placeholder="ID"
          style={{ marginRight: '10px', padding: '5px' }}
        />
        <input
          name="name"
          value={newStudent.name}
          onChange={handleInputChange}
          placeholder="Name"
          style={{ marginRight: '10px', padding: '5px' }}
        />
        <input
          name="course"
          value={newStudent.course}
          onChange={handleInputChange}
          placeholder="Course"
          style={{ marginRight: '10px', padding: '5px' }}
        />
        <button onClick={addStudent} style={{ padding: '5px 10px', backgroundColor: '#4CAF50', color: 'white', border: 'none', cursor: 'pointer' }}>Add Student</button>
      </div>
      {students.length === 0 ? (
        <p>No students available</p>
      ) : (
        <table style={{ width: '100%', borderCollapse: 'collapse' }}>
          <thead>
            <tr style={{ backgroundColor: '#f2f2f2' }}>
              <th style={{ border: '1px solid #ddd', padding: '8px' }}>ID</th>
              <th style={{ border: '1px solid #ddd', padding: '8px' }}>Name</th>
              <th style={{ border: '1px solid #ddd', padding: '8px' }}>Course</th>
              <th style={{ border: '1px solid #ddd', padding: '8px' }}>Action</th>
            </tr>
          </thead>
          <tbody>
            {students.map(student => (
              <tr key={student.id}>
                <td style={{ border: '1px solid #ddd', padding: '8px' }}>{student.id}</td>
                <td style={{ border: '1px solid #ddd', padding: '8px' }}>{student.name}</td>
                <td style={{ border: '1px solid #ddd', padding: '8px' }}>{student.course}</td>
                <td style={{ border: '1px solid #ddd', padding: '8px' }}>
                  <button onClick={() => deleteStudent(student.id)} style={{ padding: '5px 10px', backgroundColor: '#f44336', color: 'white', border: 'none', cursor: 'pointer' }}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default StudentManager;