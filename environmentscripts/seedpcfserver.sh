#!/bin/bash

## Parameters
## Usage:
## $ seedpcf.sh <api.domain.com>

## create demo users
cf api --skip-ssl-validation $1

## login
cf login -u instructor -p gonative -o system -s console


cf create-quota workshop -m 10G -r 1000 -s 10

i="1"
## create personal accounts
while [ $i -lt 26 ]
do
pword="password"
cf create-user student$[$i] $pword
cf create-org student$[$i]-org
cf set-quota student$[$i]-org workshop
#cf set-quota student$[$i]-org default
cf set-org-role student$[$i] student$[$i]-org OrgManager
cf create-space development -o student$[$i]-org
cf set-space-role student$[$i] student$[$i]-org development SpaceDeveloper
cf create-space test -o student$[$i]-org
cf set-space-role student$[$i] student$[$i]-org test SpaceDeveloper
cf create-space production -o student$[$i]-org
cf set-space-role student$[$i] student$[$i]-org production SpaceDeveloper

i=$[$i+1]
done


cf logout
