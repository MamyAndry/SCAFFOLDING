package ambovombe.kombarika.servlet;

import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.generator.CodeGenerator;
import ambovombe.kombarika.generator.service.DbService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "generate", value = "/generate")
public class Generate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        String viewPath = req.getParameter("viewPath");
        String framework = req.getParameter("framework");
        String packageName = req.getParameter("packageName");
        String entity = req.getParameter("entity");
        String controller = req.getParameter("controller");
        String repository = req.getParameter("repository");
        String view = req.getParameter("view");
        String viewType = req.getParameter("viewType");
        String url = req.getParameter("url");
        String connection = req.getParameter("connection");

        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<p>" + path + "</p>"+"<p>" + viewPath + "</p>"+"<p>" + framework + "</p>"+"<p>" + packageName + "</p>"+"<p>" + entity + "</p>"+"<p>" + controller + "</p>"+"<p>" + repository + "</p>"+"<p>" + view + "</p>"+"<p>" + viewType + "</p>"+"<p>" + url + "</p>"+"<p>" + connection + "</p>");
        out.println("</body></html>");
        CodeGenerator codeGenerator = null;
        try {
            codeGenerator = new CodeGenerator();
            System.out.println(DbService.getPrimaryKey(codeGenerator.getDbConnection(), DbService.getAllTablesArrays(codeGenerator.getDbConnection().connect(connection))[0]).get(0));
            codeGenerator.generateAll(path, viewPath, packageName, entity, controller, repository, view, viewType, url, DbService.getAllTablesArrays(codeGenerator.getDbConnection().connect(connection)), framework);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                assert codeGenerator != null;
                codeGenerator.getDbConnection().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
