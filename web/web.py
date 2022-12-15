from flask import Flask, render_template,request,session,jsonify
from Dbconnection import Db
app = Flask(__name__)


app.secret_key="aaaaaaaa"







@app.route('/')
def login_index():
    return render_template('login_index.html')

@app.route('/adhome')
def login11():
    return render_template('index.html')







@app.route('/adindex')
def login12():
    return render_template('admin/admin_index.html')


@app.route('/login_post',methods=['post'])
def login_post():
    username=request.form['textfield']
    password=request.form['textfield2']
    print(username+password)
    qry="SELECT * FROM login WHERE username ='"+username+"' AND PASSWORD='"+password+"' "
    d=Db()
    res=d.selectOne(qry)
    print(res)
    if res!=None:
        session["lid"]=res["login_id"]
        if res['type']=="admin":
            return '''<script>alert('succesfully login');window.location='/adhome'</script>'''
        elif res['type']=="shop":
            return '''<script>alert('succes');window.location='/shindex'</script>'''
        else:
            return '''<script>alert('Invalid');window.location='/'</script>'''
    else:
        return '''<script>alert('Invalid');window.location='/'</script>'''

@app.route('/new_category')
def new_category():
    return render_template('admin/NEW_CATEGORY.html')


@app.route('/adhome')
def adhome():
    return render_template('index.html')

@app.route('/search_category_post',methods=['post'])
def search_category_post():
    cat=request.form['textfield']
    qry="INSERT INTO `catagory`(`category_name`)VALUES('"+cat+"')"
    d=Db()
    d.insert(qry)
    return '''<script>alert('succes');window.location='/new_category'</script>'''


@app.route ('/category')
def category():
    qry="SELECT* FROM `catagory`"
    d=Db()
    res=d.select(qry)
    return render_template('admin/CATEGORY.html',val=res)


@app.route ('/categorysearchpost',methods=['post'])
def categorysearch():
    name=request.form['tt']
    qry="SELECT* FROM catagory WHERE category_name like '%"+name+"%'"
    d=Db()
    res=d.select(qry)
    return render_template('admin/CATEGORY.html',val=res)



@app.route('/shop_approval')
def shop_approval():
    qry="select shop.* from shop,login where shop.login_id=login.login_id and login.type='pending'"
    d=Db()
    res=d.select(qry)
    return render_template('admin/view_shop_and_approval.html', val=res)
@app.route('/shop_approvel_post', methods=['post'])
def shop_approvel_post():
    search = request.form['textfield']

    qry = "select shop.* from shop,login where shop.login_id=login.login_id and login.type='pending' AND shop_name LIKE '%" + search + "%'"
    d = Db()
    print(qry)
    res = d.select(qry)

    return render_template('admin/view_shop_and_approval.html', val=res)

@app.route('/approve_shops/<pp>')
def approve_shops(pp):
    qry="UPDATE `login` SET `type` = 'shop' WHERE `login_id`='"+pp+"'"
    d = Db()
    res = d.update(qry)
    return shop_approval()




@app.route('/reject_shops/<qq>')
def reject_shops(qq):
    qry="UPDATE `login` SET `type`= 'REJECTED' WHERE `type`='pending' AND login_id='"+qq+"'"
    d = Db()
    res = d.update(qry)
    return render_template('admin/view_shop_and_approval.html', data=res)






@app.route('/approved_shops')
def approved_shops():
    qry = "select shop.* from shop,login where shop.login_id=login.login_id and login.type='shop'"
    d = Db()
    res = d.select(qry)

    return render_template('admin/ADMIN_APPROVED_SHOPS.html',val=res)



@app.route('/approved_shops_post',methods=['post'])
def approved_shops_post():
    search=request.form['textfield']

    qry = "select shop.* from shop,login where shop.login_id=login.login_id and login.type='shop' AND shop_name LIKE '%" + search + "%'"
    d = Db()
    print(qry)
    res = d.select(qry)
    return render_template('admin/ADMIN_APPROVED_SHOPS.html', val=res)





@app.route('/rejected_shops')
def rejected_shops():
    qry = "select shop.* from shop,login where shop.login_id=login.login_id and login.type='rejected'"
    d = Db()
    res = d.select(qry)
    return render_template('admin/REJECTED_SHOPS.html',val=res)

@app.route('/search_shop_post',methods=['post'])
def search_shop_post():
    search2=request.form['tt']

    qry = "select shop.* from shop,login where shop.login_id=login.login_id and login.type='rejected' AND shop_name LIKE '%" + search2 + "%'"
    d = Db()
    print(qry)
    res = d.select(qry)
    return render_template('admin/REJECTED_SHOPS.html',val=res)




@app.route('/registered_users')
def registered_user():
    qry="SELECT * FROM USER "
    d = Db()
    res = d.select(qry)

    return render_template('admin/VIEW_REGISTERED_USERS.html',val=res)

