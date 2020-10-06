import React from "react";
import { connect } from "react-redux";
import { Modal, Button } from "semantic-ui-react";
import { deleteTodo, fetchTodo } from "../../actions";
import history from "../../history";

class TodoDelete extends React.Component {

    componentDidMount() {
        const token = localStorage.getItem("jwt");
        if (token) {
            this.props.fetchTodo(this.props.match.params.id);
        }
    }

    handleDelete = () => {
        this.props.deleteTodo(this.props.match.params.id);
    }

    handleCancel = () => {
        history.push("/");
    }

    render() {
        const {todo, currentUserId} = this.props;

        if (!todo) {
            return null;
        }

        if (todo.createdById !== currentUserId) {
            return <h1>You are not authorized!</h1>
        }

        return (
            <Modal
                open={true}
            >
                <Modal.Header>Delete Todo?</Modal.Header>
                <Modal.Content>
                    Are you sure you want to delete this todo?
                </Modal.Content>
                <Modal.Actions>
                    <Button positive onClick={() => this.handleDelete()}>
                        Agree
                    </Button>
                    <Button negative onClick={() => this.handleCancel()}>
                        Disagree
                    </Button>
                </Modal.Actions>
            </Modal>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        todo: state.todos[ownProps.match.params.id],
        currentUserId: state.signedInUser.userId
    }
}

export default connect(mapStateToProps, { deleteTodo, fetchTodo })(TodoDelete);