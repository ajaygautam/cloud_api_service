# Docker
Setup Docker. Then:
```shell
gradle build
docker build -t gautam.ajay/cloud_api_service .
docker run -p 8080:8080 gautam.ajay/cloud_api_service
```

# AWS
1. Login into AWS -> My Account -> Security Credentials -> Access Keys -> Create new. The Show Access Key - copy this info.
1. Amazon ECR -> Repositories -> Create repository.
1. Select the repository just created and select `View push commands`. Adjust the below based on your profile
   ```shell
   brew install awscli
   aws configure
   aws ecr-public get-login-password --region us-east-1 | docker login --username AWS --password-stdin public.ecr.aws/s1h1b6y8
   docker build .....
   docker tag gautam.ajay/cloud_api_service:latest public.ecr.aws/s1h1b6y8/cloud_api_service:latest
   docker push public.ecr.aws/s1h1b6y8/cloud_api_service:latest
    ```
1. Refresh repo page to ensure the upload was successful.
1. Amazon ECS -> Task definitions -> Create new ...
    1. Fargate
    1. Pick defaults for most of the wizard
    1. Task Definition Name: `cloud_api_service`
    1. Task Memory: 1 GB, CPU: 0.25
    1. Add container:
        1. name: `cloud_api_service`
        1. Image: `public.ecr.aws/s1h1b6y8/cloud_api_service:latest`
        1. Port mappings: `8080`
    1. defaults for the rest... select `Create`
    1. Launch status completed -> `View Task definition`
1. Amazon ECS -> Clusters -> Create cluster. This failed on first try. Tried again, and it worked (!!)
    1. EC2 Linux + Networking only
    1. Cluster name: `cluster1-cloudApiService`
    1. Create an empty cluster
    1. `Create`
1. Amazon ECS-> Task Definitions page. Select the checkbox for the Task -> Actions -> `Run Task`
    1. Select mostly defaults for the form
    1. Select "Run Task" from the bottom right
    
# TODO / Next steps
* Add security and roles - IAM setup
* Create a build pipeline
    * Auto push to repo