@app.route('/search_user',methods=['post'])
def search_user_post():
    search2=request.form['textfield']

@app.route('/complaints_reply')
def complaints_reply():
    qry = "SELECT complaint.*,user.user_name FROM complaint,USER WHERE complaint.user_id=user.`login_id`"
    d = Db()
    res = d.select(qry)
    print(res)

    return render_template('admin/COMPLAINT_AND_SEND_REPLY.html',val=res)

@app.route('/complaint_post',methods=['post'])
def complaint_post():
    search4=request.form['textfield']

@app.route('/review')
def review():
    qry="SELECT review.*,user.user_name,shop.shop_name FROM review INNER JOIN USER ON `review`.`user_id`=`user`.`login_id` INNER JOIN shop ON `review`.`shop_id`=`shop`.`login_id`"
    d = Db()
    res = d.select(qry)
    return render_template('admin/VIEW_REVIEW.html',val=res)

@app.route ('/reviewsearchpost',methods=['post'])
def reviewsearch():
    name=request.form['textfield']
    qry = "SELECT * FROM USER WHERE user_name LIKE '%" + name + "%'"
    d = Db()
    print(qry)
    res = d.select(qry)
    return render_template('admin/VIEW_REGISTERED_USERS.html',val=res)


@app.route('/send_reply/<cid>')
def send_reply(cid):
    qry="SELECT * FROM `complaint` WHERE complaint_id='"+cid+"' "
    d = Db()
    res = d.selectOne(qry)
    return render_template('admin/sendreply.html',data=res)

@app.route ('/send_reply_post',methods=['post'])
def send_reply_post():
    name=request.form['txt']
    cid=request.form["cid"]
    qry = "UPDATE complaint SET`status`= 'replayed' ,`reply`='"+name+"' WHERE complaint_id='"+cid+"'"
    d = Db()
    print("=======================================",cid)
    res = d.update(qry)
    return complaints_reply()






















#SHOP--------------------------------------"
@app.route('/home2')
def home2():
    return render_template('SHOP/HOME.html')

@app.route('/shindex')
def shindex():
    return render_template('SHOP/shop_index.html')


@app.route('/signup_index')
def signup_index():
    return render_template('sHOP/signup_index.html')




@app.route('/signup')
def signup():
    return render_template('SHOP/shop_signup.html')

@app.route('/signup_post',methods=['post'])
def signup_post():
    shopname=request.form['textfield']
    email=request.form['textfield2']
    phone=request.form['textfield3']
    place = request.form['textfield4']
    post = request.form['textfield5']
    district = request.form['textfield9']
    pin = request.form['textfield6']
    password = request.form['textfield7']
    conformpassword = request.form['textfield8']

    if password==conformpassword:
        d = Db()
        qry1="insert into login(username,password,type)VALUES('"+shopname+"','"+password+"','pending') "
        lid=d.insert(qry1)
        print(lid)
        qry="INSERT INTO shop (shop_name,email,phone,place,post,district,pin,login_id)VALUES('"+shopname+"','"+email+"','"+phone+"','"+place+"','"+post+"','"+district+"','"+pin+"','"+str(lid)+"') "
        led=d.insert(qry)
        print(led)

    return render_template('login_index.html')



@app.route('/change_password')
def change_password():
    return render_template('SHOP/SHOP_CHANGE_PASSWORD.html')

@app.route('/chaing_password_post',methods=['post'])
def chaing_password_post():
    old_password=request.form['textfield2']
    new_password = request.form['textfield3']
    confirm_password = request.form['textfield4']

    if new_password==confirm_password:
        d = Db()
        id=session['lid']
        qry="select * from login where login_id='"+str(id)+"'"
        pas=d.selectOne(qry)
        if pas!=None:
            qry1="update login set password='"+new_password+"'where login_id='"+str(id)+"'"
            pas1=d.update(qry1)
            return render_template('SHOP/SHOP_CHANGE_PASSWORD.html')
        else:
            return 'old password does not match'
    else:
        return 'new password and confirm password does not match'








@app.route('/view_profile')
def view_profie():
    qry="SELECT * FROM shop WHERE login_id='"+str(session['lid'])+"'"
    d = Db()
    res = d.selectOne(qry)
    return render_template('SHOP/SHOP_VIEW_PROFILE.html',val=res)




@app.route ('/product_adding')
def product_adding():
    qry = "SELECT * FROM catagory"
    d = Db()
    print(qry)
    res = d.select(qry)

    return render_template('SHOP/PRODUCT_MNG_ADD.html',val=res)

