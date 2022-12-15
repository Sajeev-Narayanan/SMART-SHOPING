import mysql.connector

class Db:
    def __init__(self):
        self.connection=mysql.connector.connect(host="localhost",user="root",password="",db="smart_shopping",port=3306)
        self.cur=self.connection.cursor(buffered=True,dictionary=True)

    def insert(self,qry):
        self.cur.execute(qry)
        return self.cur.lastrowid

    def update(self,qry):
        self.cur.execute(qry)
        return self.cur.lastrowid

    def delete(self,qry):
        self.cur.execute(qry)
        return self.cur.lastrowid

    def select(self,qry):
        self.cur.execute(qry)
        return self.cur.fetchall()

    def selectOne(self,qry):
        self.cur.execute(qry)
        return self.cur.fetchone()