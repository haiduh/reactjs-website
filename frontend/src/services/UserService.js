import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/users';

export const viewUser = function () {
    return axios.get(REST_API_BASE_URL);
}

export const createUser = function (newUser) {
    return axios.post(REST_API_BASE_URL, newUser);
}

export const getUser = function (userId) {
    return axios.get(REST_API_BASE_URL + '/' + userId);
}

export const updateUser = function (userId, user) {
    return axios.put(REST_API_BASE_URL + '/' + userId, user);
}

export const deleteUser = function (userId) {
    return axios.delete(REST_API_BASE_URL + '/' + userId);
}