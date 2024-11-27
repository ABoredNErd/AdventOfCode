clean=false
for command in $@; do
    if [ $command = "clean" ] ; then
        clean=true
    fi
done

if [ $clean = true ] ; then
    mvn clean install
else
    mvn install
fi

java -jar target/advent-0.1.0.jar
