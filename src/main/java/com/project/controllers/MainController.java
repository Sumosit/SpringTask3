package com.project.controllers;
import com.project.beans.DBUtil;
import com.project.beans.DatabaseBean;
import com.project.entites.Comments;
import com.project.entites.Posts;
import com.project.entites.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@Controller
@RequestMapping("/task3")
public class MainController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    DatabaseBean dbBean;
    @Autowired
    DBUtil dbUtilBean;
//    @Autowired
//    NotesRep notesRep;


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        request.getSession().invalidate();
        return "redirect:/task3/login";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView getRegister() {
        ModelAndView mw = new ModelAndView("register");
        return mw;
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView postRegister(@RequestParam(value = "email") String email,
                             @RequestParam(value = "fullname") String fullname,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "repassword") String repassword) {
        ModelAndView mw = new ModelAndView("register");
        request.getSession().invalidate();
        if (dbUtilBean.ifAuth(fullname) == 0 && password.equals(repassword)) {
            mw.addObject("status", "True");
            request.getSession().setAttribute("user_data", new Users(null, password, null, fullname));
            Users users = new Users(email, fullname, password, repassword);
            dbUtilBean.Register(users);
            return mw;
        } else {
            mw.addObject("status", "False");
            return mw;
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLogin() {
        ModelAndView mw = new ModelAndView("login");
        return mw;
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView postLogin(@RequestParam(value = "login") String login,
                                  @RequestParam(value = "password") String password) {
        ModelAndView mw = new ModelAndView("login");
        request.getSession().invalidate();
        if (dbUtilBean.ifAuth(login, password) == 1) {
            mw.addObject("status", "True");
            request.getSession().setAttribute("user_data", new Users(null, password, null, login));
            return mw;
        } else {
            mw.addObject("status", "False");
            return mw;
        }
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile() {
        ModelAndView mw = new ModelAndView("profile");
        Users user = (Users) request.getSession().getAttribute("user_data");
        mw.addObject("user", user);
        return mw;
    }
    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public String changePassword(@RequestParam(value = "oldpassword") String oldp,
                                 @RequestParam(value = "newpassword") String newp,
                                 @RequestParam(value = "renewpassword") String renewp) {
        Users user = (Users) request.getSession().getAttribute("user_data");
        String login = user.getLogin();
        String password = user.getPassword();
        if (password.equals(oldp) && newp.equals(renewp)) {
            dbUtilBean.changePassword(login, newp);
            request.getSession().setAttribute("user_data", new Users(null, newp, newp, login));
        }
        return "redirect:/task3/profile";
    }
    @RequestMapping(value = "/changelogin", method = RequestMethod.POST)
    public String changeLogin(@RequestParam(value = "login") String newlogin) {
        Users user = (Users) request.getSession().getAttribute("user_data");
        String oldlogin = user.getLogin();
        dbUtilBean.changeLogin(newlogin, oldlogin);
        dbUtilBean.changeAuthorPostByNewLogin(newlogin, oldlogin);
        user.setLogin(newlogin);
        request.getSession().setAttribute("user_data", user);
        return "redirect:/task3/profile";
    }
    //////////////////////////////////
    // Posts
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getPosts() {
        ModelAndView mw = new ModelAndView("posts");
        ArrayList<Posts> posts = dbUtilBean.getAllPosts();
        Users user = (Users) request.getSession().getAttribute("user_data");

        mw.addObject("user", user);
        mw.addObject("posts", posts);
        return mw;
    }
    @RequestMapping(value = "/addpost", method = RequestMethod.POST)
    public String addPost(@RequestParam(value = "title") String title,
                          @RequestParam(value = "content") String content) {
        Users users = (Users) request.getSession().getAttribute("user_data");
        dbUtilBean.addSinglePost(users.getLogin(), title, content);
        return "redirect:/task3/posts";
    }
    @RequestMapping(value = "/deletepost", method = RequestMethod.POST)
    public String deletePost(@RequestParam(value = "id") int id) {
        dbUtilBean.deleteSinglePost(id);
        return "redirect:/task3/posts";
    }
    @RequestMapping(value = "/editpost", method = RequestMethod.GET)
    public ModelAndView getEditPost(@RequestParam(value = "id") int id) {
        ModelAndView mw = new ModelAndView("editPost");
        ArrayList<Posts> posts = dbUtilBean.getSinglePost(id);
        mw.addObject("posts", posts);
        return mw;
    }
    @RequestMapping(value = "/editpost", method = RequestMethod.POST)
    public ModelAndView postEditPost(@RequestParam(value = "id") int id) {
        ModelAndView mw = new ModelAndView("editPost");
        ArrayList<Posts> posts = dbUtilBean.getSinglePost(id);
        mw.addObject("posts", posts);
        return mw;
    }
    @RequestMapping(value = "/editpostdo", method = RequestMethod.POST)
    public String doEditPost(@RequestParam(value = "id") int id,
                             @RequestParam(value = "title") String title,
                             @RequestParam(value = "content") String content) {
        dbUtilBean.editSinglePost(title, content, id);
        return "redirect:/task3/posts";
    }
    ////////////////////////////////
    // Comments
    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public ModelAndView getComments(@RequestParam(value = "id") int id) {
        ModelAndView mw = new ModelAndView("comments");
        ArrayList<Posts> posts = dbUtilBean.getSinglePost(id);
        ArrayList<Comments> comments = dbUtilBean.getAllCommentsByPostId(id);
        Users user = (Users) request.getSession().getAttribute("user_data");

        mw.addObject("posts", posts);
        mw.addObject("comments", comments);
        mw.addObject("user", user);
        return mw;
    }
    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    public ModelAndView postComments(@RequestParam(value = "id") int id) {
        ModelAndView mw = new ModelAndView("comments");
        ArrayList<Posts> posts = dbUtilBean.getSinglePost(id);
        ArrayList<Comments> comments = dbUtilBean.getAllCommentsByPostId(id);
        Users user = (Users) request.getSession().getAttribute("user_data");

        mw.addObject("user", user);
        mw.addObject("comments", comments);
        mw.addObject("posts", posts);
        return mw;
    }
    @RequestMapping(value = "/docomments", method = RequestMethod.POST)
    public String doComments(@RequestParam(value = "login") String login,
                             @RequestParam(value = "postId") int postId,
                             @RequestParam(value = "comment") String comment) {
        dbUtilBean.addCommentByPostId(login, postId, comment);

        return "redirect:/task3/comments?id="+postId;
    }
    @RequestMapping(value = "/editcomment", method = RequestMethod.GET)
    public ModelAndView getEditComment(@RequestParam(value = "id") int id,
                                       @RequestParam(value = "postId") int postId) {
        ModelAndView mw = new ModelAndView("editComment");
        Comments comment = dbUtilBean.getSingleComment(id);
        ArrayList<Posts> posts = dbUtilBean.getSinglePost(postId);
        mw.addObject("comment", comment);
        mw.addObject("posts", posts);

        return mw;
    }
    @RequestMapping(value = "/editcomment", method = RequestMethod.POST)
    public ModelAndView postEditComment(@RequestParam(value = "id") int id,
                                        @RequestParam(value = "postId") int postId) {
        ModelAndView mw = new ModelAndView("editComment");
        Comments comment = dbUtilBean.getSingleComment(id);
        ArrayList<Posts> posts = dbUtilBean.getSinglePost(postId);
        mw.addObject("comment", comment);
        mw.addObject("posts", posts);
        return mw;
    }
    @RequestMapping(value = "/editcommentdo", method = RequestMethod.POST)
    public String doEditPost(@RequestParam(value = "id") int id,
                             @RequestParam(value = "comment") String comment,
                             @RequestParam(value = "postId") int postId) {
        dbUtilBean.editSingleComment(id, comment);
        return "redirect:/task3/comments?id="+postId;
    }
    @RequestMapping(value = "/deletecomment", method = RequestMethod.POST)
    public String deleteComment(@RequestParam(value = "id") int id,
                                @RequestParam(value = "postId") int postId) {
        dbUtilBean.deleteSingleComment(id);
        return "redirect:/task3/comments?id="+postId;
    }

    //////////////////////////////////
    // Notes
//    @RequestMapping(value = "/notes", method = RequestMethod.GET)
//    public ModelAndView notesGet() {
//        ModelAndView mw = new ModelAndView("notes");
//        Iterable<Notes> notes = notesRep.findAll();
//        mw.addObject("notes", notes);
//        return mw;
//    }
//    @RequestMapping(value = "/addnote", method = RequestMethod.POST)
//    public String addNote(@RequestParam(value = "content") String content) {
//        Users users = (Users) request.getSession().getAttribute("user_data");
//        return "redirect:/task3/posts";
//    }
}
