#!/bin/sh

git_path="/home/work/git/spring-shiro-training"
war_path=$git_path"/target/shiro.war"

pro_path="/home/work/shiro.dreamlu.net"
tom_bin="/home/work/tomcat/tomcat-shiro/bin"

echo "goto..."$git_path
cd $git_path
echo "git...pull...begin..."
git pull
echo "git...pull...end..."

echo "build...war...begin..."
mvn clean -U package -DskipTests -Pproduction > /dev/null
echo "build...war...end..."

unzip -o $war_path -d $pro_path > /dev/null
echo "unzip...war...end..."

echo "tomcat...reload...begin..."
sh $tom_bin"/shutdown.sh"
# kill -9 `ps aux|grep tomcat-shiro |grep -v grep |grep java |awk '{print $2}'`
# sleep 1
sh $tom_bin"/startup.sh"
echo "tomcat...reload...end....."