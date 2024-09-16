import axios from 'axios';

const backUrl = process.env.REACT_APP_BACKEND_URL;

const API_URL = `${backUrl}/api/aya`;

export const getAyaById = (id) => {
    return axios.get(`${API_URL}/${id}`)
        .then(response => response.data)
        .catch(error => {
            console.error('Error fetching Aya by ID:', error);
            throw error;
        });
};

export const getAllAyas = () => {
    return axios.get(API_URL)
        .then(response => response.data)
        .catch(error => {
            console.error('Error fetching all Aya:', error);
            throw error;
        });
};

export const createAya = (aya) => {
    console.log(aya)
    return axios.post(API_URL, aya)
        .then(response =>console.log(response))
        .catch(error => {
            console.error('Error creating Aya:', error);
            throw error;
        });
};
