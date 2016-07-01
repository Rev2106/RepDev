@echo off

set JPATH=
"%JPATH%java.exe" -version

echo.

"%JPATH%java.exe" -cp swt-win/swt64.jar;swtcompare.jar;./ -Djava.library.path=swt-win com.repdev.RepDevMain