#!/usr/bin/env bash

# Read path to file from parameters.
# Search within the file for name and replace with Main.
# Move and rename the file to src/main/java/Main.
# Call uva-node to submit file.

sed -i -e 's/foo/bar/g' filename