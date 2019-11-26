package com.project.db;

import java.util.ArrayList;

public class DBManager {

    private static ArrayList<News> news = new ArrayList<>();
    private static Long id = 6L;
    static{

        news.add(new News(1L, "News 1", "Content 1"));
        news.add(new News(2L, "News 2", "Content 2"));
        news.add(new News(3L, "News 3", "Content 3"));
        news.add(new News(4L, "News 4", "Content 4"));
        news.add(new News(5L, "News 5", "Content 5"));

    }

    public static ArrayList<News> getAllNews(){
        return news;
    }

    public static News getNews(Long id){
        for(News n: news){
            if(n.getId()==id) return n;
        }
        return null;
    }

    public static void addNews(News n){
        n.setId(id);
        id++;
        news.add(n);
    }
}
