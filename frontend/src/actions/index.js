import { ApiRequest } from "../api/request";
import {CREATE_TODO, DELETE_TODO, EDIT_TODO, FETCH_ALL_TODOS, FETCH_TODO} from "./types";
import history from "../history";

export const fetchAllTodos = () => async (dispatch) => {
    const response = await ApiRequest().get("/api/todos", {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("jwt")}`
        }
    });
    dispatch({
        type: FETCH_ALL_TODOS,
        payload: response.data
    });
}

export const createTodo = (body) => async (dispatch) => {
    const response = await ApiRequest().post("/api/todo", {
        description: body.description,
        todoDays: body.todoDays,
        todoHours: body.todoHours,
        userName: body.userName
    }, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("jwt")}`
        }
    });

    dispatch({
        type: CREATE_TODO,
        payload: response.data
    });

    history.push("/");
}

export const deleteTodo = (id) => async (dispatch) => {
    await ApiRequest().delete(`/api/todo/${id}`, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("jwt")}`
        }
    });

    dispatch({
        type: DELETE_TODO,
        payload: id
    });

    history.push("/");
}

export const fetchTodo = (id) => async (dispatch) => {
    const response = await ApiRequest().get(`/api/todo/${id}`, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("jwt")}`
        }
    });

    dispatch({
        type: FETCH_TODO,
        payload: response.data
    });
}

export const editTodo = (body) => async (dispatch) => {
    const response = await ApiRequest().put(`/api/todo/${body.id}`, {
        description: body.description,
        todoDays: body.todoDays,
        todoHours: body.todoHours,
        userName: body.userName
    }, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("jwt")}`
        }
    });

    dispatch({
        type: EDIT_TODO,
        payload: response.data
    });
    history.push("/");
}

