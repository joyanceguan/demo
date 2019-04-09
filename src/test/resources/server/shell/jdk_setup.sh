#定义基本路径，安装路径及安装包路径
setup_path=/usr/test/setup/  #安装包基本路径
base_path=/usr/test/  #安装基本路径
jdk_name=jdk-8u181-linux-x64.tar.gz  #jdk名（与setup_path拼接）  
jdk_path=java/1.8  #安装路径
profile_source=/usr/test/profile  #profile文件路径
echo 开始安装jdk....

#判断是否有java路径
if [ ! -d $base_path$jdk_path ];
then
   mkdir -m 777 -p $base_path$jdk_path
else
   chmod 777 -R $base_path$jdk_path
fi  

#判断是否有安装包
if [ ! -e $setup_path$jdk_name ];
then
   #需要下载安装包
   wget -P $setup_path --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u181-b13/96a7b8442fe848ef90c96a2fad6ed6d1/jdk-8u181-linux-x64.tar.gz
   echo download finish
else
   echo have download
fi  

tar -zxvf $setup_path$jdk_name -C $base_path$jdk_path
 
path=`ls $base_path$jdk_path`
echo 安装路径为$base_path$jdk_path/$path   
   
#jdk配置,追加输出到profile文件
JAVA_HOME='JAVA_HOME='"$base_path$jdk_path/$path"
EPATH='PATH=$JAVA_HOME/bin:$PATH'
CLASSPATH='CLASSPATH=.:$JAVA_HOME/lib'
EXPORT_JAVA_HOME='export JAVA_HOME'
EXPORT_PATH='export PATH'
EXPORT_CLASSPATH='export CLASSPATH'
profile_append="
$JAVA_HOME
$EPATH
$CLASSPATH
$EXPORT_JAVA_HOME
$EXPORT_PATH
$EXPORT_CLASSPATH
"
echo "$profile_append">>$profile_source 
echo 追加jdk信息到profile成功....
`source $profile_source`
echo 生效profile成功....

#测试是否成功
echo `java -version`


