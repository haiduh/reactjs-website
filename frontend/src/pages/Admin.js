import React, { useEffect, useState } from 'react'
import { viewUser, deleteUser } from '../services/UserService';
import { useNavigate } from 'react-router-dom';

function Admin() {

    const [user, setUser] = useState([]);
    const navigator = useNavigate();

    useEffect(() => {
        viewUsersFunction();
    }, []);

    function viewUsersFunction() {
        viewUser().then((Response) => {
            setUser(Response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    function addNewUserFunction() {
        console.log(`Clicked 'Add a new user'. Navigated to add-new-user page.`);
        navigator('/add-new-user');
    }

    function updateUserFunction(id) {
        console.log(`Clicked 'Update' for user: ` + id + `. Navigated to update-user page.`);
        navigator(`/update-user/${id}`);
    }

    function deleteUserFunction(id) {
        console.log(`Clicked 'Delete' for user: '` + id);
        deleteUser(id).then((Response) => {
            console.log(Response.data);
            alert(Response.data);
            viewUsersFunction();
        }).catch(error => {
            console.error(error);
        })
    }

    return (
        <div className='container'>
            <br />
            <h2 className='text-left'>Registered Users</h2>
            <br />
            <table className='table table-bordered'>
                <thead className='table-primary'>
                    <tr>
                        <th className='text-center'>UUID (ID number)</th>
                        <th className='text-center'>Username</th>
                        <th className='text-center'>First name</th>
                        <th className='text-center'>Surname</th>
                        <th className='text-center'>Email Address</th>
                        <th className='text-center'>Password</th>
                        <th className='text-center'>Manage user</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        user.map(user =>
                            <tr key={user.id}>
                                <td>{user.id}</td>
                                <td>{user.username}</td>
                                <td>{user.firstName}</td>
                                <td>{user.surname}</td>
                                <td>{user.emailAddress}</td>
                                <td>{user.password}</td>
                                <td>
                                    <button className='btn btn-outline-primary mb-2' onClick={() => updateUserFunction(user.id)}>Update</button>
                                    <button className='btn btn-outline-primary mb-2' onClick={() => deleteUserFunction(user.id)}>Delete</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
            <button className='btn btn-outline-primary mb-2' onClick={addNewUserFunction}>Add a new user</button><br />
        </div>
    )
}

export default Admin