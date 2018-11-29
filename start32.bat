@echo off

set JPATH=
"%JPATH%java.exe" -version

echo.

"%JPATH%java.exe" -cp jars/swt-win32.jar;jars/swt-compare.jar;jars/repdev-win32.jar com.repdev.RepDevMain