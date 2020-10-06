import React from "react";
import { connect } from "react-redux";
import { fetchTodo, editTodo } from "../../actions";
import TodoForm from "./TodoForm";

class TodoEdit extends React.Component {

    componentDidMount() {
        const token = localStorage.getItem("jwt");
        if (token) {
            this.props.fetchTodo(this.props.match.params.id);
        }
    }

    handleOnSubmit = (formValues) => {
        formValues.userName = this.props.userName;
        formValues.id = this.props.match.params.id;
        this.props.editTodo(formValues);
    }

    getInitialValues = (todo) => {
        const milliSeconds = todo.expiredAt - new Date().getTime();
        const days = Math.floor((milliSeconds / (60 * 60 * 24 * 1000)) % 365);
        const remainingMilliSeconds = milliSeconds - days * 24 * 60 * 60 * 1000;
        const hours = Math.floor((remainingMilliSeconds / (60 * 60 * 1000) % 24));

        return {
            description: todo.description,
            todoDays: days.toString(),
            todoHours: hours.toString()
        }
    }

    renderForm = () => {
        const {todo, currentUserId} = this.props;

        if (!todo) {
            return null;
        }

        if (todo.createdById !== currentUserId) {
            return <h1>You are not authorized!</h1>
        }

        return <TodoForm initialValues={this.getInitialValues(todo)} onSubmit={this.handleOnSubmit}/>;
    }

    render() {
        return (
            <div>{this.renderForm()}</div>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        todo: state.todos[ownProps.match.params.id],
        userName: state.signedInUser.userName,
        currentUserId: state.signedInUser.userId
    }
}

export default connect(mapStateToProps, { fetchTodo, editTodo })(TodoEdit);