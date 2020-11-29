package Login;

import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

import java.net.URL;

//http://localhost:3466/AddProduct/?id=7&name=name7&organization=org7&quantity=700
//http://localhost:3466/ListProducts

@SuppressWarnings("NotNullNullableValidation")
public final class MainHandler {

  public static void main(String[] args) throws Exception {
    final Server server = new DefaultServer().build();

    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
    context.setContextPath("/");
    final URL resource = LoginService.class.getResource("/static");
    context.setBaseResource(Resource.newResource(resource.toExternalForm()));
    context.setWelcomeFiles(new String[]{"/static/Info"});

    final String hashConfig = MainHandler.class.getResource("/hash_config").toExternalForm();
    final HashLoginService hashLoginService = new HashLoginService("login", hashConfig);
    final ConstraintSecurityHandler securityHandler = new SecurityHandlerBuilder().build(hashLoginService);
//    server.addBean(hashLoginService);

    context.addServlet(HttpServletDispatcher.class, "/");
    context.addEventListener(new GuiceListener());
    securityHandler.setHandler(context);

    server.setHandler(context);

    server.start();
  }
}
