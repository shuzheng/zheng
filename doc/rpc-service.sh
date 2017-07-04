#!/bin/sh
#
#�ýű�ΪLinux������java�����ͨ�ýű�����������Ϊ����������service�ű������ã�
#Ҳ������Ϊ����java����Ķ����ű���ʹ�á�
#
#Author: tudaxia.com, Date: 2011/6/7
#
#����!!!���ýű�stop����ʹ��ϵͳkill������ǿ����ָֹ����java������̡�
#��ɱ������ǰ��δ���κ�������顣��ĳЩ����£���������ڽ����ļ������ݿ�д������
#���ܻ�������ݶ�ʧ�����ݲ��������������Ҫ���ǵ��������������Ҫ��д�˽ű���
#������ִ��kill����ǰ��һϵ�м�顣
# 
###################################
# ������Щע�����ÿ��Ա�chkconfig�����ȡ 
# chkconfig: - 99 50 
# description: Java���������ű� 
# processname: test 
# config: �����Ҫ�Ļ����������� 
################################### 
#
###################################
#��������������ִ�в���
#��Ҫ����ʵ�ʻ����Լ�Java�����������޸���Щ����
###################################
#JDK����·��
JAVA_HOME="/usr/java/jdk"
 
#ִ�г���������ʹ�õ�ϵͳ�û������ǵ���ȫ���Ƽ���ʹ��root�ʺ�
RUNNING_USER=root
 
#Java�������ڵ�Ŀ¼��classes����һ��Ŀ¼��
APP_HOME=/opt/tudaxia/test/WEB-INF
 
#��Ҫ������Java������main�����ࣩ
APP_MAINCLASS=com.tudaxia.test.TestMain
 
#ƴ��������classpath����������ָ��libĿ¼�����е�jar
CLASSPATH=$APP_HOME/classes
for i in "$APP_HOME"/lib/*.jar; do
   CLASSPATH="$CLASSPATH":"$i"
done
 
#java�������������
JAVA_OPTS="-ms512m -mx512m -Xmn256m -Djava.awt.headless=true -XX:MaxPermSize=128m"
 
###################################
#(����)�жϳ����Ƿ�������
#
#˵����
#ʹ��JDK�Դ���JPS���grep������ϣ�׼ȷ����pid
#jps �� l ��������ʾ��ʾjava��������·��
#ʹ��awk���ָ��pid ($1����)����Java��������($2����)
###################################
#��ʼ��psid������ȫ�֣�
psid=0
 
checkpid() {
   javaps=`$JAVA_HOME/bin/jps -l | grep $APP_MAINCLASS`
 
   if [ -n "$javaps" ]; then
      psid=`echo $javaps | awk '{print $1}'`
   else
      psid=0
   fi
}
 
###################################
#(����)��������
#
#˵����
#1. ���ȵ���checkpid������ˢ��$psidȫ�ֱ���
#2. ��������Ѿ�������$psid������0��������ʾ����������
#3. �������û�б���������ִ������������
#4. ��������ִ�к��ٴε���checkpid����
#5. �������4�Ľ���ܹ�ȷ�ϳ����pid,���ӡ[OK]�������ӡ[Failed]
#ע�⣺echo -n ��ʾ��ӡ�ַ��󣬲�����
#ע��: "nohup ĳ���� >/dev/null 2>&1 &" ���÷�
###################################
start() {
   checkpid
 
   if [ $psid -ne 0 ]; then
      echo "================================"
      echo "warn: $APP_MAINCLASS already started! (pid=$psid)"
      echo "================================"
   else
      echo -n "Starting $APP_MAINCLASS ..."
      JAVA_CMD="nohup $JAVA_HOME/bin/java $JAVA_OPTS -classpath $CLASSPATH $APP_MAINCLASS >/dev/null 2>&1 &"
      su - $RUNNING_USER -c "$JAVA_CMD"
      checkpid
      if [ $psid -ne 0 ]; then
         echo "(pid=$psid) [OK]"
      else
         echo "[Failed]"
      fi
   fi
}
 
###################################
#(����)ֹͣ����
#
#˵����
#1. ���ȵ���checkpid������ˢ��$psidȫ�ֱ���
#2. ��������Ѿ�������$psid������0������ʼִ��ֹͣ��������ʾ����δ����
#3. ʹ��kill -9 pid�������ǿ��ɱ������
#4. ִ��kill�����н���������ϲ鿴��һ������ķ���ֵ: $?
#5. �������4�Ľ��$?����0,���ӡ[OK]�������ӡ[Failed]
#6. Ϊ�˷�ֹjava����������Σ��������ӷ��������̣�����ɱ���Ĵ����ݹ����stop����
#ע�⣺echo -n ��ʾ��ӡ�ַ��󣬲�����
#ע��: ��shell����У�"$?" ��ʾ��һ���������һ�������ķ���ֵ
###################################
stop() {
   checkpid
 
   if [ $psid -ne 0 ]; then
      echo -n "Stopping $APP_MAINCLASS ...(pid=$psid) "
      su - $RUNNING_USER -c "kill -9 $psid"
      if [ $? -eq 0 ]; then
         echo "[OK]"
      else
         echo "[Failed]"
      fi
 
      checkpid
      if [ $psid -ne 0 ]; then
         stop
      fi
   else
      echo "================================"
      echo "warn: $APP_MAINCLASS is not running"
      echo "================================"
   fi
}
 
###################################
#(����)����������״̬
#
#˵����
#1. ���ȵ���checkpid������ˢ��$psidȫ�ֱ���
#2. ��������Ѿ�������$psid������0��������ʾ�������в���ʾ��pid
#3. ������ʾ����δ����
###################################
status() {
   checkpid
 
   if [ $psid -ne 0 ];  then
      echo "$APP_MAINCLASS is running! (pid=$psid)"
   else
      echo "$APP_MAINCLASS is not running"
   fi
}
 
###################################
#(����)��ӡϵͳ��������
###################################
info() {
   echo "System Information:"
   echo "****************************"
   echo `head -n 1 /etc/issue`
   echo `uname -a`
   echo
   echo "JAVA_HOME=$JAVA_HOME"
   echo `$JAVA_HOME/bin/java -version`
   echo
   echo "APP_HOME=$APP_HOME"
   echo "APP_MAINCLASS=$APP_MAINCLASS"
   echo "****************************"
}
 
###################################
#��ȡ�ű��ĵ�һ������($1)�������ж�
#����ȡֵ��Χ��{start|stop|restart|status|info}
#���������ָ����Χ֮�ڣ����ӡ������Ϣ
###################################
case "$1" in
   'start')
      start
      ;;
   'stop')
     stop
     ;;
   'restart')
     stop
     start
     ;;
   'status')
     status
     ;;
   'info')
     info
     ;;
  *)
     echo "Usage: $0 {start|stop|restart|status|info}"
     exit 1
esac
exit 0