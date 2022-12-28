mvn clean package
docker build -t backend-app .
docker run -p 8080:8080 -d backend-app