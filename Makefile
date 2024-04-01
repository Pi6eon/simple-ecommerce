build-app:
	./gradlew bootBuildImage --imageName=fallinnadim/jobapp

postgres:
	docker run --name postgres14 \
    	--network jobapp \
    	-p 5432:5432 \
    	-e POSTGRES_USER=fallinnadim \
    	-e POSTGRES_PASSWORD=Nadim456*- \
    	-e POSTGRES_INITDB_ARGS="--auth-host=scram-sha-256 --auth-local=scram-sha-256" \
    	-d postgres:14.11-alpine3.19

server :
	docker run --name spring-app \
        	--network jobapp \
        	-p 8080:8080 \
        	-d fallinnadim/jobapp:latest

.PHONY: build-app, postgres, server