# SDET-Clipboard-Health-Dilip-Meghwal

## steps to run as maven project
- run ```mvn clean test``` from command prompt.
- if using eclipse right click on pom.xml and run the desired command.
- if using the intellij select desired goals from the maven tab and click on run.

## steps to run from testngRunner.xml.
- Right click on testngRunner.xml and click on run.

## Run on local and remote
- set remote as ```false``` if you want to run local
- set remote as ```true in testng.xml``` if you want to run in docker.

## check cucumber reports in target folder
- path : ```target/cucumber-reports/cucumber-html-reports```