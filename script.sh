#!/bin/bashSS
if [[ $# != 2 ]] ; then
    echo "Got $# params, expected 2!"
    exit 1
fi

rm -rf input
mkdir input

POSTFIX=("localhost pulseaudio.desktop: W: [pulseaudio] main.c: This program is not intended to be run as root (unless --system is specified)."
          "localhost gnome-session-binary[2187]: WARNING: Application 'org.gnome.SettingsDaemon.Housekeeping.desktop' killed by signal 15")

for i in $(seq "$2")
  do
    HOUR=$((RANDOM % 24))
    PRIORITY=$((RANDOM % 8))
      if [ $HOUR -le 9 ]; then
        TWO_DIGIT_HOUR="0$HOUR"
      else
        TWO_DIGIT_HOUR="$HOUR"
      fi
    RESULT="Nov 15 $TWO_DIGIT_HOUR:12:33 $PRIORITY ${POSTFIX[$((RANDOM % ${#POSTFIX[*]}))]}"
    echo $RESULT >> input/"$1.log"
  done

start-dfs.sh
start-yarn.sh
hdfs dfs -rm -r input
hdfs dfs -rm -r output
hdfs dfs -put ~/Desktop/hw2/input input
spark-submit --class bigdata.hw2.SparkApp --master local ~/Desktop/hw2/target/hw2-1.0-SNAPSHOT.jar \
  hdfs://localhost:9000/user/root/input hdfs://localhost:9000/user/root/output/

hdfs dfs -cat output/part-00000
