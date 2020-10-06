import {AUTHENTICATE, SIGN_IN, SIGN_OUT, SIGN_UP} from "../actions/types";
import history from "../history";

const initialState = {
    isSignedIn: false,
    userId: null,
    jwt: null,
    userName: null
}

const setLocalStorage = (jwt, userId, userName) => {
    localStorage.setItem("jwt", jwt);
    localStorage.setItem("userId", userId);
    localStorage.setItem("userName", userName);
};

const clearLocalStorage = () => {
    localStorage.removeItem("jwt");
    localStorage.removeItem("userId");
    localStorage.removeItem("userName");
};

export const AuthReducer = (state = initialState, action) => {
    switch (action.type) {
        case SIGN_UP:
            return state;
        case SIGN_IN:
            const { jwt, userId, userName } = action.payload;
            setLocalStorage(jwt, userId, userName)
            return { isSignedIn: true, jwt, userId, userName };
        case SIGN_OUT:
            clearLocalStorage()
            history.push("/");
            return { ...initialState };
        case AUTHENTICATE:
            return { isSignedIn: true, jwt: localStorage.getItem("jwt"), userId: parseInt(localStorage.getItem("userId")), userName: localStorage.getItem("userName") }
        default:
            return state;
    }
}