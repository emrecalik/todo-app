import React from "react";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import { fetchAllTodos } from "../../actions";
import { authenticate } from "../../actions/auth";

class TodoList extends React.Component {

    componentDidMount() {
        const token = localStorage.getItem("jwt");
        if (token) {
            this.props.authenticate();
            this.props.fetchAllTodos();
        }
    }

    renderAdminButtons = (todo) => {
        if (this.props.userId === todo.createdById) {
            return (
                <div className={"right floated content"}>
                    <Link to={`/todo/edit/${todo.id}`} className={"ui vk button"}>Edit</Link>
                    <Link to={`/todo/delete/${todo.id}`} className={"ui youtube button"}>Delete</Link>
                </div>
            );
        }
        return null;
    }

    renderList = () => {
        const { todos, isSignedIn } = this.props;

        if (!isSignedIn) {
            return null;
        }

        return todos.map((todo) => {
            return (
                <div key={todo.id} className={"item"}>
                    {this.renderAdminButtons(todo)}
                    <i className={"large github middle aligned icon"}/>
                    <div className={"content"}>
                        <Link to={`/todo/show/${todo.id}`} className={"header"}>{todo.description}</Link>
                        <div className={"description"} style={{fontSize:"11px"}}>Responsible: {todo.responsible}</div>
                        <div className={"description"} style={{fontSize:"10px", fontStyle:"italic"}}>Expires at {new Date(todo.expiredAt).toString()}</div>
                    </div>
                </div>
            )
        })
    }

    renderCreateButton = () => {
        if (this.props.isSignedIn) {
            return (
                <div>
                    <Link to={"/todo/create"} className={"ui right floated primary button"}>Create Todo</Link>
                </div>
            )
        }
        return null;
    }

    render() {

        return (
            <div>
                <div className={"ui relaxed celled list"}>
                    {this.renderList()}
                </div>
                {this.renderCreateButton()}
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    const { isSignedIn, userId } = state.signedInUser;
    return {
        todos: Object.values(state.todos),
        isSignedIn: isSignedIn,
        userId: userId
    }
}

export default connect(mapStateToProps, { fetchAllTodos, authenticate })(TodoList);