dir /b /s .\*.java > file.txt
findstr /i .java file.txt > src.txt
mkdir temp
javac --source 8 --target 8 -cp library/DAO.jar -d temp @src.txt
del file.txt
del src.txt

@rem xcopy ./library/DAO.jar ./temp/ 

@rem jar --create --file scaffold.jar -e generator.Main -C temp .
jar cvMf scaffold.jar META-INF -C temp .

del temp