Falstad-Circuit-Simulator
=================

This is the original version of Paul Falstad's circuit simulator with the following change(s):

* Move source files to a package named (com.falstad)
* Maven based project

Running
-------

Open project on IDE supporting Maven (Netbeans or Eclipse) then build and run.

You may prefer simply run:
    
    $ mvn clean install
    $ mvn exec:java -Dexec.mainClass=com.falstad.Circuit

Terms and Conditions
--------------------

Check <http://www.falstad.com/licensing.html> before redistributing or modifying the code. You must always consult the original licensing information but, in case the link is unavailable, here is a copy of the original license (as of 2019-03-13):

    You have permission to use these applets in a classroom setting or take screenshots as long as the applets are unmodified. Modification or redistribution for non-commercial purposes is allowed, as long as you credit me (Paul Falstad) and provide a link to my page (the page you found the applet(s) on, or http://www.falstad.com/mathphysics.html). Contact me for any other uses. The source code for each applet is generally available on that applet's web page, but some of the applets use third-party source code that has restrictions.
    THIS SOFTWARE IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.