@app.route('/product_add_post',methods=['post'])
def product_add_post():
    category_name=request.form['select2']
    name=request.form['textfield']
    brand = request.form['textfield2']
    # quantity = request.form['textfield4']
    price=request.form['textfield3']
    img=request.files["file"]
    img.save("C:\\Users\\mi\\OneDrive\\Desktop\\SMART SHOPING\\web\\static\\product\\"+img.filename)
    path="/static/product/"+img.filename
    d = Db()
    qry="INSERT INTO`product`(`product_name`,`brand`,category_id,price,shoplid,image) VALUES ('"+name+"','"+brand+"','"+category_name+"','"+price+"','"+str(session['lid'])+"','"+str(path)+"')"
    res = d.insert(qry)
    q = "INSERT INTO `stock`(`stock_quantity`,`product_id`,`shop_lid`) VALUES('10','"+str(res)+"','"+str(session['lid'])+"')"
    res1 = d.insert(q)

    return '''<script>alert('succes');window.location='/product_adding'</script>'''


@app.route('/product_view')
def product_view():
    qry="SELECT `catagory`.`category_name`,`product`.* FROM `catagory` ,`product` WHERE `catagory`.`category_id`=`product`.`category_id` AND `shoplid`='"+str(session['lid'])+"'"
    d = Db()
    print(qry)
    res = d.select(qry)
    return render_template('SHOP/PRODUCT_MNG_VIEW.html',res=res)



@app.route('/product_deleting/<kk>')
def product_deleting(kk):
    qry="DELETE FROM `product` WHERE `product_id`='"+kk+"'"
    d = Db()
    res = d.delete(qry)
    return product_view()

@app.route('/search_product_post',methods=['post'])
def search_product_post():
    search2=request.form['tt']
    d = Db()
    qry = "SELECT `product`.*,`catagory`.`category_name` FROM `catagory` INNER JOIN `product` ON `product`.`category_id`=`catagory`.`category_id` WHERE`product`.`product_name` LIKE '%"+search2+"%' AND `product`.`shoplid`='"+str(session['lid'])+"'"
    res = d.select(qry)
    return render_template('shop/PRODUCT_MNG_VIEW.html',res=res)

@app.route('/product_editing/<pid>')
def product_editing(pid):
    qry = "SELECT * FROM catagory"
    d = Db()
    print(qry)
    res = d.select(qry)
    q="SELECT `product`.*,`catagory`.* FROM `catagory` INNER JOIN `product` ON `product`.`category_id`=`catagory`.`category_id` WHERE`product`.product_id='"+pid+"' AND `product`.`shoplid`='"+str(session['lid'])+"'"
    re=d.selectOne(q)
    return render_template('SHOP/PRODUCT_MNG_UPDATE.html',val=res,data=re)

@app.route('/product_edit_post',methods=['post'])
def product_edit_post():
    category_name=request.form['select']
    name=request.form['textfield']
    brand = request.form['textfield2']
    quantity = request.form['textfield4']
    price = request.form['textfield3']
    pid=request.form["pid"]
    if 'file' in request.files:

        img = request.files["file"]
        if img.filename !="":
            img.save("C:\\Users\\mi\\OneDrive\\Desktop\\SMART SHOPING\\web\\static\\product\\" + img.filename)
            path = "/static/product/" + img.filename
            pid = request.form['pid']
            qry = "UPDATE product SET  `category_id`='"+category_name+"',`product_name`='"+name+"',`brand`='"+brand+"',`quantity`='"+quantity+"',`price`='"+price+"',image='"+str(path)+"' WHERE `product_id`='"+pid+"'"
            d = Db()
            print(qry)
            d.insert(qry)
        else:
            qry = "UPDATE product SET  `category_id`='" + category_name + "',`product_name`='" + name + "',`brand`='" + brand + "',`quantity`='" + quantity + "',`price`='" + price + "' WHERE `product_id`='" + pid + "'"
            d = Db()
            print(qry)
            d.insert(qry)
    else:

        qry = "UPDATE product SET  `category_id`='" + category_name + "',`product_name`='" + name + "',`brand`='" + brand + "',`quantity`='" + quantity + "',`price`='" + price + "' WHERE `product_id`='" + pid + "'"
        d = Db()
        print(qry)
        d.insert(qry)
    return '''<script>alert('succes');window.location='/product_view'</script>'''





@app.route('/stocks')
def stocks():
    qry="SELECT product.*,stock.* FROM product INNER JOIN stock ON stock.`product_id`=`product`.`product_id`WHERE`product`.`shoplid`='"+str(session['lid'])+"'"
    d = Db()
    res = d.select(qry)
    return render_template('SHOP/STOCK_MANAGMENT.html',val=res)

@app.route('/stock_mng_post',methods=['post'])
def stock_mng_post():
    search5=request.form['textfield']
    qry = "SELECT product.*,stock.stock_quantity FROM product INNER JOIN stock ON stock.`product_id`=`product`.`product_id` where `product`.`product_name` LIKE '%"+search5+"%' "
    d = Db()
    res = d.select(qry)
    return render_template('SHOP/STOCK_MANAGMENT.html', val=res)







