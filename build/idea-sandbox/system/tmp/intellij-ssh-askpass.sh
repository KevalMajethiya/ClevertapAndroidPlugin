#!/bin/sh
"/Library/Java/JavaVirtualMachines/jdk-15.0.2.jdk/Contents/Home/bin/java" -cp "/Applications/Android Studio.app/Contents/plugins/git4idea/lib/git4idea-rt.jar:/Applications/Android Studio.app/Contents/lib/xmlrpc-2.0.1.jar:/Applications/Android Studio.app/Contents/lib/commons-codec-1.14.jar:/Applications/Android Studio.app/Contents/lib/util.jar" org.jetbrains.git4idea.nativessh.GitNativeSshAskPassApp "$@"
