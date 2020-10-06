import React from "react";
import { connect } from "react-redux";
import { fetchTodo } from "../../actions";

class TodoShow extends React.Component {

    componentDidMount() {
        this.props.fetchTodo(this.props.match.params.id);
    }

    renderTodo = () => {
        const { todo } = this.props;
        if (!todo) {
            return null;
        }
        return (
            <div className={"ui list"}>
                <div className={"item"}>
                    <i className={"right triangle icon"}/>
                    <div className={"content"}>
                        <div className={"header"}>Description</div>
                        <div className={"description"}>{todo.description}</div>
                    </div>
                </div>
                <div className={"item"}>
                    <i className={"right triangle icon"}/>
                    <div className={"content"}>
                        <div className={"header"}>Responsible</div>
                        <div className={"description"}>{todo.responsible}</div>
                    </div>
                </div>
                <div className={"item"}>
                    <i className={"right triangle icon"}/>
                    <div className={"content"}>
                        <div className={"header"}>Expires At</div>
                        <div className={"description"}>{new Date(todo.expiredAt).toString()}</div>
                    </div>
                </div>
                <div className={"item"}>
                    <i className={"right triangle icon"}/>
                    <div className={"content"}>
                        <div className={"header"}>Created By</div>
                        <div className={"description"}>{todo.createdByName}</div>
                    </div>
                </div>
                <div className={"item"}>
                    <i className={"right triangle icon"}/>
                    <div className={"content"}>
                        <div className={"header"}>Created At</div>
                        <div className={"description"}>{new Date(todo.createdAt).toString()}</div>
                    </div>
                </div>
                <div className={"item"}>
                    <i className={"right triangle icon"}/>
                    <div className={"content"}>
                        <div className={"header"}>Updated By</div>
                        <div className={"description"}>{todo.updatedByName}</div>
                    </div>
                </div>
                <div className={"item"}>
                    <i className={"right triangle icon"}/>
                    <div className={"content"}>
                        <div className={"header"}>Updated At</div>
                        <div className={"description"}>{new Date(todo.updatedAt).toString()}</div>
                    </div>
                </div>
            </div>
        )
    }

    render() {
        return (
          <div>
              {this.renderTodo()}
          </div>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return { todo: state.todos[ownProps.match.params.id] }
}

export default connect(mapStateToProps, { fetchTodo })(TodoShow);