@app.route('/purchase_history')
def purchase_history():
    # qry="SELECT `parchase_main`.*,`purchase_sub`.*,user.user_name,user.phone,product.price,payment.payment_type FROM `parchase_main` INNER JOIN purchase_sub ON `parchase_main`.`purchase_id`=`purchase_sub`.`purchase_id`INNER JOIN USER ON `user`.`login_id`=`parchase_main`.`user_id` INNER JOIN product ON `product`.`product_id`=`purchase_sub`.`product_id` INNER JOIN `payment` ON payment.`purchase_id`=`parchase_main`.`purchase_id` where "
    qry="SELECT DISTINCT purchase_sub.`purchase_id`,`parchase_main`.*,`purchase_sub`.*,`product`.*,`user`.*,`payment`.*FROM `parchase_main`INNER JOIN `purchase_sub`ON `parchase_main`.`purchase_id`=`purchase_sub`.`purchase_id` INNER JOIN `product`ON `product`.`product_id`=`purchase_sub`.`product_id` INNER JOIN `user`ON`user`.`login_id`=`parchase_main`.`user_id` INNER JOIN`payment`ON `payment`.`purchase_id`=`parchase_main`.`purchase_id` WHERE `product`.`shoplid`='"+str(session['lid'])+"'"
    d = Db()
    print(qry)
    res = d.select(qry)
    ls=[]
    sum=0
    for i in res:
        print("-----", i)
        price = int(i["quantity"])
        pqty = int(i["price"])
        total = price * pqty
        ptot = str(total)
        sum = sum + total
        print("====================tot======================", sum)
        ls.append({"tota": ptot,"user_name":i['user_name'],"phone":i['phone'],"date":i['date'],"payment_type":i['payment_type'],"purchase_id":i['purchase_id']})
    return render_template('SHOP/PURCHASE_HISTORY.html',val=ls)

@app.route('/purchase_history_post',methods=['post'])
def purchase_history_post():
    name=request.form['textfield']
    qry = "SELECT DISTINCT purchase_sub.`purchase_id`,`parchase_main`.*,`purchase_sub`.*,`product`.*,`user`.*,`payment`.*FROM `parchase_main`INNER JOIN `purchase_sub`ON `parchase_main`.`purchase_id`=`purchase_sub`.`purchase_id` INNER JOIN `product`ON `product`.`product_id`=`purchase_sub`.`product_id` INNER JOIN `user`ON`user`.`login_id`=`parchase_main`.`user_id` INNER JOIN`payment`ON `payment`.`purchase_id`=`parchase_main`.`purchase_id` WHERE `product`.`shoplid`='" + str(
        session['lid']) + "'and user_name LIKE '%" + name + "%'"

    d = Db()
    print(qry)
    res = d.select(qry)
    ls = []
    sum = 0
    for i in res:
        print("-----", i)
        price = int(i["quantity"])
        pqty = int(i["price"])
        total = price * pqty
        ptot = str(total)
        sum = sum + total
        print("====================tot======================", sum)
        ls.append({"tota": ptot, "user_name": i['user_name'], "phone": i['phone'], "date": i['date'],
                   "payment_type": i['payment_type'], "purchase_id": i['purchase_id']})
    return render_template('SHOP/PURCHASE_HISTORY.html', val=ls)


@app.route('/user_review')
def user_review():
    qry = "SELECT review.*,user.user_name FROM review INNER JOIN USER ON `review`.`user_id`=`user`.`login_id` INNER JOIN shop ON `review`.`shop_id`=`shop`.`login_id` where shop.login_id='"+str(session["lid"])+"'"
    d = Db()
    res = d.select(qry)
    return render_template('SHOP/VIEW_USER_REVIEWS.html',val=res)
@app.route('/stock_update')
def stock_update():
    qry="select * from product where shoplid='"+str(session['lid'])+"'"
    d = Db()

    res = d.select(qry)
    return render_template('SHOP/add_stock.html',val=res)

@app.route('/stock_updating/<id>')
def stock_updating(id):
    d = Db()
    qry = "SELECT stock.*, product.`product_name` FROM `product` INNER JOIN `stock` ON `stock`.`product_id`=`product`.`product_id` WHERE `stock`.`stock_id`='"+str(id)+"'"
    res = d.selectOne(qry)
    return render_template('SHOP/add_stock.html', val=res)

@app.route('/add_stock_post', methods=['post'])
def add_Stock_post():
    q = request.form['textfield']
    p = request.form['h1']
    d = Db()
    qry = "update stock set stock_quantity='"+q+"' where stock_id='"+str(p)+"'"
    print(qry)
    res = d.update(qry)
    print(res)

    return stocks()





