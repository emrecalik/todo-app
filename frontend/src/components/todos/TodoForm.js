import React from "react";
import {Field, reduxForm} from "redux-form";


class TodoForm extends React.Component {

    handleOnSubmit = (formValues) => {
        this.props.onSubmit(formValues);
    }

    render() {
        return (
            <form onSubmit={this.props.handleSubmit(this.handleOnSubmit)} className={"ui form"}>
                <div className={"field"}>
                    <label>Todo Description</label>
                    <div>
                        <Field
                            name={"description"}
                            type={"text"}
                            component={"input"}
                        />
                    </div>
                </div>
                <div className={"two fields"}>
                    <div className={"field"}>
                        <label>Todo Days</label>
                        <div>
                            <Field
                                name={"todoDays"}
                                type={"text"}
                                component={"input"}
                            />
                        </div>
                    </div>
                    <div className={"field"}>
                        <label>Todo Hours</label>
                        <div>
                            <Field
                                name={"todoHours"}
                                type={"text"}
                                component={"input"}
                            />
                        </div>
                    </div>
                </div>
                <div>
                    <button className={"ui green button"}>Submit</button>
                </div>
            </form>
        )
    }
}

export default reduxForm({
    form: "todoForm"
})(TodoForm)