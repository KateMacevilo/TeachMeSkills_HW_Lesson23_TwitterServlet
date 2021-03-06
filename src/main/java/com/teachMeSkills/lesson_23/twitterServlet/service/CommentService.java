package com.teachMeSkills.lesson_23.twitterServlet.service;

import com.teachMeSkills.lesson_23.twitterServlet.entity.Comment;
import com.teachMeSkills.lesson_23.twitterServlet.storage.CommentStorage;

public class CommentService {

    private final CommentStorage commentStorage = new CommentStorage();

    public int getCommentID() {
        return commentStorage.getCommentId();
    }

    public boolean addComment(Comment comment) {
        return commentStorage.addComment(comment);
    }

    public boolean deleteComment(int commentId, String userLogin) {
        return commentStorage.deleteCommentById(commentId, userLogin);
    }

    public String getLoginById(int commentId){
        return commentStorage.getLoginById(commentId);
    }

    public boolean editComment(int commentId, String userLogin, String text){
        return commentStorage.editCommentById(commentId, userLogin, text);
    }

    public Comment getCommentById(int idComment){
        return commentStorage.getCommentById(idComment);
    }

}
