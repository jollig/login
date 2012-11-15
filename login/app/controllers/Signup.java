package controllers;

import is.ru.honn.rupin.data.UserDataGateway;
import is.ru.honn.rupin.domain.User;
import is.ru.honn.rupin.domain.UserRegistration;
import play.data.Form;
import play.mvc.*;

import views.html.signup.*;

public class Signup extends RuPinController {
    final static Form<UserRegistration> signupForm = form(UserRegistration.class);

    public static Result blank() {
        return ok(form.render(signupForm));
    }

    public static void submit() {
        Form<UserRegistration> filledForm = signupForm.bindFromRequest();

        if (!"true".equals(filledForm.field("accept").value()))
        {
            filledForm.reject("accept", "You must accept the terms and conditions");
        }

        if (filledForm.field("username").value().length () < 4)
            filledForm.reject("username", "Username must be at least 4 characters");

        if (!filledForm.field("password").value().equals(filledForm.field("repeatPassword").value()))
        {
            filledForm.reject("repeatPassword", "Password does not match");
        }

        if (filledForm.hasErrors())
        {
            return badRequest(form.render(filledForm));
        }
        else
        {
            User created = filledForm.get();

            UserDataGateway userDataGateway = (UserDataGateway)ctx.getBean("userDataGateway");
            userDataGateway.add(created);

            return ok(summary.render(created));
        }
    }
}
