package com.weixin.store.web;

import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import com.weixin.store.dao.CustomerDao;
import com.weixin.store.dao.GoodsDao;
import com.weixin.store.dao.Imp.GoodsDaoImp;
import com.weixin.store.dao.Imp.OrderDaoImp;
import com.weixin.store.dao.OrderDao;
import com.weixin.store.domain.Customer;
import com.weixin.store.domain.Goods;
import com.weixin.store.service.CustomerService;
import com.weixin.store.service.Imp.CustomerServiceImp;
import com.weixin.store.service.Imp.OrderServiceImp;
import com.weixin.store.service.OrderService;
import com.weixin.store.service.ServiceException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

//@javax.servlet.annotation.WebServlet(name = "Controller",urlPatterns = {"/controller"})
public class Controller extends javax.servlet.http.HttpServlet {

    DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    CustomerService customerDao=new CustomerServiceImp();
    GoodsDao goodsDao=new GoodsDaoImp();
   OrderService orderService=new OrderServiceImp();

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

                int start=(currentPage-1)*pageSize;
                int end=currentPage*pageSize;
                if(currentPage==totalPageNumber){
                    end=goods.size();  //最后一页 特殊
                }

                request.setAttribute("goodslist",goods.subList(start,end));
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

        }else if("detail".equals(action)){
            //商品详情
            String goodid=request.getParameter("id");

            Goods goods= null;
            try {
                goods = goodsDao.findByPK(new Long(goodid));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            request.setAttribute("goods",goods);
            request.getRequestDispatcher("goods_detail.jsp").forward(request,response);



        }else if("add".equals(action)){
            String goodid=request.getParameter("id");
            String name=request.getParameter("name");
            Float price=new Float(request.getParameter("price"));

            //购物车添加  购物车结构是List中包含Map，每一个Map是一个商品
            //丛Session中取数据
            List<Map<String,Object>> cart=(List<Map<String,Object>>) request.getSession().getAttribute("cart");

            if(cart==null){
                cart=new ArrayList<Map<String,Object>>();
                request.getSession().setAttribute("cart",cart);
            }


            int flag=0;

            //购物车中有选择商品
            for(Map<String,Object> item:cart){
                String goodsid2=(String)item.get("goodsid");
                if(goodid.equals(goodsid2)){
                    Integer quantity=(Integer)item.get("quantity");
                    quantity++;
                    item.put("quantity",quantity);
                    flag++;
                }

            }



            //购车车中没有选择的商品
           if(flag==0){
               Map<String,Object> item=new HashMap<>();
               //item结构 Map [商品id，商品名称，商品数量]
               item.put("goodsid",goodid);
               item.put("goodsname",name);
               item.put("quantity",1);
               item.put("price",price);
               cart.add(item);
           }

            String pagename=request.getParameter("pagename");

            if(pagename.equals("list")){

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



            }else if(pagename.equals("detail")){



                Goods goods= null;
                try {
                    goods = goodsDao.findByPK(new Long(goodid));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                request.setAttribute("goods",goods);
                request.getRequestDispatcher("goods_detail.jsp").forward(request,response);


            }


            }else if("cart".equals(action)){

            //查看购物车

            //丛Session中取数据
            List<Map<String,Object>> cart=(List<Map<String,Object>>) request.getSession().getAttribute("cart");
            double total=0.0;
            if(cart!=null){
                for (Map<String,Object> item:cart){
                    String goodsid2=(String)item.get("goodsid");
                    Integer quantity=(Integer)item.get("quantity");
                    Float price=(Float)item.get("price");
                    double subtotal=price*quantity;
                    total+=subtotal;

                }

            }


            request.setAttribute("total",total);
            request.getRequestDispatcher("cart.jsp").forward(request,response);

        }else if("sub_ord".equals(action)){
            List<Map<String,Object>> cart=(List<Map<String,Object>>) request.getSession().getAttribute("cart");
            for (Map<String,Object> item:cart){
                String goodsid=(String)item.get("goodsid");
                String strquantity=request.getParameter("quantity_"+goodsid);

                int quantity=0;
                try{
                    quantity=new  Integer(strquantity);
                }catch(Exception e){

                }

                item.put("quantity",quantity);
            }

        //提交订单
            try {
                String ordersid=orderService.submitOrders(cart);
                request.setAttribute("ordersid",ordersid);
                request.getRequestDispatcher("order_finish.jsp").forward(request,response);

                request.getSession().removeAttribute("cart");



            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }



        }else if ("main".equals(action)){
            request.getRequestDispatcher("main.jsp").forward(request,response);


        }


    }
}

