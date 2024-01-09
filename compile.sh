path="/home/fanilo/Documents/L3/mrNaina/SCAFFOLDING"

shopt -s globstar
rm -r ./build/*
javac -cp "$(printf %s: /home/fanilo/Documents/L3/mrNaina/SCAFFOLDING/lib/**/*.jar)" -d $path/build $path/src/**/*.java
cp database.json build/
