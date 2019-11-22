#!/bin/sh
docker build . -t petstoregraal
mkdir -p build
docker run --rm --entrypoint cat petstoregraal  /home/application/function.zip > build/function.zip

sam local start-api -t sam.yaml -p 3000

