
FROM docker.io/bitnami/wildfly:30

COPY deploy/ /opt/jboss/wildfly/standalone/deployments/
