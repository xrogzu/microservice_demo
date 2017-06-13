for file in `ls .`
do
  if [ -d ./$file ]
  then
    cd $file && mvn docker:build -DpushImage -DskipTests && cd ..
  fi
done