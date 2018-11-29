@echo off

set JPATH=
"%JPATH%java.exe" -version

echo.

"%JPATH%java.exe" -cp jars/swt-win64.jar;jars/swt-compare.jar;jars/repdev-win64.jar com.repdev.RepDevMain