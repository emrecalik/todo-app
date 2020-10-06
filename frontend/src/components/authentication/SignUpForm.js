import React from "react";
import { Field, reduxForm } from "redux-form";

class SignUpForm extends React.Component {

    handleOnSubmit = (formValues) => {
        this.props.onSubmit(formValues);
    }

    render() {
        return (
            <form onSubmit={this.props.handleSubmit(this.handleOnSubmit)} className={"ui form"}>
                <div className={"field"}>
                    <label>Name</label>
                    <Field
                        name={"name"}
                        component={"input"}
                        type={"text"}
                    />
                </div>
                <div className={"field"}>
                    <label>Username</label>
                    <div>
                        <Field
                            name={"userName"}
                            type={"text"}
                            component={"input"}
                        />
                    </div>
                </div>
                <div className={"field"}>
                    <label>Email</label>
                    <div>
                        <Field
                            name={"email"}
                            type={"text"}
                            component={"input"}
                        />
                    </div>
                </div>
                <div className={"field"}>
                    <label>Role</label>
                    <div>
                        <Field name="role" component="select">
                            <option />
                            <option value="user">User</option>
                            <option value="admin">Admin</option>
                        </Field>
                    </div>
                </div>
                <div className={"field"}>
                    <label>Password</label>
                    <div>
                        <Field
                            name={"password"}
                            component={"input"}
                            type={"text"}
                        />
                    </div>
                </div>
                <div>
                    <button className={"ui primary button"}>Submit</button>
                </div>
            </form>
        );
    }
}

export default reduxForm({
    form: "signUpForm"
})(SignUpForm)