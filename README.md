# SDET-Clipboard-Health-Dilip-Meghwal

## steps to run as maven project
- run ```mvn clean test``` from command prompt.
- if using eclipse right click on pom.xml and run the desired command.
- if using the intellij select desired goals from the maven tab and click on run.

## steps to run from testngRunner.xml.
- Right click on testngRunner.xml and click on run.

## Run with docker
- docker-compose up
- mvn clean test -Dremote=true

## check cucumber reports in target folder
- path : ```target/cucumber-reports/cucumber-html-reports```

## Jenkins + Github Actions + Kubernetes
* https://stackoverflow.com/questions/72341686/jenkins-allow-local-checkout
* https://harshityadav95.medium.com/how-to-setup-docker-in-jenkins-on-mac-c45fe02f91c5
* https://medium.com/@saidinesh.narisetti/run-selenium-test-in-docker-with-github-actions-16ee17fe6b07
* https://harshitshah156.medium.com/host-your-automation-report-on-github-pages-with-github-actions-69f80857bd28
* https://www.youtube.com/watch?v=OHKC8x3Ru2Q
* 
* https://www.linkedin.com/pulse/selenium-4-grid-integration-kubernetes-rishi-khanna/
* https://github.com/kubernetes/examples/tree/master/staging/selenium

### Windows
* https://minikube.sigs.k8s.io/docs/start/
* https://kubernetes.io/docs/tasks/tools/install-kubectl-windows/
* https://sahajamit.medium.com/spinning-up-your-own-scalable-selenium-grid-in-kubernetes-part-1-e4017bac68f4
* $NODEPORT = (kubectl get svc --selector 'app=selenium-hub' -o jsonpath='{.items[0].spec.ports[0].nodePort}')
* $NODE = (kubectl get nodes -o jsonpath='{.items[0].metadata.name}')
* curl http://${NODE}:${NODEPORT}
* minikube dashboard (minikube url)
* minikube service selenium-hub --url (To get selenium hub url)
* kubectl delete deployment selenium-hub
* kubectl delete deployment selenium-node-chrome
* kubectl delete deployment selenium-node-firefox
* kubectl delete deployment selenium-python
* kubectl delete svc selenium-hub


