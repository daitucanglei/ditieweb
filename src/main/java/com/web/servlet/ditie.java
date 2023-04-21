package com.web.servlet;
import com.alibaba.fastjson.JSON;
import com.pojo.*;
import com.service.ditieService;
import com.service.impl.ditieimpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/ditie/*")
public class ditie extends servlet{
    private ditieService ditieServices=new ditieimpl();
    public void all(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用service查询
        List<ditiepojo> ditiepojos = ditieServices.selectAll();
        //2. 转为JSON
        String jsonString = JSON.toJSONString(ditiepojos);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    public void all1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用service查询
        List<ditiepojo> ditiepojos = ditieServices.selectAll();

        request.setAttribute("list",ditiepojos);
        request.getRequestDispatcher("/cha.jsp").forward(request,response);
    }
    public void ceshi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
     /*  String qdian = request.getParameter("qidian");
        String zdain = request.getParameter("zdain");;*/

        request.setCharacterEncoding("utf-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        lix lix = JSON.parseObject(params, lix.class);
        String qidian = lix.getQidian();
        String zdain1 = lix.getZdain();
        zhunbei zhunbei=new zhunbei();
        String zuiduan = zhunbei.zuiduan(qidian, zdain1);
        String[] s = zuiduan.split(" ");
        int b=s.length;
        List<zhandian1>zhandian1s=new ArrayList<zhandian1>();
        for(int i=0;i<b;i++)
        {
            zhandian1 zhandian1=new zhandian1();
            zhandian1.setZhandian1(s[i]);
            zhandian1s.add(zhandian1);
        }
        String jsonString = JSON.toJSONString(zhandian1s);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

       /* request.setAttribute("jl",zuiduan);
        request.getRequestDispatcher("/ceshi.jsp").forward(request,response);*/
    }
    public void tiaocha1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        ditiepojo ditiepojo = JSON.parseObject(params, ditiepojo.class);
        List<com.pojo.ditiepojo> tiaocha = ditieServices.tiaocha(ditiepojo);
        String s = JSON.toJSONString(tiaocha);
        //System.out.println(s);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        // 获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        //转为 Brand
        ditiepojo ditiepojo = JSON.parseObject(params, ditiepojo.class);
        //2. 调用service查询
        PageBean<ditiepojo> pageBean = ditieServices.selectByPageAndCondition(currentPage,pageSize,ditiepojo);
        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* List<zhandian> select = ditieServices.select();
       *//* for(zhandian u:select)
        {
            String[] s = u.getZhandian1().split(" ");
            int b=s.length;
            for(int i=0;i<b;i++)
            {
                ditiepojo ditiepojo=new ditiepojo(null,null,null,null);
                ditiepojo.setXianlu(u.getXianlu());
                ditiepojo.setZhandian(s[i]);
                ditieServices.add(ditiepojo);
            }
        }*/
        List<ditiepojo> ditiepojos = ditieServices.selectAll();
        String zhandain1[]=new String[100000];
        for(ditiepojo u:ditiepojos)
        {
            List<ditiepojo> qzcah = ditieServices.qzcah(u.getZhandian());
            int b=qzcah.size();
            if(b>1)
            {
                for(int i=0;i<b;i=i+1)
                {
                    for(int j=0;j<=b-1;j++)
                    {if(i!=j)
                    {
                        huanc huanc=new huanc(null,null,null);
                        huanc.setXianlu1(qzcah.get(i).getXianlu());
                        huanc.setGzhandain(qzcah.get(i).getZhandian());
                        huanc.setXianlu2(qzcah.get(j).getXianlu());
                        ditieServices.add1(huanc);
                    }

                    }

                }
            }
        }

    }
    public void zxhc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        lix lix = JSON.parseObject(params, lix.class);
        String qidian = lix.getQidian();
        String zdain1 = lix.getZdain();
        zhunbei zhunbei=new zhunbei();
        String zuiduan = zhunbei.zuiduan(qidian, zdain1);
        String zxlu = zhunbei.zxlu();
        String[] s = zxlu.split(" ");
        int b=s.length;List<lu> lus=new ArrayList<>();
        for(int i=0;i<b;i++)
        {
            lu lu=new lu();
            lu.setLu(s[i]);lus.add(lu);

        }
        String jsonString = JSON.toJSONString(lus);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}