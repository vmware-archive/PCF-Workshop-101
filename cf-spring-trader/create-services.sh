#!/usr/bin/env bash
cf create-service p-mysql 5000mb-dev trader-db
cf create-service p-service-registry standard trader-registry
cf create-service p-config-server standard config-server
cf create-service p-circuit-breaker-dashboard standard trader-dashboard
