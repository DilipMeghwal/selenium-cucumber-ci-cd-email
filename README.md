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
- mvn clean test -Dremote=true -Dk8=true

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
* https://www.linkedin.com/pulse/selenium-4-grid-integration-kubernetes-rishi-khanna/
* https://www.linkedin.com/pulse/deploying-selenium-grid-k8s-use-locally-mikhail-shabatura-m5toe/
* minikube start —driver=docker
* minikube config set driver docker (if you see error "These changes will take effect upon a minikube delete and then a minikube start" then try and check minikube profile already created "minikube profile list")
* docker context use default
* kubectl create --filename=src/test/resources/staging/selenium-hub-deployment.yaml
* kubectl create --filename=src/test/resources/staging/selenium-hub-svc.yaml
* kubectl describe svc selenium-hub
* $NODEPORT = (kubectl get svc --selector 'app=selenium-hub' -o jsonpath='{.items[0].spec.ports[0].nodePort}')
* $NODE = (kubectl get nodes -o jsonpath='{.items[0].metadata.name}')
* curl http://${NODE}:${NODEPORT}
* minikube dashboard (minikube url)
* minikube addons enable ingress
* minikube tunnel
* kubectl create --filename=src/test/resources/staging/selenium-hub-deployement-ingress.yaml
* kubectl delete -A ValidatingWebhookConfiguration ingress-nginx-admission (If any error)
* kubectl get ingress
* update C:\Windows\System32\drivers\etc\hosts (IP and hosts)
* minikube service selenium-hub --url (To get selenium hub url)
* kubectl create --filename=src/test/resources/staging/selenium-node-chrome-deployment.yaml
* minikube ssh (to ping and check the ingress host, see notes below) => ping my-selenium-grid.com then exit
* mvn clean test -Dremote=true
* kubectl delete deployment selenium-hub
* kubectl delete deployment selenium-hub-deployement-ingress
* kubectl delete deployment selenium-node-chrome
* kubectl delete deployment selenium-node-firefox
* kubectl delete svc selenium-hub
* kubectl delete svc selenium-node-chrome
* kubectl delete all  --all -n default
* minikube stop
* minikube delete --all --purge
* rm -rf ~/.minikube/

minikube start --driver=docker --memory=1024Mi --force-systemd
minikube addons enable metrics-server
kubectl describe nodes
kubectl top node

### fetch local docker image
- eval $(minikube docker-env)
- docker build -t image-name
- minikube image ls

### macOS
* export NODEPORT=`kubectl get svc --selector='app=selenium-hub' --output=template --template="{{ with index .items 0}}{{with index .spec.ports 0 }}{{.nodePort}}{{end}}{{end}}"`
* export NODE=`kubectl get nodes --output=template --template="{{with index .items 0 }}{{.metadata.name}}{{end}}"`
* curl http://$NODE:$NODEPORT

### note for ingress on other than linux
- The thing to take away from this is that - on an O/S other than Linux - the IP address is 127.0.0.1 NOT whatever IP you see when you run > kubectl get ingress. This is because - on an OS other than Linux - you need minikube tunnel running as a 'bridge' between 127.0.0.1 and whatever IP the Ingress controller is using. It's 127.0.0.1 you need to reference in your hosts file, not the IP shown in > kubectl get ingress. Luck.

### aws
- https://www.youtube.com/watch?v=kUOMIFvMgfs


https://automationqahub.com/how-to-create-disposable-selenium-grid-using-docker/

### sonarqube
- mvn verify sonar:sonar -Dsonar.login=admin -Dsonar.password=admin@123
- https://www.fosstechnix.com/how-to-install-sonarqube-on-windows-4-steps/

## properties file lib
- https://eliasnogueira.com/easily-manage-properties-files-in-java-with-owner/


