@echo off

set JPATH=
"%JPATH%java.exe" -version

echo.

"%JPATH%java.exe" -cp swt-win/64/swt.jar;swt-win/swtcompare.jar;./ -Djava.library.path=swt-win com.repdev.RepDevMain