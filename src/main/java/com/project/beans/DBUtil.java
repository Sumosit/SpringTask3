package com.project.beans;

import com.project.entites.Comments;
import com.project.entites.Posts;
import com.project.entites.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBUtil {

    private Connection con;

    public DBUtil(String driverClassName, String url,String user, String password){
        System.out.println("INITIALIZING DB BEAN");
        try{
            Class.forName(driverClassName);
            con = DriverManager.getConnection(url, user, password);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Register(Users users) {
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO users(email, fullname, password, re_password) VALUES (?, ?, ?, ?)");
            st.setString(1, users.getEmail());
            st.setString(2, users.getLogin());
            st.setString(3, users.getPassword());
            st.setString(4, users.getRepassword());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int ifAuth(String fullname, String password) {
        Users users = new Users();
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM users WHERE fullname=? and password=?");
            st.setString(1, fullname);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                users.setLogin(rs.getString("fullname"));
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (users.getLogin() == null) {
            return 0;
        }
        else if (users.getLogin().equals(fullname)) {
            return 1;
        }
        return 0;
    }

    public int ifAuth(String fullname) {
        Users users = new Users();
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM users WHERE fullname=?");
            st.setString(1, fullname);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                users.setLogin(rs.getString("fullname"));
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (users.getLogin() == null) {
            return 0;
        }
        else if (users.getLogin().equals(fullname)) {
            return 1;
        }
        return 0;
    }

    public void changePassword(String login, String password) {
        try {
            PreparedStatement st = con.prepareStatement("UPDATE users SET password=?, re_password=? WHERE fullname=?");
            st.setString(1, password);
            st.setString(2, password);
            st.setString(3, login);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeAuthorPostByNewLogin(String newlogin, String oldlogin) {
        try {
            PreparedStatement st = con.prepareStatement("UPDATE posts SET author=? WHERE author=?");
            st.setString(1, newlogin);
            st.setString(2, oldlogin);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeLogin(String newlogin, String oldlogin) {
        try {
            PreparedStatement st = con.prepareStatement("UPDATE users SET fullname=? WHERE fullname=?");
            st.setString(1, newlogin);
            st.setString(2, oldlogin);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Users getSingleUserByLogin(String login) {
        Users users = null;
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM users WHERE fullname=?");
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                users = new Users(
                        rs.getString("email"),
                        rs.getString("fullname"),
                        rs.getString("password"),
                        rs.getString("repassword"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public ArrayList<Posts> getAllPosts() {
        ArrayList<Posts> posts = new ArrayList<>();
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM posts");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                posts.add(new Posts(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getTimestamp(5)));
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    public void addSinglePost(String author, String title, String content) {
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO posts VALUES (null, ?, ?, ?, null)");
            st.setString(1, author);
            st.setString(2, title);
            st.setString(3, content);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSinglePost(int id) {
        try {
            PreparedStatement st = con.prepareStatement("DELETE FROM posts WHERE id=?");
            st.setInt(1, id);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editSinglePost(String title, String content, int id) {
        try {
            PreparedStatement st = con.prepareStatement("UPDATE posts SET title=?, content=? WHERE id=?");
            st.setString(1, title);
            st.setString(2, content);
            st.setInt(3, id);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(title + " " + content + " " + id);
    }

    public ArrayList<Posts> getSinglePost(int id) {
        ArrayList<Posts> posts = new ArrayList<>();
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM posts WHERE id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                posts.add(new Posts(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getTimestamp(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    public void addCommentByPostId(String login, int postId, String comment) {
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO comments VALUES (null, ?, ?, ?, null )");
            st.setInt(1, postId);
            st.setString(2, login);
            st.setString(3, comment);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Comments> getAllCommentsByPostId(int postId) {
        ArrayList<Comments> comments = new ArrayList<>();
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM comments WHERE postId=?");
            st.setString(1, String.valueOf(postId));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                comments.add(new Comments(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getTimestamp(5)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    public Comments getSingleComment(int id) {
        Comments comment = new Comments();
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM comments WHERE id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                comment.setId(rs.getInt(1));
                comment.setPostId(rs.getInt(2));
                comment.setAuthor(rs.getString(3));
                comment.setComment(rs.getString(4));
                comment.setDate(rs.getTimestamp(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comment;
    }

    public void editSingleComment(int id, String comment) {
        try {
            PreparedStatement st = con.prepareStatement("UPDATE comments SET comment=? WHERE id=?");
            st.setString(1, comment);
            st.setInt(2, id);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSingleComment(int id) {
        try {
            PreparedStatement st = con.prepareStatement("DELETE FROM comments WHERE id=?");
            st.setInt(1, id);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
