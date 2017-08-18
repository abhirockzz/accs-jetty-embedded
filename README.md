# Build and deploy a Jetty based Java application using Oracle Cloud

Check [the blog](tbd) for details

## Build

- `git clone https://github.com/abhirockzz/accs-jetty-embedded.git`
- `mvn clean install` - creates `accs-jetty-embedded-dist.zip` in `target` directory

## Deploy to Oracle Cloud

- Use Oracle Developer Cloud - read [the blog](https://medium.com/oracledevs/build-deploy-a-jetty-based-app-using-oracle-application-container-cloud-oracle-developer-cloud-c756f7a04a3b)
- Use Oracle Application Container Cloud [console](http://docs.oracle.com/en/cloud/paas/app-container-cloud/csjse/exploring-application-deployments-page.html#GUID-5E4472B1-F5C6-4556-908C-D76C4C14FC60)
- Use Oracle Application Container Cloud [REST APIs](http://docs.oracle.com/en/cloud/paas/app-container-cloud/apcsr/op-paas-service-apaas-api-v1.1-apps-%7BidentityDomainId%7D-post.html)
- Use Oracle Application Container Cloud [PSM APIs](https://docs.oracle.com/en/cloud/paas/java-cloud/pscli/accs-push.html)

## Run locally

- `git clone https://github.com/abhirockzz/accs-jetty-embedded.git`
- `mvn clean install`
- `java -jar target/accs-jetty-embedded.jar`

## Test

Refer to [**Test the application** section](https://medium.com/oracledevs/build-deploy-a-jetty-based-app-using-oracle-application-container-cloud-oracle-developer-cloud-c756f7a04a3b) from the blog
