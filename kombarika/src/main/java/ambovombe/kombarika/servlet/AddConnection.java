package ambovombe.kombarika.servlet;

import ambovombe.kombarika.database.DbProperties;
import ambovombe.kombarika.generator.CodeGenerator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addConnection", value = "/addConnection")
public class AddConnection extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String connectionName = req.getParameter("connectionName");
        String dataSource = req.getParameter("dataSource");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String type = req.getParameter("databaseType");
        PrintWriter out = resp.getWriter();

        try {
            DbProperties dbProperties = new DbProperties();
            dbProperties.setDatabase(type);
            dbProperties.setDatasource(dataSource);
            dbProperties.setUsername(username);
            dbProperties.setPassword(password);
            out.println("<html><body>");
            out.println("<p>" + type + "</p>"+"<p>" + dataSource + "</p>"+"<p>" + username + "</p>"+"<p>" + password + "</p>"+"<p>" + connectionName + "</p>");
            out.println("</body></html>");
            dbProperties.addConnection(connectionName);
        } catch (Exception e) {
            out.println(e.getMessage());
        }

    }
}
