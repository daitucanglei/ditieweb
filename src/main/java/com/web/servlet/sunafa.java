package com.web.servlet;
import com.pojo.ditiepojo;
import com.pojo.huanc;
import com.service.ditieService;
import com.service.impl.ditieimpl;
import java.util.List;
public class sunafa {
    private ditieService ditieServices=new ditieimpl();
    /*public  String jl[] =new String[10000];*/
    public String lu="";public String lz="";
    public String hx(String xianlu1,String xianlu2)
    {
        List<huanc> hcha = ditieServices.hcha(xianlu1);
        int size = hcha.size();
        for(int i=0;i<size;i++)
        {
            System.out.println(hcha.get(i).getXianlu2());
            if(hcha.get(i).getXianlu2().equals(xianlu2))
            {
                lu=lu+xianlu1+" "+hcha.get(i).getXianlu2()+";";
                continue;
                // return 1;
            }
            else{
                lu=lu+hcha.get(i).getXianlu1()+" ";
                String[] s = lu.split(" ");
               // System.out.println(s[0]);
                if(hcha.get(i).getXianlu2().equals(s[0]))
                {
                    continue;
                }
                hx(hcha.get(i).getXianlu2(),xianlu2);
            }
        }
        return lu;
    }

}

