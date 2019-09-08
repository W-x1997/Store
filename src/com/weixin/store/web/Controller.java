package com.weixin.store.web;

import com.weixin.store.dao.CustomerDao;
import com.weixin.store.dao.GoodsDao;
import com.weixin.store.dao.Imp.GoodsDaoImp;
import com.weixin.store.domain.Customer;
import com.weixin.store.domain.Goods;
import com.weixin.store.service.CustomerService;
import com.weixin.store.service.Imp.CustomerServiceImp;
import com.weixin.store.service.ServiceException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

//@javax.servlet.annotation.WebServlet(name = "Controller",urlPatterns = {"/controller"})
public class Controller extends javax.servlet.http.HttpServlet {

    DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    CustomerService customerDao=new CustomerServiceImp();
    GoodsDao goodsDao=new GoodsDaoImp();

    private  int totalPageNumber=0; //总页数
    private  int pageSize=0; //每页行数
    private  int currentPage=1; //当前页


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        pageSize=new Integer(config.getInitParameter("pageSize"));
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String action=request.getParameter("action");
        if("reg".equals(action)){
            //注册
            String userid=request.getParameter("userid");
            String name=request.getParameter("name");
            String password=request.getParameter("password");
            String password2=request.getParameter("password2");
            String birthday=request.getParameter("birthday");
            String address=request.getParameter("address");
            String phone=request.getParameter("phone");

            //服务器验证
            List<String> errors=new ArrayList<>();
            if (userid == null || userid.equals("")) {
                errors.add("客户账号不能为空！");
            }
            if (name == null || name.equals("")) {
                errors.add("客户姓名不能为空！");
            }
            if (password == null
                    || password2 == null
                    || !password.equals(password2)) {
                errors.add("两次输入的密码不一致！");
            }

            String pattern = "^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))$";
            if (!Pattern.matches(pattern, birthday)) {
                errors.add("出生日期格式无效！");
            }

            if (errors.size()>0){ //验证失败
                request.setAttribute("errors",errors);
                request.getRequestDispatcher("customer_reg").forward(request,response);
            }else {
                //验证成功
                Customer customer=new Customer();
                customer.setId(userid);
                customer.setPassword(password);
                customer.setName(name);
                customer.setAddress(address);
                customer.setPhone(phone);
                try {
                    Date d=dateFormat.parse(birthday);
                    customer.setBirthday(d);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //注册

                try {
                    customerDao.register(customer);
                    request.getRequestDispatcher("login.jsp");
                } catch (ServiceException e) {
                    errors.add("客户ID已经注册！");
                    request.setAttribute("errors",errors);
                    request.getRequestDispatcher("customer_reg.jsp").forward(request,response);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }

        }else if("login".equals(action)){
            //用户登录
            String userid=request.getParameter("userid");
            String password=request.getParameter("password");
            Customer customer=new Customer();
            customer.setId(userid);
            customer.setPassword(password);


            try {
                if(customerDao.login(customer)==true){
                    HttpSession session=request.getSession();
                    session.setAttribute("customer",customer);
                    request.getRequestDispatcher("main.jsp").forward(request,response);


                    }else{
                    List<String> errors=new ArrayList<>();
                    errors.add("账号密码不正确");

                    request.setAttribute("errors",errors);
                    request.getRequestDispatcher("login.jsp").forward(request,response);


                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else if("list".equals(action)){
            //商品列表
            try {
                List<Goods> goods=goodsDao.findAll();

                if(goods.size()%pageSize==0)
                    totalPageNumber=goods.size()/pageSize;
                else
                    totalPageNumber=goods.size()/pageSize+1;

                request.setAttribute("totalPageNumber",totalPageNumber);
                request.setAttribute("currentPage",currentPage);

                request.setAttribute("goodslist",goods.subList(0,currentPage*pageSize));
             //   request.setAttribute("goodslist ",goods);
                request.getRequestDispatcher("goods_list.jsp").forward(request,response);


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }




        }else if("paging".equals(action)){
            //商品列表分页
            String page=request.getParameter("page");
            if(page.equals("prev")){
                //向上翻页
                currentPage--;
                if(currentPage<1){
                 currentPage=1;
                }

            }else if(page.equals("next")){
                // 向下翻页
                currentPage++;
                if(currentPage>totalPageNumber){
                    currentPage=totalPageNumber;
                }


            }else {
                currentPage = Integer.parseInt(page);
            }
            int start=(currentPage-1)*pageSize;
            int end=currentPage*pageSize;

            List<Goods> goods= null;
            try {
                goods = goodsDao.findStartEnd(start,end);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


            request.setAttribute("totalPageNumber",totalPageNumber);
            request.setAttribute("currentPage",currentPage);

            request.setAttribute("goodslist",goods);
            //   request.setAttribute("goodslist ",goods);
            request.getRequestDispatcher("goods_list.jsp").forward(request,response);

        }


    }
}
