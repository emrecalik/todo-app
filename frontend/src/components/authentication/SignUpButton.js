import React from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";

class SignUpButton extends React.Component {

    render() {
        const isDisabled = this.props.isSignedIn === true ? "disabled" : "";

        return (
            <div>
                <Link to={"/user/signup"} className={`ui ${isDisabled} brown button`}>Sign Up</Link>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return { isSignedIn: state.signedInUser.isSignedIn }
}

export default connect(mapStateToProps)(SignUpButton);