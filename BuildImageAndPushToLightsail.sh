mvn clean package
docker build -t backend-app .
aws lightsail push-container-image --region us-east-1 --service-name backend-container-service --label backend-app --image backend-app:latest