# @app.route('/stock_updating/<v>')
# def stock_updating(v):
#     qry="SELECT `stock`.*,`product`.* FROM `product`,`stock` WHERE `stock`.`product_id`=`product`.`product_id` AND `stock`.`stock_id`='"+v+"'"
#     d = Db()
#     res = d.select(qry)
#     session['tt']=v
#     return render_template('SHOP/add_stock.html',val=res)
#
# @app.route('/stock_editing_postt',methods=['post'])
# def stock_editing_postt():
#     stock_quantity=request.form['textfield']
#     qry="UPDATE `stock` SET `stock_id`="'+ stock_quantity +'" WHERE `stock_id`=<v>`"
#     d = Db()
#     print(qry)
#     d.update(qry)
#     return '''<script>alert('sucess');window.location='/stocks'</script>'''


@app.route('/product_total/<pid>')
def product_total(pid):
    c=Db()
    qry2="SELECT DISTINCT `shop`.* FROM `shop`,`product`,`parchase_main`,`purchase_sub` WHERE `product`.`shoplid`=`shop`.`login_id` AND `purchase_sub`.`product_id`=`product`.`product_id` AND `purchase_sub`.`purchase_id`=`parchase_main`.`purchase_id` AND `parchase_main`.`purchase_id`='"+pid+"'"
    res2=c.selectOne(qry2)
    qry3="SELECT DISTINCT `user`.`user_name` FROM `user`,`product`,`parchase_main`,`purchase_sub` WHERE `parchase_main`.`user_id`=`user`.`login_id` AND `purchase_sub`.`product_id`=`product`.`product_id` AND `purchase_sub`.`purchase_id`=`parchase_main`.`purchase_id` AND `parchase_main`.`purchase_id`='"+pid+"'"
    res3=c.selectOne(qry3)
    print(qry3)

    qry4="SELECT * FROM `parchase_main` WHERE `purchase_id`='"+pid+"'"
    res4=c.selectOne(qry4)
    qry="SELECT `product`.*,`purchase_sub`.`quantity`,`purchase_sub`.`quantity`*`product`.`price` FROM `product` INNER JOIN `purchase_sub` ON `product`.`product_id`=`purchase_sub`.`product_id` WHERE `purchase_id`='"+pid+"'"
    d = Db()
    res = d.select(qry)
    print(res)
    ls=[]
    total=1
    sum=0
    for i in res:
        print("-----",i)
        price=int(i["quantity"])
        pqty=int(i["price"])
        total=price*pqty
        ptot=str(total)
        sum=sum+total
        print("-----------------------------------",sum)
        print("====================tot======================",total)
        ls.append({"tota":ptot,"product_name":i['product_name'],"price":i['price'],"quantity":i['quantity']})
    return render_template('SHOP/produt_total.html',val=ls,data=sum,ss=res2,sv=res3,sc=res4)


# ------------------------android methods------------

@app.route('/amd_login',methods=['post'])
def and_login_post():
    username=request.form['username']
    password=request.form['pass']
    print(username+password)
    qry="SELECT * FROM login WHERE username ='"+username+"' AND PASSWORD='"+password+"' "
    d=Db()
    res=d.selectOne(qry)
    print(res)
    if res is not None:
        if res['type']=="user":
            return jsonify(status="ok",id=res['login_id'])
        else:
            return jsonify(status="no")
    else:
        return jsonify(status="no")

@app.route('/and_registration', methods=['post'])
def and_signup_post():
    username = request.form['username']
    email = request.form['email']
    phone = request.form['phone']

    place = request.form['place']
    post = request.form['post']
    district = request.form['district']
    pin = request.form['pin']
    password = request.form['password']
    image = request.files['pic']
    image.save("C:\\Users\\mi\\OneDrive\\Desktop\\SMART SHOPING\\web\\static\\customer\\" + image.filename)
    path = "/static/customer/" + image.filename
    d = Db()
    qry1 = "insert into login(username,password,type)VALUES('" + email + "','" + password + "','user') "
    lid = d.insert(qry1)
    print(lid)
    qry = "INSERT INTO user (user_name,email,phone,photo,place,post,district,pin,login_id)VALUES('" + username + "','" + email + "','" + phone + "','"+path+"','" + place + "','" + post + "','" + district + "','" + pin + "','" + str(
        lid) + "') "
    led = d.insert(qry)
    print(led)

    return jsonify(status="ok")


@app.route('/and_chaing_password_post',methods=['post'])
def and_chaing_password_post():
    old_password=request.form['old_password']
    new_password = request.form['new_password']
    confirm_password = request.form['confirm_password']

    if new_password==confirm_password:
        d = Db()
        id=session['lid']
        qry="select * from login where login_id='"+str(id)+"'"
        pas=d.selectOne(qry)
        if pas!=None:
            qry1="update login set password='"+new_password+"'where login_id='"+str(id)+"'"
            pas1=d.update(qry1)
            return jsonify(status="ok")
        else:
            return jsonify(status="no")
    else:
        return jsonify(status="no")

