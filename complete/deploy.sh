#!/bin/sh
docker build . -t petstore
mkdir -p build
docker run --rm --entrypoint cat petstore  /home/application/function.zip > build/function.zip