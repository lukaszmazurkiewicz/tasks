call runcrud.bat
if "%ERRORLEVEL%" == "0" goto startchrome
echo.
echo GRADLEW BUILD has errors - breaking work
goto fail

:startchrome
start /wait chrome "http://localhost:8080/crud/v1/task/getTasks"

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.