import React, { useEffect, useState } from 'react';
import { createUser, getUser, updateUser } from '../services/UserService';
import { useNavigate, useParams } from 'react-router-dom';

function AdminComponent() {

  const [username, setUsername] = useState('');
  const [firstName, setFirstName] = useState('');
  const [surname, setSurname] = useState('');
  const [emailAddress, setEmailAddress] = useState('');
  const [password, setPassword] = useState('');

  const { id } = useParams();
  const [error, setError] = useState({
    username: '',
    firstName: '',
    surname: '',
    emailAddress: '',
    password: ''
  });

  const navigator = useNavigate();

  useEffect(() => {
    if (id) {
      getUser(id).then((Response) => {
        setUsername(Response.data.username);
        setFirstName(Response.data.firstName);
        setSurname(Response.data.surname);
        setEmailAddress(Response.data.emailAddress);
        setPassword(Response.data.password);
      }).catch(error => {
        console.error(error);
      })
    }
  }, [id]);

  function createOrUpdateUser(event) {
    event.preventDefault();

    if (validateForm()) {

      const user = { username, firstName, surname, emailAddress, password };
      console.log(user);

      if (id) {
        updateUser(id, user).then((Response) => {
          console.log(Response.data);
          console.log('User: ' + Response.data.username + ' updated successfully.');
          alert('User: ' + Response.data.username + ' updated successfully.')
          navigator('/admin');
        }).catch(error => {
          console.error(error);
          alert(error);
        })
      } else {
        createUser(user).then((Response) => {
          console.log(Response.data);
          console.log('User: ' + Response.data.username + ' created successfully.');
          alert('User: ' + Response.data.username + ' created successfully.');
          navigator('/admin')
        }).catch(error => {
          console.error(error);
          alert("The minimum field requirements may have not been met OR the username and/or email address may have already been used.");
        })
      }
    }
  }

  function validateForm() {
    let valid = true;
    const errorCopy = { ...error };

    if (username.trim()) {
      errorCopy.username = '';
    } else {
      errorCopy.username = 'Username is required';
      console.log('Minimum field requirements for Username not met.')
      valid = false;
    }

    if (firstName.trim()) {
      errorCopy.firstName = '';
    } else {
      errorCopy.firstName = 'First name is required';
      console.log('Minimum field requirements for First name not met.')
      valid = false;
    }

    if (surname.trim()) {
      errorCopy.surname = '';
    } else {
      errorCopy.surname = 'Surname is required';
      console.log('Minimum field requirements for Surname not met.')
      valid = false;
    }

    if (emailAddress.trim()) {
      errorCopy.emailAddress = '';
    } else {
      errorCopy.emailAddress = 'Email address is required';
      console.log('Minimum field requirements for Email Address not met.')
      valid = false;
    }

    if (password.trim()) {
      errorCopy.password = '';
    } else {
      errorCopy.password = 'Password is required';
      console.log('Minimum field requirements for Password not met.')
      valid = false;
    }

    setError(errorCopy);
    return valid;
  }

  function dynamicPageHeader() {
    if (id) {
      return <h2 className='text-center'>Update user</h2>
    } else {
      return <h2 className='text-center'>Add a new user</h2>
    }
  }

  return (
    <div className='container'>
      <br /><br />
      <div className='row'>
        <div className='card col-md-4 offset-md-4 offset-md-4'><br />
          {
            dynamicPageHeader()
          }
          <div className='card-body'>
            <form>

              <div className='form-group mb-2'>
                <label className='form-label'>Username:</label>
                <input
                  type='text'
                  placeholder='Minimum 3 characters'
                  name='username'
                  value={username}
                  className={`form-control ${error.username ? 'is-invalid' : ''}`}
                  onChange={(event) => setUsername(event.target.value)}
                ></input>
                {error.username && <div className='invalid-feedback'> {error.username} </div>}
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'>First name:</label>
                <input
                  type='text'
                  placeholder='Minimum 1 character'
                  name='firstName'
                  value={firstName}
                  className={`form-control ${error.firstName ? 'is-invalid' : ''}`}
                  onChange={(event) => setFirstName(event.target.value)}
                >
                </input>
                {error.firstName && <div className='invalid-feedback'> {error.firstName} </div>}
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'>Surname:</label>
                <input
                  type='text'
                  placeholder='Minimum 1 character'
                  name='surname'
                  value={surname}
                  className={`form-control ${error.surname ? 'is-invalid' : ''}`}
                  onChange={(event) => setSurname(event.target.value)}
                >
                </input>
                {error.surname && <div className='invalid-feedback'> {error.surname} </div>}
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'>Email Address:</label>
                <input
                  type='text'
                  placeholder='E.g. username@domain.com or .co.uk'
                  name='emailAddress'
                  value={emailAddress}
                  className={`form-control ${error.emailAddress ? 'is-invalid' : ''}`}
                  onChange={(event) => setEmailAddress(event.target.value)}
                >
                </input>
                {error.emailAddress && <div className='invalid-feedback'> {error.emailAddress} </div>}
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'>Password:</label>
                <input
                  type='text'
                  placeholder='Minimum 6 characters'
                  name='password'
                  value={password}
                  className={`form-control ${error.password ? 'is-invalid' : ''}`}
                  onChange={(event) => setPassword(event.target.value)}
                >
                </input>
                {error.password && <div className='invalid-feedback'> {error.password} </div>}
              </div>
              <button className='btn btn-outline-primary' onClick={createOrUpdateUser}>Submit</button>
            </form>
          </div>
        </div>
      </div >
    </div >
  )
}

export default AdminComponent