import axios from "axios";

export const ApiRequest = () => axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        'Access-Control-Allow-Origin' : '*',
        'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS'
    }
});