dir /b /s .\*.java > file.txt
findstr /i .java file.txt > src.txt
mkdir temp
javac --source 8 --target 8 -cp library/DAO.jar -d temp @src.txt
del file.txt
del src.txt
cd temp
jar --create --file scaffold.jar --main-class generator.Main -C build .
cd ..
del temp

