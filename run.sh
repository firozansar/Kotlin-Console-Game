#!/usr/bin/env bash

# "chmod +x run.sh" to make it executable
# ""./run.sh" to run

./gradlew --quiet "installDist" && "./build/install/Kotlin-Console-Game/bin/Kotlin-Console-Game"

