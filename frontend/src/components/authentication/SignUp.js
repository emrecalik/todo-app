import React from "react";
import { connect } from "react-redux";
import SignUpForm from "./SignUpForm";
import { signUpAction } from "../../actions/auth";

class SignUp extends React.Component {

    handleOnSubmit = (formValues) => {
        this.props.signUpAction(formValues);
    }

    render() {
        return (
            <div>
                <SignUpForm onSubmit={this.handleOnSubmit}/>
            </div>
        );
    }
}

export default connect(null,{ signUpAction })(SignUp);