@app.route("/customer_viewprofile", methods=['post'])
def customer_viewprofile():
    lid=request.form['lid']
    qry="SELECT * FROM USER where login_id='"+lid+"'"
    db=Db()
    res = db.selectOne(qry)
    print(res)
    return jsonify(status="ok",name=res["user_name"],email_id=res["email"],ph_no=res["phone"],district=res["district"],state=res["place"],post=res["post"],pincode=res["pin"],image=res["photo"])


@app.route("/viewproductcust",methods=['POST'])
def viewproductcust():
    db=Db()
    res = db.select("SELECT `product`.*,`catagory`.`category_id`,`catagory`.`category_name` FROM `catagory`,`product` WHERE `catagory`.`category_id`=`product`.`category_id`")
    print(res)
    return jsonify(status="ok", data=res)

@app.route("/send_complaint", methods=['post'])
def send_complaint():
    print("hhh")
    complaint = request.form['complaint']
    lid= request.form['lid']
    qry="INSERT INTO `complaint`(`complaint`,`user_id`,`date`,`reply`,`status`) VALUES('"+complaint+"','"+lid+"',CURDATE(),'pending','pending')"
    print(qry)
    db = Db()
    res = db.insert(qry)
    return jsonify(status="ok", data=res)

@app.route("/viewreply", methods=['post'])
def viewreply():
    lid=request.form['lid']
    print(lid)
    qry="SELECT `complaint`,`reply`,`date`FROM `complaint` WHERE `user_id`='"+lid+"'"
    db=Db()
    res = db.select(qry)
    print(res)
    return jsonify(status="ok",data=res)
@app.route("/bysearch",methods=['POST'])
def bysearch():
    db=Db()
    name=request.form['name']
    res = db.select("SELECT `product`.*,`catagory`.`category_id`,`catagory`.`category_name` FROM `catagory`,`product` WHERE `catagory`.`category_id`=`product`.`category_id` and `product_name`  LIKE '%"+name+"%'")
    print(res)
    return jsonify(status="ok", data=res)



@app.route("/viewnearbyseller", methods=['post'])
def viewnearbyseller():
    # lati=request.form['lati']
    # logi = request.form['logi']
    qry="SELECT * from shop"
    db=Db()
    res = db.select(qry)
    print(res)
    return jsonify(status="ok",data=res)

@app.route("/vproductsofeachseller", methods=['post'])
def vproductsofeachseller():
    lid=request.form['lid']
    print(lid)
    qry="SELECT * FROM `product` INNER JOIN `catagory` ON `catagory`.`category_id`=`product`.`category_id` WHERE `shoplid`='"+lid+"'"
    db=Db()
    res = db.select(qry)
    print(res)
    return jsonify(status="ok",data=res)

@app.route("/viewcart", methods=['post'])
def viewcart():
    lid=request.form['lid']
    sellerid=request.form["sellerid"]
    qry = "SELECT `cart`.*,`product`.* ,cart.`quantity` AS qqty FROM `cart` INNER JOIN `product` on product.product_id=cart.product_id inner join catagory ON `catagory`.`category_id`=`product`.`category_id`  WHERE `cart`.`customer_l_id` = '"+lid+"' AND  `cart`.`seller_id`='"+sellerid+"'"
    db=Db()
    res=db.select(qry)
    print(res)
    ls = []
    total = 1
    sum = 0
    for i in res:
        print("-----", i)
        price = int(i["qqty"])
        pqty = int(i["price"])
        total = price * pqty
        ptot = str(total)
        sum = sum + total
        print("---------------SUMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM--------------------", sum)
        ls.append({"tota": ptot, "product_name": i['product_name'], "price": i['price'], "quantity": i['quantity']})
    return jsonify(status="ok", data=res,tot=sum)



@app.route("/addtocart", methods=['post'])
def addtocart():
    customer_l_id = request.form['customer_l_id']
    product_id = request.form['product_id']
    quantity = request.form['quantity']
    seller_id=request.form["sellerlid"]
    qry="INSERT INTO cart(customer_l_id,product_id,quantity,seller_id)VALUES('"+customer_l_id+"','"+product_id+"','"+quantity+"','"+seller_id+"')"
    db=Db()
    res = db.insert(qry)
    return jsonify(status="ok")

@app.route("/delete_cart", methods=['post'])
def deletefromcart():
    cart_id = request.form['cart_id']
    qry="delete from cart where cart_id='"+cart_id+"'"
    db=Db()
    res=db.delete(qry)
    return jsonify(status="ok")

