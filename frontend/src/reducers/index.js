import { combineReducers } from "redux";
import { AuthReducer } from "./AuthReducer";
import { reducer as FormReducer } from "redux-form";
import {TodoReducer} from "./TodoReducer";

export const rootReducer = combineReducers({
    form: FormReducer,
    signedInUser: AuthReducer,
    todos: TodoReducer
});

