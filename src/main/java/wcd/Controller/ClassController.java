package wcd.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wcd.entity.Classroom;
import wcd.service.ClassroomService;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/classes")
public class ClassController extends HttpServlet {
    private ClassroomService classroomService;
    @Override
    public void init() throws ServletException {
        super.init();
        classroomService = new ClassroomService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null || action.isEmpty()) action = "list";
        switch (action){
            case "new": formNewClassroom(req,resp); break;
            case "edit": formEditClassroom(req,resp);break;
            case "list": showListClasses(req,resp); break;

        }
    }

    private void showListClasses(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Classroom> list = classroomService.getAllClasses();
        req.setAttribute("classes",list);
        req.getRequestDispatcher("classroom/list.jsp").forward(req,resp);
    }


    private void formNewClassroom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.getRequestDispatcher("classroom/new.jsp").forward(req,resp);
    }

    private void formEditClassroom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Classroom classroom = classroomService.getClassroomById(id);
            if(classroom == null){
                throw new Exception("Class not found!");
            }
            req.setAttribute("classroom",classroom);
            req.getRequestDispatcher("classroom/edit.jsp").forward(req,resp);
        }catch (Exception e){
            resp.sendRedirect("classes?action=list");
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "insert":insertClassroom(req,resp); break;
            case "update": updateClassroom(req,resp);break;
            case "delete": deleteClassroom(req,resp);break;
        }
    }

    private void insertClassroom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            Classroom classroom = new Classroom();
            classroom.setName(name);
            classroomService.saveClassroom(classroom);
            resp.sendRedirect("classes?action=list");
        }catch (Exception e){
            formNewClassroom(req,resp);
        }

    }

    private void updateClassroom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            Classroom classroom = classroomService.getClassroomById(id);
            if(classroom == null){
                throw new Exception("Classroom not found!");
            }
            // update
            classroom.setName(name);
            classroomService.updateClassroom(classroom);
            resp.sendRedirect("classes?action=list");
        }catch (Exception e){
            resp.sendRedirect("classes?action=edit&id="+req.getParameter("id"));
        }
    }
    private void deleteClassroom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Classroom classroom = classroomService.getClassroomById(id);
            if(classroom == null){
                throw new Exception("Classroom not found!");
            }
            classroomService.deleteClassroom(id);
            resp.sendRedirect("classes?action=list");
        }catch (Exception e){
            resp.sendRedirect("classes?action=edit&id="+req.getParameter("id"));
        }
    }
}
