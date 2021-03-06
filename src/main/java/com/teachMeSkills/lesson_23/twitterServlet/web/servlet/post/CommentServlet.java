package com.teachMeSkills.lesson_23.twitterServlet.web.servlet.post;

import com.teachMeSkills.lesson_23.twitterServlet.entity.Comment;
import com.teachMeSkills.lesson_23.twitterServlet.entity.Role;
import com.teachMeSkills.lesson_23.twitterServlet.entity.User;
import com.teachMeSkills.lesson_23.twitterServlet.service.CommentService;
import com.teachMeSkills.lesson_23.twitterServlet.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addComment")
public class CommentServlet extends HttpServlet {

    private final PostService postService = new PostService();
    private final CommentService commentService = new CommentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int idPost = Integer.parseInt(req.getParameter("idPost"));
        int idComment = commentService.getCommentID();
        String text = req.getParameter("text");
        User user = (User) req.getSession().getAttribute("user");

        Comment comment = new Comment(idComment, text, user.getLogin());
        commentService.addComment(comment);

        boolean isAddedComment = postService.addCommentToPost(idPost, comment);

        if (isAddedComment) {
            resp.getWriter().println("Success");
        } else {
            resp.getWriter().println("Error");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int idComment = Integer.parseInt(req.getParameter("idComment"));
        int idPost = Integer.parseInt(req.getParameter("idPost"));
        User user = (User) req.getSession().getAttribute("user");
        boolean isDeleted = false;

        Comment comment = new Comment();
        comment = commentService.getCommentById(idComment);

        if (user.getRole().equals(Role.ADMIN)) {
            String loginUser = commentService.getLoginById(idComment);
            isDeleted = postService.deleteComment(idPost, comment, loginUser);
        } else {
            isDeleted = postService.deleteComment(idPost, comment, user.getLogin());
        }

        if (isDeleted) {
            resp.getWriter().println("Success");
        } else {
            resp.getWriter().println("Error");
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int idComment = Integer.parseInt(req.getParameter("idComment"));
        String text = req.getParameter("text");
        User user = (User) req.getSession().getAttribute("user");
        boolean isEdited = false;

        if (user.getRole().equals(Role.ADMIN)) {
            String loginUser = commentService.getLoginById(idComment);
            isEdited = commentService.editComment(idComment, loginUser, text);
        } else {
            isEdited = commentService.editComment(idComment, user.getLogin(), text);
        }

        if (isEdited) {
            resp.getWriter().println("Success");
        } else {
            resp.getWriter().println("Error");
        }
    }
}
