package controllers;

import play.mvc.Result;
import views.html.index;

public class Application extends RuPinController {
  
  public static Result index() {
    return ok(index.render("Your new application is ready."));
  }
  
}