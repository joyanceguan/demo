#定义基本路径，安装路径及安装包路径（rpm包安装，没设置安装路径，默认安装到/usr/share/mysql）
setup_path=/usr/test/setup/  #安装包基本路径
base_path=/usr/test/  #安装基本路径
mysql_devel_name=MySQL-devel-5.6.32-1.el6.x86_64.rpm  #mysql名（与setup_path拼接）  
mysql_server_name=MySQL-server-5.6.32-1.el6.x86_64.rpm  #mysql名（与setup_path拼接） 
mysql_client_name=MySQL-client-5.6.32-1.el6.x86_64.rpm  #mysql名（与setup_path拼接） 
mysql_passwd=dgjs123456
mysql_default_config=/usr/share/mysql/my-default.cnf
mysql_move_config=/etc/my.cnf
mysql_init=/usr/bin/mysql_install_db
mysql_data=/usr/local/mysql/data
echo 开始安装mysql....

#判断是否有安装包
if [ ! -e $setup_path$mysql_devel_name ];
then
   #需要下载安装包
   #=================expect begin=================
   #定义临时文件
   cat > tmp_expect.sh << EOF
#!/usr/tcl/bin/expect
spawn scp root@47.93.30.182:/usr/setup/MySQL-*.rpm $setup_path
expect {
   "*password" { send "622826Dgjs\r" }
}
interact
EOF
   chmod 755 ./tmp_expect.sh
   #执行并删除临时文件
   expect ./tmp_expect.sh && rm -rf ./tmp_expect.sh
   #=================expect end=================
   echo download finish
else
   echo have download
fi

#安装
rpm -ivh $setup_path$mysql_server_name
rpm -ivh $setup_path$mysql_devel_name
rpm -ivh $setup_path$mysql_client_name

cp /usr/share/mysql/my-default.cnf /etc/my.cnf
/usr/bin/mysql_install_db
service mysql start

random_passwd=`cat /root/.mysql_secret`
## 返回结果如上一行，需要截取随机密码
temp_passwd=`echo $random_passwd | awk -F '): ' '{print $NF}' `
echo ========$temp_passwd

#重置密码
#=================expect begin=================
   cat > init_mysql_passwd.sh << EOF
#!/usr/tcl/bin/expect
set timeout -1
spawn mysql -uroot -p$temp_passwd
expect {
   "*mysql>" { send "SET PASSWORD = PASSWORD('$mysql_passwd');\r" }
   "*mysql>" { send "exit;\r" }
}
EOF
   chmod 755 ./init_mysql_passwd.sh
   #执行并删除临时文件
#   ./init_mysql_passwd.sh && rm -rf ./init_mysql_passwd.sh
#=================expect end=================
