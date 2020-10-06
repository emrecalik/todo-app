import { ApiRequest } from "../api/request";
import {SIGN_IN, SIGN_UP, SIGN_OUT, AUTHENTICATE} from "./types";
import history from "../history";

export const signUpAction = (body) => async (dispatch) =>{
    const response = await ApiRequest().post("/api/auth/signup", {
        name: body.name,
        userName: body.userName,
        email: body.email,
        password: body.password,
        roles: [{ "roleName": "ROLE_" + body.role.toUpperCase() }]
    });

    dispatch({
        type: SIGN_UP,
        payload: response
    });
    history.push("/");
}

export const signInAction = (body) => async (dispatch) => {
    const response = await ApiRequest().post("/api/auth/signin", {
        userName: body.userName,
        password: body.password
    });

    dispatch({
        type: SIGN_IN,
        payload: response.data
    });

    history.push("/");
}

export const signOutAction = () => async (dispatch) => {
    dispatch({
        type: SIGN_OUT
    });
}

export const authenticate = () => async (dispatch) => {
    dispatch({
        type: AUTHENTICATE
    });
}