import React from "react";
import Header from "./Header";
import { Router, Route } from "react-router-dom";
import { connect } from "react-redux";

import SignUp from "./authentication/SignUp";
import SignIn from "./authentication/Auth";
import TodoList from "./todos/TodoList";
import history from "../history";
import TodoShow from "./todos/TodoShow";
import TodoCreate from "./todos/TodoCreate";
import TodoDelete from "./todos/TodoDelete";
import TodoEdit from "./todos/TodoEdit";
import { authenticate } from "../actions/auth";

class App extends React.Component {

    componentDidMount() {
        const token = localStorage.getItem("jwt");
        if (token) {
            this.props.authenticate();
        }
    }

    render() {
        return (
            <div className={"ui container"} style={{marginTop:"10px"}}>
                <Router history={history}>
                    <Header/>
                    <Route path={["/", "/todos"]} exact component={TodoList}/>
                    <Route path={"/user/signup"} exact component={SignUp}/>
                    <Route path={"/user/signin"} exact component={SignIn}/>
                    <Route path={"/todo/show/:id"} exact component={TodoShow}/>
                    <Route path={"/todo/create"} exact component={TodoCreate}/>
                    <Route path={"/todo/delete/:id"} exact component={TodoDelete}/>
                    <Route path={"/todo/edit/:id"} exact component={TodoEdit}/>
                </Router>
            </div>
        );
    }
}

export default connect(null, { authenticate })(App);