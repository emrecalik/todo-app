import React from "react";
import {Field, reduxForm} from "redux-form";

class AuthForm extends React.Component {

    handleOnSubmit = (formValues) => {
        this.props.onSubmit(formValues);
    }

    render() {
        return (
          <form onSubmit={this.props.handleSubmit(this.handleOnSubmit)} className={"ui form"}>
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
                  <button className={"ui green button"}>Submit</button>
              </div>
          </form>
        );
    }
}

export default reduxForm({
    form: "signInForm"
})(AuthForm)