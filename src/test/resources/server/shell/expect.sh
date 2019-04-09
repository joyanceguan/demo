#定义基本路径，安装路径及安装包路径
setup_path=/usr/test/setup/  #安装包基本路径
base_path=/usr/test/  #安装基本路径
tcl_name=tcl8.4.11-src.tar.gz #tcl名（与setup_path拼接） 
tcl_path=tcl/8.4 #tcl安装路径 
expect_name=download #expect名
expect_path=expect/5.45 #expect安装路径
profile_source=/usr/test/profile  #profile文件路径
echo 开始安装expect....

#判断是否有tcl路径
if [ ! -d $base_path$tcl_path ];
then
   mkdir -m 777 -p $base_path$tcl_path
else
   chmod 777 -R $base_path$tcl_path
fi  

#判断是否有tcl安装包
if [ ! -e $setup_path$tcl_name ];
then
   #需要下载安装包
   wget -P $setup_path http://nchc.dl.sourceforge.net/sourceforge/tcl/tcl8.4.11-src.tar.gz
   echo download finish
else
   echo have download
fi 
tar -xvf $setup_path$tcl_name -C $base_path$tcl_path

#安装tcl
l_path=`ls $base_path$tcl_path`
cd $base_path$tcl_path/$l_path/unix
./configure --prefix=$base_path$tcl_path --enable-shared
make
make install

#判断是否有expect路径
if [ ! -d $base_path$expect_path ];
then
   mkdir -m 777 -p $base_path$expect_path
else
   chmod 777 -R $base_path$expect_path
fi

#判断是否有expect安装包
if [ ! -e $setup_path$expect_name ];
then
   #需要下载安装包
   wget -P $setup_path http://sourceforge.net/projects/expect/files/Expect/5.45/expect5.45.tar.gz/download
   echo download finish
else
   echo have download
fi 
tar -xvf $setup_path$expect_name -C $base_path$expect_path

#安装expect
e_path=`ls $base_path$expect_path`
cd $base_path$expect_path/$e_path
./configure --prefix=$base_path$expect_path --with-tclinclude=$base_path$tcl_path/$l_path/generic --with-tclconfig=$base_path$tcl_path/lib
make && make install

#expect配置，追加输出到profile文件
M_EXPECT_PATH='EXPECT_PATH='"$base_path$tcl_path/bin"
EXPORT_EXPECT_PATH='export EXPECT_PATH'
profile_append="
$M_EXPECT_PATH
$EXPORT_EXPECT_PATH
"
echo "$profile_append">>$profile_source 

#判断环境变量是否有expect
PROFILE_EXPECT_PATH=`grep ':$EXPECT_PATH' $profile_source`
V_EXPECT_PATH=':$EXPECT_PATH'
V_PATH='bin:$PATH'
#查找PATH变量，并在后面追加EXPECT_PATH
if [ -z "$PROFILE_EXPECT_PATH" ]; 
  then 
    sed -i "/$V_PATH/ s/$/$V_EXPECT_PATH/" $profile_source
fi

echo 配置expect信息到profile成功....
`source $profile_source`
echo 生效profile成功....
