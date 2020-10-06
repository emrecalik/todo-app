import React from "react";
import { Link } from "react-router-dom";
import AuthButton from "./authentication/AuthButton";
import SignUpButton from "./authentication/SignUpButton";

class Header extends React.Component {

    render() {
        return (
            <div className={"ui secondary pointing menu"}>
                <Link to={"/"} className={"item"}>To-Do APP</Link>
                <div className={"right menu"}>
                    <Link to={"/todos"} className={"item"}>All Todos</Link>
                </div>
                <AuthButton/>
                <SignUpButton/>
            </div>
        );
    }
}

export default Header;

