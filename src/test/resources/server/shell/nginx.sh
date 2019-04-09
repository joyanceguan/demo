#环境准备
yum -y install gcc gcc-c++ autoconf automake make
yum -y install zlib zlib-devel openssl openssl--devel pcre pcre-devel 
yum -y install make zlib zlib-devel gcc-c++ libtool  openssl openssl-devel

#定义路径
setup_path=/usr/test/setup/  #安装包基本路径
base_path=/usr/test/  #安装基本路径
nginx_name=nginx-1.14.0.tar.gz
nginx_path=nginx/1.14

#判断是否有nginx路径
if [ ! -d $base_path$nginx_path ];
then
   mkdir -m 777 -p $base_path$nginx_path
else
   chmod 777 -R $base_path$nginx_path
fi 

#判断是否有安装包
if [ ! -e $setup_path$nginx_name ];
then
   #需要下载安装包
   wget -P $setup_path http://nginx.org/download/nginx-1.14.0.tar.gz
   echo download finish
else
   echo have download
fi 

#安装
tar -zxvf $setup_path$nginx_name -C $base_path$nginx_path
path=`ls $base_path$nginx_path`
cd $base_path$nginx_path/$path
./configure --prefix=/usr/local/nginx/ --with-http_ssl_module
make && make install