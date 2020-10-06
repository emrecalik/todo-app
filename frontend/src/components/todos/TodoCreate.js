import React from "react";
import { connect } from "react-redux";
import TodoForm from "./TodoForm";
import { createTodo } from "../../actions";

class TodoCreate extends React.Component {

    handleOnSubmit = (formValues) => {
        formValues.userName = this.props.userName;
        this.props.createTodo(formValues);
    }

    renderForm = () => {
        if (this.props.isSignedIn) {
            return <TodoForm onSubmit={this.handleOnSubmit}/>
        }
        return null;
    }

    render() {
        return (
            <div>
                {this.renderForm()}
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    const { isSignedIn, userName } = state.signedInUser;
    return {
        isSignedIn: isSignedIn,
        userName: userName,
    }
}

export default connect(mapStateToProps, { createTodo })(TodoCreate);