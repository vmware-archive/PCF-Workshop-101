#!/usr/bin/env bash

cf delete webtrader -f
cf delete portfolio -f
cf delete accounts -f
cf delete quotes -f

cf delete-service trader-db -f
cf delete-service trader-registry -f
cf delete-service config-server -f
cf delete-service trader-dashboard -f