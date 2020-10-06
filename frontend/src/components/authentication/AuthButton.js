import React from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { signOutAction } from "../../actions/auth";

class AuthButton extends React.Component {

    handleSignOutClick = () => {
        this.props.signOutAction();
    }

    renderButton = () => {
        if (this.props.isSignedIn) {
            return <button className={`ui google plus button`} onClick={this.handleSignOutClick}>Sign Out</button>
        }
        return <Link to={"/user/signin"} className={`ui primary button`}>Sign In</Link>
    }

    render() {
        return (
            <div>
                {this.renderButton()}
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return { isSignedIn: state.signedInUser.isSignedIn }
}

export default connect(mapStateToProps, { signOutAction })(AuthButton);