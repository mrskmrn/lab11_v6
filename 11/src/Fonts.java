package z11;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Fonts extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String font_size = (String) req.getParameter("font_size");
        int size;
        try{
            size = Integer.parseInt(font_size);
        }
        catch(Exception ex){
            req.setAttribute("error","Incorrect size data");
            req.getRequestDispatcher("Output.jsp").forward(req, resp);
            return;
        }
        String string_amount = (String) req.getParameter("string_amount");
        int amount;
        try{
            amount = Integer.parseInt(string_amount);
        }
        catch(Exception ex){
            req.setAttribute("error","Incorrect amount data");
            req.getRequestDispatcher("Output.jsp").forward(req, resp);
            return;
        }
        List<String> list_ = new ArrayList<>();
        InputStream input = getServletContext().getResourceAsStream("file.txt");
        BufferedReader buf = new BufferedReader(new InputStreamReader(input));
        String strLine;
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while ((strLine = buf.readLine())!=null) {
            list_.add(strLine);
            i++;
        }
        if (amount>i)
            req.setAttribute("error","Not enough strings in file");
        else{
            req.setAttribute("text", list_);
            req.setAttribute("size", font_size);
            req.setAttribute("amount", amount);
        }
        req.getRequestDispatcher("Output.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}

