// imports
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

//class App.java
public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    ProcessBuilder process = new ProcessBuilder();
    Integer port;
    if (process.environment().get("PORT") !=null) {
      port = Integer.parseInt(process.environment().get("PORT"));
    } else {
      port = 4567;
    }

    setPort(port);

 // creating a root route in App.java file that will render our home page
  get("/", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("stylists", Stylist.all());
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // creating a root route in App.java file that will render our about page
 get("/about", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("stylists", Stylist.all());
    model.put("template", "templates/about.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // creating a root route in App.java file that will render our about page
  get("/contact", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("stylists", Stylist.all());
    model.put("template", "templates/contact.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // route responsible for rendering the template with the new-client form
  get("/clients/new", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("stylists", Stylist.all());
    model.put("template", "templates/client-form.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // route to display all clients
  get("/clients", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("clients", Client.all());
    model.put("template", "templates/clients.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // relating stylist to the clients
    get("/stylists/:id/clients/new", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
        model.put("stylist", stylist);
        model.put("template", "templates/stylist-clients-form.vtl");
        return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


  }
}
