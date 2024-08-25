import axios from 'axios';

const api = axios.create({
    baseURL: 'https://your-backend-url.herokuapp.com',
});

export default api;
