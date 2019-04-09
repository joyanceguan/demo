#定义基本路径，安装路径及安装包路径
setup_path=/usr/setup/  #安装包基本路径
base_path=/usr/test/  #安装基本路径
tomcat_name=apache-tomcat-8.5.33.tar.gz  #tomcat名（与setup_path拼接） 
tomcat_path=tomcat/8.5  #安装路径 
echo 开始安装tomcat

#判断是否有tomcat路径
if [ ! -d $base_path$tomcat_path ];
then
   mkdir -m 777 -p $base_path$tomcat_path
else
   chmod 777 -R $base_path$tomcat_path
fi  

#判断是否有安装包
if [ ! -e $setup_path$tomcat_name ];
then
   #需要下载安装包
   wget -P $setup_path  https://mirrors.tuna.tsinghua.edu.cn/apache/tomcat/tomcat-8/v8.5.33/bin/apache-tomcat-8.5.33.tar.gz
   echo download finish
else
   echo have download
fi

tar -xvf $setup_path$tomcat_name -C $base_path$tomcat_path
echo tomcat安装成功