@app.route("/and_changepassword", methods=['post'])
def and_changepassword():
    print("hlwwww")
    lid=request.form['lid']
    print("qqq")
    current_password = request.form['currentpassword']
    new_password = request.form['newpassword']
    qry = "SELECT * FROM login WHERE login_id='"+lid+"'and password='" + current_password + "'"
    db = Db()
    res = db.selectOne(qry)
    if res is not None:
        qry="UPDATE login SET password='"+new_password+"' WHERE login_id='"+lid+"'"
        db = Db()
        res = db.update(qry)
        return jsonify(status="ok")
    else:
        return jsonify(status="no")


@app.route("/and_product_details", methods=['post'])
def and_product_details():
    db = Db()
    pid = request.form['product_id']
    qry="select * from product where product_id='"+pid+"'"
    res = db.selectOne(qry)
    return jsonify(status="ok")


@app.route("/and_product_details_f", methods=['post'])
def and_product_details_f():
    db = Db()
    pid = request.form['product_id']
    qry="SELECT product.*,stock.`stock_quantity` AS quantity FROM stock INNER JOIN `product` ON `product`.`product_id`=`stock`.`product_id` where product.product_id='"+pid+"'"
    res = db.selectOne(qry)
    return jsonify(status="ok",name=res["product_name"],price=res["price"],quantity=res["quantity"],productid=["product_id"],image=res["image"])

@app.route("/buy_products", methods=['post'])
def buy_products():
    # print("hello")
    # db=Db()
    # print("hi")
    # bank_name = request.form['bank_name']
    # print("hoi")
    # accno = request.form['account_no']
    # password = request.form['pass']
    # selerid = request.form['selerid']
    # lid=request.form['lid']
    # pid=request.form["pid"]
    # price=request.form["price"]
    # qty=request.form["qty"]
    # qry="SELECT * FROM bank where acc_no= '"+accno+"'and pin='"+password+"'"
    # ress=db.selectOne(qry)
    # if ress is None:
    #     return jsonify(status="no")
    # else:
    #     qry=" SELECT * FROM cart WHERE `customer_l_id`='"+lid+"' and seller_id='"+selerid+"'"
    #     res=db.select(qry)
    #     tot=0
    #     tot=float(price)*float(qty)
    #     print(tot)
    #     if  float(ress['balance']) > tot:
    #         qry="INSERT INTO `orders` (`customer_l_id`,`status`,`date`,`total`,seller_id) VALUES('"+str(lid)+"','pending',CURDATE(),'"+str(tot)+"','"+str(selerid)+"')"
    #         orderid=db.insert(qry)
    #         qry="INSERT INTO `order_sub` (`order_id`,`product_id`,`quantity`,seller_id) VALUES ('"+str(orderid)+"','"+str(pid)+"','"+str(qty)+"','"+str(selerid)+"')"
    #         db.insert(qry)
    #         qry="INSERT INTO `payment` (`order_id`,`date`,`amount`) VALUES ('"+str(orderid)+"',CURDATE(),'"+str(tot)+"')"
    #         db.insert(qry)
    #     return jsonify(status="ok"
    c = Db()
    uid = request.form['lid']
    total = request.form['price']
    bank = request.form['bank_name']
    account = request.form['account_no']
    pin = request.form['pass']

    qry = "SELECT * FROM `cart` WHERE `customer_l_id`='" + uid + "'"
    res = c.select(qry)

    qry1 = "SELECT * FROM bank where acc_no='" + account + "' and pin='" + pin + "'"
    res1 = c.selectOne(qry1)
    print(res1)

    qry3 = "insert into parchase_main(date,status,total,user_id)values(curdate(),'pending','" + str(
        total) + "','" + uid + "')"
    res3 = c.insert(qry3)

    if res1 is not None:
        if int(res1['balance']) > int(total):
            for i in res:
                pdtid = i['product_id']
                qty = i['quantity']
                qry2 = "SELECT * FROM `product` WHERE `product_id`='" + str(pdtid) + "'"
                res2 = c.selectOne(qry2)
                qty2 = res2['price']
                # total = int(qty) * int(qty2)
                # print(total)





                qry4 = "insert into purchase_sub(purchase_id,product_id,quantity)values('" + str(res3) + "','" + str(pdtid) + "','" + str(qty) + "')"
                res4 = c.insert(qry4)
                print("------------")
            qry2 = "update bank set balance=balance-'" + str(total) + "'where acc_no='" + account + "'"
            res2 = c.update(qry2)
            print(qry2)
            print(res2)

            qrydel = "delete FROM `cart` WHERE `customer_l_id`='" + uid + "'"
            resdel = c.delete(qrydel)

            pay="INSERT INTO `payment`(`purchase_id`,`payment_status`,`date`,`payment_type`)VALUES('" + str(res3) + "','completed',curdate(),'online')"
            c.insert(pay)

            return jsonify(status='ok')
        else:
            return jsonify(status='no')


