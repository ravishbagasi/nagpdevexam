####Installing JDK1.8 from the below path
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

###After installing java
Set a path in Environment variables(System Variables) in the My Computer
Variable Name: JAVA_HOME
Variable value: C:\Program Files\Java\jdk1.8.0_131 (location of the java installed path)

On Both User and System Variables
Variable Name: path
Variable value: C:\Program Files\Java\jdk1.8.0_131\bin (location of the java installed path till bin folder)

Try to run the command "javac" in command prompt

### Installing TestNG
Go to Eclipse help and click on the button to “Install New Software”.
Click the Add button and type “TestNG” as name and “http://dl.bintray.com/testng-team/testng-eclipse-release/” as a path.
Once you add the Path, you will notice a TestNG entry under the name. You then need to select that and click on Next to install TestNG in Eclipse. Accept the license agreement and hit the Finish button.


### Installing maven
http://mirror.metrocast.net/apache/maven
Download the zip file and extract it.
Now move the extracted file to location where java is installed if java is installed(C:\Program Files) then move the extracted folder to this loaction(C:\Program Files)

add a path in Environment variables(System Varaibles) in the My Computer
Variable Name: M2_HOME
Variable value: C:\Program Files (x86)\apache-maven-3.6.1 


add a path in Environment variables(User Varaibles) in the My Computer
Variable Name: path
Variable value: M2_HOME%\bin 

Try to run the command "mvn -version" in command prompt


#For runnning the complete project 
open the project and open the command prompt on that project
run the command ' mvn clean install '


#For running the testNG file
open the project and open the command prompt on that project
run the command ' mvn clean test -DtestngFile=FileName(Either TestNG.xml OR TestNG_SelectedMethods.xml) '





