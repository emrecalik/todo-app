import React from "react";
import SignInForm from "./AuthForm";
import { connect } from "react-redux";
import { signInAction } from "../../actions/auth";

class Auth extends React.Component {

    handleOnSubmit = (formValues) => {
        this.props.signInAction(formValues);
    }

    render() {
        return (
            <div>
                <SignInForm onSubmit={this.handleOnSubmit}/>
            </div>
        );
    }
}

export default connect(null,{ signInAction })(Auth);