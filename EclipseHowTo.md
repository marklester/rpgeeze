Here's my accelerated guide to setting up Eclipse to work with the version control system and JOGL. This might be a bit daunting the first time you do it if you're not familiar with these technologies, so if you reach an impasse, feel free to call or e-mail me. I can also help you set this up during the discussion section. Without further ado, the guide:

  * download and install [Eclipse](http://www.eclipse.org/)
  * install [Subclipse](http://subclipse.tigris.org/)
    * fire up Eclipse
    * go to Help, Software Updates, Find and Install
    * continue following the [online instructions](http://subclipse.tigris.org/servlets/ProjectProcess?pageID=p4wYuA)
    * NOTE: some people had problems with Subclipse 1.4, so you may want to just use 1.2, the update URL is http://subclipse.tigris.org/update_1.2.x
  * in Eclipse, create a new project
    * select New, Project..., SVN, Checkout Projects from SVN
    * add the repository location
    * input your credentials when prompted
    * only get the trunk directory, we'll deal with tags and branches later as needed
    * say you want the New Project wizard when asked and configure it as a Java project
    * sit back, relax, the download's a few MB because of JOGL
  * set up JOGL
    * this is largely based on [this tutorial](http://timelessname.com/jogl/lesson01/), so you can check that page for guidance; but please note that I've altered the directory structure a bit
    * make sure the project you just created is selected, then go to Project, Properties on the menu bar
    * pick Java Build Path on the left pane, then go to the Libraries tab
    * click Add JARs and navigate to the lib/jogl directory in your project; add jogl.jar and gluegen-rt.jar
    * click the triangle next to jogl.jar; you should see several variables, like Source attachment, Javadoc location, etc.; we will edit these now
    * click Source attachment, click Edit, click Workspace, find and select jogl-1.1.1-src.zip in the lib/jogl directory
    * click Javadoc location, click Edit, select the Javadoc in archive option, select the Workspace file option, find and select jogl-1.1.1-docs.zip in the lib/jogl directory, then set the path within archive to jogl-1.1.1-docs; it should now validate
    * if this worked, then you shouldn't have any compile errors anymore; we're almost done
    * go to the src directory and right-click RunGame.java, then run it as a Java application; you will get an exception because it doesn't find the libraries
    * right-click RunGame.java again, go to Run As, then edit Run Configurations, and go to the Arguments tab
      * if you have Linux/x86, add -Djava.library.path=lib/jogl/linux-i586 to the VM arguments
      * if you have Mac OS X 10.4+, add -Djava.library.path=lib/jogl/macosx-universal to the VM arguments
      * if you have Windows/x86, add -Djava.library.path=lib/jogl/windows-i586 to the VM arguments
      * if you have another operating system or architecture, you will have to download your system-specific files, make the corresponding directory in lib/jogl and edit a couple configuration files; if you don't want to, just tell me and I'll do it
    * the Ant script build.xml includes several tasks for building and packaging the project for distribution; you can right-click it and run it; it will create a dist directory including RunGame.sh and RunGame.bat which should let you execute the project outside Eclipse