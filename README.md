# Build and deploy a Jetty based Java application using Oracle Cloud

Check [the blog](tbd) for details

## Build

- `git clone https://github.com/abhirockzz/accs-jetty-embedded.git`
- `mvn clean install` - creates `accs-jetty-embedded-dist.zip` in `target` directory

## Deploy to Oracle Cloud

- Use Developer Cloud - read [the blog](tbd)
- Use Application Container Cloud [console](http://docs.oracle.com/en/cloud/paas/app-container-cloud/csjse/exploring-application-deployments-page.html#GUID-5E4472B1-F5C6-4556-908C-D76C4C14FC60)
- Use Application Container Cloud [REST APIs](http://docs.oracle.com/en/cloud/paas/app-container-cloud/apcsr/op-paas-service-apaas-api-v1.1-apps-%7BidentityDomainId%7D-post.html)
- Use Application Container Cloud [PSM APIs](https://docs.oracle.com/en/cloud/paas/java-cloud/pscli/accs-push.html)

## Run locally

`java -jar target/accs-jetty-embedded.jar`

## Test

Refer to [this section](tbd) from the blog