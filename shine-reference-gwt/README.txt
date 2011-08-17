
README
======
Pre-requistes : Maven 2.* must be installed and available from the command line.
GWT needs to be locally installed, this then would be the ~/.m2/settings.xml file

<settings>
   <profiles>
     <profile>
       <id>gwt-1.4.61</id>
       <properties>
         <google.webtoolkit.home>/Applications/java/gwt-mac-1.4.61</google.webtoolkit.home>
         <!-- XstartOnFirstThread needed only on the mac -->
         <google.webtoolkit.extrajvmargs>-XstartOnFirstThread</google.webtoolkit.extrajvmargs>
       </properties>
     </profile>
   </profiles>
   <activeProfiles>
     <activeProfile>gwt-1.4.61</activeProfile>
   </activeProfiles>
</settings>

Run
----
> mvn gwt:gwt
Run the application in hosted mode.

This will also create a war file that can be used in a Tomcat container. shine-reference-gwt.war

Note: to get this war file working the following needs to be followed (detailed in WEB-INF/web.xml)

<?xml version="1.0" encoding="UTF-8"?>
<web-app>
	
	<display-name>Shine GWT Sample</display-name>
	   <!-- 
    Bit of a hack here due to the bug in the gwt maven support
	http://groups.google.com/group/gwt-maven/browse_thread/thread/20276dab9cfcef38/679206309ed261da?lnk=gst&q=getModuleBaseURL#679206309ed261da			
	And the fact that "mvn gwt:gwt" and "mvn package" do not include the web.xml as
	built by gwt:gwt and placed in target/web.xml. So hosted mode works but deploying as a war doesnt.
	After deployment in tomcat, uncomment the following section to make it work.
	Any ideas for fixes for next iteration much appreciated!
	-->
	
	<!-- 	
	<servlet>
		<servlet-name>service</servlet-name>
		<servlet-class>com.shinetech.server.SampleServiceImpl</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>
		
	<servlet-mapping>
		<servlet-name>service</servlet-name>
		<url-pattern>/com.shinetech.SampleApp/service</url-pattern>
	</servlet-mapping>
	-->
</web-app>


