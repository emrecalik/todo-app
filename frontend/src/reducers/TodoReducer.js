import {FETCH_ALL_TODOS, CREATE_TODO, DELETE_TODO, FETCH_TODO, EDIT_TODO} from "../actions/types";
import _ from "lodash";

export const TodoReducer = (state = { }, action) => {
    switch (action.type) {
        case FETCH_ALL_TODOS:
            return { ...state, ..._.mapKeys(action.payload, "id") };
        case CREATE_TODO:
            return { ...state, [action.payload.id]: action.payload };
        case DELETE_TODO:
            return { ..._.omit(state, action.payload) };
        case FETCH_TODO:
            return { ...state, [action.payload.id]: action.payload };
        case EDIT_TODO:
            return { ...state, [action.payload.id]: action.payload };
        default:
            return state;
    }
}