@app.route("/fs", methods=['post'])
def b():
    c=Db()
    uid=request.form['lid']
    total=request.form['total']
    bank=request.form['bank_name']
    account=request.form['account_no']
    pin=request.form['pass']

    qry="SELECT * FROM `cart` WHERE `customer_l_id`='"+uid+"'"
    res=c.select(qry)
    for i in res:
        pdtid=i['product_id']
        qty=i['quantity']
        qry2="SELECT * FROM `product` WHERE `product_id`='"+str(pdtid)+"'"
        res2=c.selectOne(qry2)
        qty2=res2['price']
        total = int(qty) * int(qty2)
        print(total)
    qry1="SELECT * FROM bank where acc_no='"+account+"' and pin='"+pin+"'"
    res1=c.selectOne(qry1)
    print(res1)
    if res1 is not None:
            if int(res1['balance'])>int(total):
                print("------------")
                qry2="update bank set balance=balance-'"+str(total)+"'where acc_no='"+account+"'"
                res2=c.update(qry2)
                print(qry2)
                print(res2)
                qry3="insert into parchase_main(date,status,total,user_id)values(curdate(),'pending','"+str(total)+"','"+uid+"')"
                res3=c.insert(qry3)
                qry4="insert into purchase_sub(purchase_id,product_id,quantity)values('"+str(res3)+"','"+str(pdtid)+"','"+str(qty)+"')"
                res4=c.insert(qry4)

                qry = "delete FROM `cart` WHERE `customer_l_id`='" + uid + "'"
                res = c.delete(qry)

                pay = "INSERT INTO `payment`(`purchase_id`,`payment_status`,`date`,`payment_type`)VALUES('" + str(
                    res3) + "','completed',curdate(),'online')"
                c.insert(pay)

                return jsonify(status='ok')
            else:
                return jsonify(status='no')



@app.route("/and_payment_post", methods=['post'])
def and_payment_postt():
    c=Db()
    uid=request.form['u_id']
    total=request.form['price']
    bank=request.form['bank_name']
    account=request.form['account_no']
    pin=request.form['pass']
    pid=request.form["pid"]
    # ptype=request.form['pay_type']
    qunt=request.form['qty']
    qry1="SELECT * FROM bank where acc_no='"+account+"' and pin='"+pin+"'"
    res1=c.selectOne(qry1)
    if res1 is not None:
        if str(res1['balance'])>total:
            qry2="update bank set balance=balance-'"+total+"' where acc_no='"+account+"'"
            res2=c.update(qry2)
            print(qry2)
            print(res2)
            qry3="insert into parchase_main(date,status,total,user_id)values(curdate(),'pending','"+total+"','"+uid+"')"
            res3=c.insert(qry3)
            qry4="insert into purchase_sub(purchase_id,product_id,quantity)values('"+str(res3)+"','"+pid+"','"+qunt+"')"
            res4=c.insert(qry4)

            pay = "INSERT INTO `payment`(`purchase_id`,`payment_status`,`date`,`payment_type`)VALUES('" + str(
                res3) + "','completed',curdate(),'online')"
            c.insert(pay)
            return jsonify(status='ok')
        else:
            return jsonify(status='insufficient')
    else:
        return jsonify(status='no')

@app.route("/viewpreviousorder", methods=['post'])
def and_viewpreviousorder():
    c=Db()
    uid = request.form['lid']
    qry="SELECT `shop`.*,`product`.*,`parchase_main`.* FROM `product`,`shop`,`parchase_main`,`purchase_sub` WHERE `shop`.`login_id`=`product`.`shoplid` AND `product`.`product_id`=`purchase_sub`.`product_id` AND `purchase_sub`.`purchase_id`=`parchase_main`.`purchase_id` AND `parchase_main`.`user_id`='"+uid+"'"
    res = c.select(qry)
    return jsonify(status='ok',data=res)

#----------------------------------------stock
#
# @app.route('/add_stock')
# def add_stock():
#     d = Db()
#     qry = "select * from product where shoplid='"+str(session['lid'])+"'"
#     res = d.select(qry)
#     return render_template('SHOP/add_stock.html')
# #
# @app.route('/add_stock_post', methods=['post'])
# def add_stock_post():
#     d = Db()
#     p = request.form['select']
#     s = request.form['textfield']
#     qry = "insert into product()"
#     return add_stock()


@app.route("/and_reviewsadd", methods=['post'])
def and_reviewsadd():
    print("hhh")
    review = request.form['review']
    lid= request.form['uid']
    shid=request.form["shid"]
    rating=request.form["rating"]
    qry="insert into `review`(`shop_id`,`user_id`,`review`,`date`,rating) values('"+shid+"','"+str(lid)+"','"+review+"',curdate(),'"+rating+"')"
    print(qry)
    db = Db()
    res = db.insert(qry)
    return jsonify(status="ok", data=res)





if __name__ == '__main__':
    app.run(debug=True,port=5005,host='0.0.0.0')
