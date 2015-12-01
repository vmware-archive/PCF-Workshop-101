#!/bin/bash

## Parameters
## Usage:
## $ seedpcf.sh <api.domain.com>

## create demo users
cf api --skip-ssl-validation $1
### Get admin password after successful install using OpsMgr.
cf login -u instructor -p gonative -o system -s console



i="1"
## create personal accounts
while [ $i -lt 26 ]
do
pword="password"
cf delete-user -f student$[$i]
cf delete-org -f student$[$i]-org

i=$[$i+1]
done

cf delete-quota -f workshop

cf logout
