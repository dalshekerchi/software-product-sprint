package com.google.sps.servlets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/** Handles requests sent to the /fun-fact URL. Try running a server and navigating to /fun-fact! */
@WebServlet("/i-like")
public class JSONServlet extends HttpServlet {
    private static final ArrayList < String > iLike = new ArrayList < > (Arrays.asList(
        "skiing", " karaoking",
        "cooking (breakfast specialist)", " hiking with friends",
        "watching the sunrise"));
    Gson gson = new Gson();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;");

        Gson gson = new Gson();
        String json = gson.toJson(iLike);
        response.getWriter().println(json);
    }
}
